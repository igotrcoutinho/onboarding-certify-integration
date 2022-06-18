package io.smilego.onboarding.certify.service.api.application.service;

import io.micronaut.runtime.event.annotation.EventListener;
import io.smilego.onboarding.certify.service.api.application.configuration.property.ApplicationProperties;
import io.smilego.onboarding.certify.service.api.application.exception.MaxAttemptsReachedException;
import io.smilego.onboarding.certify.service.api.application.exception.ProcessException;
import io.smilego.onboarding.certify.service.api.domain.erro.Erro;
import io.smilego.onboarding.certify.service.api.domain.model.Processo;
import io.smilego.onboarding.certify.service.api.domain.properties.PropertiesHandler;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Properties;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.domain.properties.functional.PropertyConverter;
import io.smilego.onboarding.certify.service.api.domain.resultado.Resultado;
import io.smilego.onboarding.certify.service.api.domain.service.ConsultaService;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.client.RabbitMQAsleepClient;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.client.RabbitMQMainClient;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.DownloadFileException;
import io.smilego.onboarding.certify.service.api.infrastructure.handler.error.ErrorHandler;
import io.smilego.onboarding.certify.service.api.infrastructure.log.ApplicationLogger;
import io.smilego.onboarding.certify.service.api.infrastructure.log.Log;
import io.smilego.onboarding.certify.service.api.infrastructure.service.DocumentService;
import io.smilego.onboarding.certify.service.api.infrastructure.service.FileService;
import io.smilego.onboarding.certify.service.api.infrastructure.service.ProcessService;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.KapptaResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create.CreateProcessRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertResponse.DocumentInformation;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.get.GetProcessResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.status.TypedStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class ConsultaServiceImpl implements ConsultaService {

    @Inject
    private PropertiesHandler propertiesHandler;

    @Inject
    private FileService arquivoService;

    @Inject
    private ProcessService processService;

    @Inject
    private RabbitMQAsleepClient amqpAsleepClient;

    @Inject
    private RabbitMQMainClient amqpMainClient;

    @Inject
    private ApplicationProperties applicationProperties;

    @Inject
    private ApplicationLogger logger;

    @Inject
    private DocumentService documentoService;

    @Inject
    private ErrorHandler errorHandlerService;

    private final String costumerId = "${certify.process.url}";

    public static final Set<String> downloadables = Arrays.asList("pessoa.face").stream().collect(Collectors.toSet());

    @EventListener
    public void onProcessoRecebido(Processo processo) {
        logger.info(Log.Builder.of()
            .setHeader("Processo recebido")
            .addRow("processo", processo)
            .build()
        );

        try {
            if (!processo.isExecutado()) {
                List<DocumentInsertRequest> documentos = documentoService.convert(processo.getPropriedades());

                try {
                    iniciarProcesso(processo, documentos);
                    //enviarDocumentos(processo, documentos);
                    //executarProcesso(processo);
                } catch(ProcessException e) {
                    Integer codigo = e.getResponse().map(r -> r.getError().getCode()).orElse(Integer.MAX_VALUE);
                    if (!Erro.isProcessoFinalizado(codigo))
                        throw e;
                    processo.setExecutado(true);
                    amqpMainClient.send(processo);
                }
            } else {
                //verificarProcesso(processo);
            }
        } catch (ProcessException e) {
            errorHandlerService.handlerError(processo, e);
        } catch (Exception e) {
            logger.error(Log.Builder.of()
                .setHeader("Erro durante o processamento")
                .addRow("process", processo.getId())
                .addRow("processo", processo)
                .addRow("error", e.getClass())
                .addRow("message", e.getMessage())
                .build(),
                e
            );

            errorHandlerService.handlerError(processo, e);
        }
    }

    public void iniciarProcesso(Processo processo, List<DocumentInsertRequest> documentos) {
        String processId = processo.getId();

        if (!StringUtils.isNullOrEmpty(processId)) {
            return;
        }

//        CreateProcessRequest createProcessRequest = converter(processo.getPropriedades());
        ReceiveRequest createProcessRequest = converterCertify(processo.getPropriedades(), documentos);

        //createProcessRequest.setOnlySelfie(isOnlySelfie);

        processId = processService.createProcess(createProcessRequest);

        processo.setId(processId);
    }

//    public void enviarDocumentos(Processo processo, List<?> documentos) {
//        if (processo.isDocumentosInseridos()) {
//            return;
//        }
//
//        Set<Property> propriedadesOcr = new HashSet<>();
//        boolean documentosTipificado = true;
//
//        try {
//            for(Object documento: documentos) {
//                DocumentInsertResponse response = processService.sendDocument(processo.getId(), (DocumentInsertRequest) documento);
//                DocumentInformation information = response.getInformation();
//
//                if (TypedStatus.FAILED.equals(response.getType())) {
//                    documentosTipificado = false;
//                }
//
//                if (Objects.nonNull(information)) {
//                    Properties properties = propertiesHandler.extract(information);
//                    propriedadesOcr.addAll(properties.asList());
//                }
//            }
//        } catch (ProcessException e) {
//            if (!processo.isEtapaTentativaUnica())
//                throw e;
//            documentosTipificado = false;
//        }
//
//        processo.setDocumentosTipificado(documentosTipificado);
//        processo.setPropriedadesOcr(propriedadesOcr);
//    }
//
//    public void executarProcesso(Processo processo) {
//        processService.executeProcess(processo.getId());
//        processo.setExecutado(true);
//        amqpAsleepClient.suspend(processo);
//    }
//
//    public void verificarProcesso(Processo processo) {
//        GetProcessResponse response = processService.getProcess(processo.getId());
//
//        if (!response.hasFinished()) {
//            processo.incrementarTentativasConsulta();
//
//            Log.Builder logBuilder = Log.Builder.of()
//                .addRow("process", processo.getId())
//                .addRow("response", response);
//
//            if (processo.getTentativasConsulta() >= applicationProperties.getProcess().getRetry().getMaxAttempts()) {
//                logger.error(logBuilder.setHeader("Processo Abortado - Tentativas Máximas Alcanças").build());
//                throw new MaxAttemptsReachedException("Tentativas máximas alcanças para consultas ao método GetProcess.");
//            }
//
//            logger.info(logBuilder.setHeader("Processo não concluído - Suspenso").build());
//
//            amqpAsleepClient.suspend(processo);
//
//            return;
//        }
//
//        KapptaResponse retorno = gerarRetorno(processo, response);
//
//        logger.info(Log.Builder.of()
//            .setHeader("Resultado da consulta")
//            .addRow("resultado", retorno.getResultado())
//            .addRow("response", retorno)
//            .build()
//        );
//
//        amqpMainClient.send(retorno);
//    }
//
//    private Resultado avaliarProcesso(GetProcessResponse response) {
//        if (response.isError())
//            return Resultado.ERRO;
//
//        if (response.isDivergent())
//            return Resultado.REPROVADO;
//
//        if (response.isCanceled())
//            return Resultado.ABSTIDO;
//
//        return validarScore(response.getScore());
//    }
//
//    private Resultado validarScore(Integer score) {
//        TreeSet<Integer> abstentionRange = applicationProperties.getProcess().getDecisionLimiters();
//        return Objects.isNull(abstentionRange.higher(score)) ? Resultado.APROVADO :
//                    Objects.isNull(abstentionRange.lower(score)) ? Resultado.REPROVADO :
//                        Resultado.ABSTIDO;
//    }

    private CreateProcessRequest converter(List<Property> propriedades) {
        Properties properties = Properties.create(propriedades);

        PropertyConverter converter = (alias, property) -> downloadables.contains(alias) ?
                arquivoService.downloadAsBase64(Integer.parseInt((String)property)).orElseThrow(
                    () -> new DownloadFileException("Não foi possível efetuar o download do arquivo id " + (String)property)) :
                property;

        return propertiesHandler.load(CreateProcessRequest.class, properties, converter);
    }

    private ReceiveRequest converterCertify(List<Property> propriedades, List<DocumentInsertRequest> documentos) {
        Properties properties = Properties.create(propriedades);

        PropertyConverter converter = (alias, property) -> downloadables.contains(alias) ?
                arquivoService.downloadAsBase64(Integer.parseInt((String)property)).orElseThrow(
                        () -> new DownloadFileException("Não foi possível efetuar o download do arquivo id " + (String)property)) :
                property;
        CreateProcessRequest createRequest =  propertiesHandler.load(CreateProcessRequest.class, properties, converter);
        ReceiveRequest receiveRequest = new ReceiveRequest();
        receiveRequest.setName(createRequest.getInformation().getName());
        receiveRequest.setEmail(createRequest.getInformation().getEmail());
        receiveRequest.setCpf(createRequest.getInformation().getDocument());
        receiveRequest.seteOnboardingType(1);
        receiveRequest.setCostumerId(applicationProperties.getCostumerId());
        ReceiveRequest.Face face = new ReceiveRequest.Face();
        face.setImage(createRequest.getFaceImage());
        receiveRequest.setFace(face);
        ReceiveRequest.Document document = new ReceiveRequest.Document();
        try {
            documentos.forEach(d->{
                switch (d.getType().typeSide()){
                    case FRONT:
                        document.setImageFront(d.getImage());
                        break;
                    case BACK:
                        document.setImageBack(d.getImage());
                        break;
                }
            });
        } catch (ProcessException e) {
            throw e;
        }

        receiveRequest.setDocument(document);


        return receiveRequest;
    }

//    private KapptaResponse gerarRetorno(Processo processo, GetProcessResponse processResponse) {
//        Resultado resultado = avaliarProcesso(processResponse);
//
//        Properties properties = propertiesHandler.extract(processResponse);
//        List<Property> propriedades = new ArrayList<>();
//
//        propriedades.addAll(properties.asList());
//        propriedades.addAll(processo.getPropriedadesOcr());
//
//        KapptaResponse kapptaResponse = KapptaResponse.of(processo.getEtapaId(), resultado, propriedades, null);
//        kapptaResponse.adicionarRespostaBiometria(processo.getId(), processo.isDocumentosTipificado());
//        return kapptaResponse;
//    }
//
}
