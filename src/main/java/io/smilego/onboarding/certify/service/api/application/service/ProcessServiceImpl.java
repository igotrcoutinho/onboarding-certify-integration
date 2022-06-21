package io.smilego.onboarding.certify.service.api.application.service;

import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.smilego.onboarding.certify.service.api.application.exception.ProcessException;
import io.smilego.onboarding.certify.service.api.infrastructure.client.CertifyCertificationClient;
import io.smilego.onboarding.certify.service.api.infrastructure.client.CertifyIdentificationClient;
import io.smilego.onboarding.certify.service.api.infrastructure.log.ApplicationLogger;
import io.smilego.onboarding.certify.service.api.infrastructure.log.Log;
import io.smilego.onboarding.certify.service.api.infrastructure.service.ProcessService;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.detail.DetailResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.error.ErrorReceiveResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.identification.ScoreResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.error.ErrorProcessResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ProcessServiceImpl implements ProcessService {

    @Inject
    private CertifyCertificationClient certificationClient;

    @Inject
    private CertifyIdentificationClient identificationClient;

    @Inject
    private ApplicationLogger logger;

    @FunctionalInterface
    public static interface ConsultCaller<T> {
        public T call(String authorization);
    }

    @Override
    public String createProcess(ReceiveRequest request) {
        try {
            ReceiveResponse response = certificationClient.receive(request);

            logger.info(Log.Builder.of()
                .setHeader("Processo criado")
                .addRow("request", request)
                .addRow("response", response)
                .build()
            );

            return response.getId();
        } catch (HttpClientResponseException e) {
            Optional<ErrorReceiveResponse> response = e.getResponse().getBody(ErrorReceiveResponse.class);
            String message = response.map(r -> r.getErrors()).orElse(e.getMessage());

            logger.error(Log.Builder.of()
                .setHeader("Erro durante a criação do processo")
                .addRow("status", e.getStatus())
                .addRow("request", request)
                .addRow("response", response.orElse(null))
                .addRow("message", message)
                .build(),
                e
            );

            throw new ProcessException("Erro ao criar processo - " + message, e, response);
        }
    }

    @Override
    public DetailResponse getProcess(String processId) {
        try {
            DetailResponse response = certificationClient.details(processId);

            logger.info(Log.Builder.of()
                .setHeader("Resultado do Processo")
                .addRow("process", processId)
                .addRow("response", response)
                .build()
            );

            return response;
        } catch (HttpClientResponseException e) {
            Optional<ErrorReceiveResponse> response = e.getResponse().getBody(ErrorReceiveResponse.class);
            String message = response.map(r -> r.getErrors()).orElse(e.getMessage());

            logger.error(Log.Builder.of()
                .setHeader("Erro durante a recuperação do processo")
                .addRow("process", processId)
                .addRow("status", e.getStatus())
                .addRow("response", response.orElse(null))
                .addRow("message", message)
                .build(),
                e
            );

            throw new ProcessException("Erro ao recuperar o processo - " + message, e, response);
        }
    }

    public ScoreResponse getScore(String cpf, String processId) {
        try {
            ScoreResponse response = identificationClient.getScore(cpf, processId);

            logger.info(Log.Builder.of()
                    .setHeader("Resultado do Score")
                    .addRow("process", processId)
                    .addRow("response", response)
                    .build()
            );

            return response;
        } catch (HttpClientResponseException e) {
            Optional<ErrorReceiveResponse> response = e.getResponse().getBody(ErrorReceiveResponse.class);
            String message = response.map(r -> r.getErrors()).orElse(e.getMessage());

            logger.error(Log.Builder.of()
                            .setHeader("Erro durante a recuperação do score processo")
                            .addRow("process", processId)
                            .addRow("status", e.getStatus())
                            .addRow("response", response.orElse(null))
                            .addRow("message", message)
                            .build(),
                    e
            );

            throw new ProcessException("Erro ao recuperar o score do processo - " + message, e, response);
        }
    }

}
