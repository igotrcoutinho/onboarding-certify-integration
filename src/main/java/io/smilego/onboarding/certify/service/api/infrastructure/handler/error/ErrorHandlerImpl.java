package io.smilego.onboarding.certify.service.api.infrastructure.handler.error;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import io.micronaut.http.client.exceptions.HttpClientException;
import io.smilego.onboarding.certify.service.api.application.configuration.property.ApplicationProperties;
import io.smilego.onboarding.certify.service.api.application.exception.ReceiveException;
import io.smilego.onboarding.certify.service.api.domain.erro.Erro;
import io.smilego.onboarding.certify.service.api.domain.model.Processo;
import io.smilego.onboarding.certify.service.api.domain.resultado.Resultado;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.client.RabbitMQAsleepClient;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.client.RabbitMQMainClient;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.ArchitetureException;
import io.smilego.onboarding.certify.service.api.infrastructure.service.CertificationService;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.error.ErrorReceiveResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.KapptaResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


@Singleton
public class ErrorHandlerImpl implements ErrorHandler {

    @Inject
    private ApplicationProperties applicationProperties;

    @Inject
    private RabbitMQAsleepClient amqpAsleepClient;

    @Inject
    private RabbitMQMainClient amqpMainClient;

    @Inject
    private CertificationService certificationService;

    @Override
    public void handlerError(Processo processo, Exception exception) {
        if (exception instanceof ReceiveException) {
            handleProcessError(processo, (ReceiveException) exception);
        } else if (exception instanceof HttpClientException) {
            handleHttpError(processo, (HttpClientException) exception);
        } else if (exception instanceof ArchitetureException) {
            handleArchitetureError(processo, exception);
        } else {
            handleCommonError(processo, exception);
        }
    }

    private void handleHttpError(Processo processo, HttpClientException exception) {
        processo.addErro(exception.getMessage());
        handleRetentativa(processo);
    }

    private void handleCommonError(Processo processo, Exception exception) {
        processo.addErro(exception.getMessage());
        handleRetentativa(processo);
    }

    private void handleArchitetureError(Processo processo, Exception exception) {
        processo.addErro(exception.getMessage());
        handleInconsistencia(processo);
    }

    private void handleProcessError(Processo processo, ReceiveException exception) {
        Optional<ErrorReceiveResponse> response = exception.getResponse();
        Integer codigo = response.map(r -> r.getStatus()).orElse(Integer.MAX_VALUE);
        String mensagem = response.map(r -> r.getErrors()).orElse(exception.getMessage());

        processo.addErro(mensagem);

        if (Erro.isCapturaFaceInvalida(codigo)) {
            handleCapturaFaceInvalida(processo);
        } else if (Erro.isCapturaDocumentosInvalida(codigo)) {
            handleCapturaDocumentacaoInvalida(processo);
        } else if (Erro.isInconsistente(codigo)) {
            handleInconsistencia(processo);
        } else if (Erro.isTemporario(codigo)) {
            handleRetentativa(processo);
        } else {
            throw new RuntimeException("Código de erro desconhecido (" + codigo + ")");
        }
    }

    private void handleCapturaFaceInvalida(Processo processo) {
        KapptaResponse retorno = KapptaResponse.of(processo.getEtapaId(), Resultado.DOCUMENTACAO_INVALIDA, null, new HashSet<>(processo.getErros()).toString());
        amqpMainClient.send(retorno);
    }

    private void handleCapturaDocumentacaoInvalida(Processo processo) {
        KapptaResponse retorno = KapptaResponse.of(processo.getEtapaId(), Resultado.DOCUMENTACAO_INVALIDA, null, new HashSet<>(processo.getErros()).toString());
        if (processo.isEtapaTentativaUnica()) {
            //TODO receive execute
            //certificationService.executeProcess(processo.getId());
            retorno = KapptaResponse.of(processo.getEtapaId(), Resultado.ABSTIDO, null, new HashSet<>(processo.getErros()).toString());
            retorno.adicionarRespostaBiometria(processo.getId(), processo.isDocumentosTipificado());
        }
        amqpMainClient.send(retorno);
    }

    private void handleInconsistencia(Processo processo) {
        KapptaResponse retorno = KapptaResponse.of(processo.getEtapaId(), Resultado.ERRO, null, new HashSet<>(processo.getErros()).toString());
        amqpMainClient.send(retorno);
    }

    private void handleRetentativa(Processo processo) {
        if (processo.getErrosCount() < applicationProperties.getError().getRetry().getMaxAttempts()) {
            amqpAsleepClient.suspendByError(processo);
        } else {
            KapptaResponse retorno = KapptaResponse.of(processo.getEtapaId(), Resultado.ABSTIDO, null, new HashSet<>(processo.getErros()).toString());
            if (Objects.nonNull(processo.getId())) {
                retorno.adicionarRespostaBiometria(processo.getId(), processo.isDocumentosTipificado());
                //TODO receive execute
                //certificationService.executeProcess(processo.getId());
                amqpMainClient.send(retorno);
            }
        }

    }
}
