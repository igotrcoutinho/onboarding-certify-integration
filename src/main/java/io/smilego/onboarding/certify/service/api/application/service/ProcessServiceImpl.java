package io.smilego.onboarding.certify.service.api.application.service;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.smilego.onboarding.certify.service.api.application.exception.ProcessException;
import io.smilego.onboarding.certify.service.api.infrastructure.client.CertifyCertificationClient;
import io.smilego.onboarding.certify.service.api.infrastructure.log.ApplicationLogger;
import io.smilego.onboarding.certify.service.api.infrastructure.log.Log;
import io.smilego.onboarding.certify.service.api.infrastructure.service.ProcessService;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create.CreateProcessRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create.CreateProcessResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.error.ErrorProcessResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.execute.ExecuteProcessResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.get.GetProcessResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ProcessServiceImpl implements ProcessService {

//    @Inject
//    private AuthService authService;
//
//    @Inject
//    private UnicoProcessClient processClient;

    @Inject
    private CertifyCertificationClient certificationClient;

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
            Optional<ErrorProcessResponse> response = e.getResponse().getBody(ErrorProcessResponse.class);
            String message = response.map(r -> r.getError().getDescription()).orElse(e.getMessage());

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

//    @Override
//    public DocumentInsertResponse sendDocument(String processId, DocumentInsertRequest request) {
//        try {
//            DocumentInsertResponse response = consult(a -> processClient.documentInsert(a, processId, request));
//
//            logger.info(Log.Builder.of()
//                .setHeader("Documento enviado")
//                .addRow("process", processId)
//                .addRow("request", request)
//                .addRow("response", response)
//                .build()
//            );
//
//            return response;
//        } catch (HttpClientResponseException e) {
//            Optional<ErrorProcessResponse> response = e.getResponse().getBody(ErrorProcessResponse.class);
//            String message = response.map(r -> r.getError().getDescription()).orElse(e.getMessage());
//
//            logger.error(Log.Builder.of()
//                .setHeader("Erro durante o envio do documento")
//                .addRow("process", processId)
//                .addRow("status", e.getStatus())
//                .addRow("request", request)
//                .addRow("response", response.orElse(null))
//                .addRow("message", message)
//                .build(),
//                e
//            );
//
//            throw new ProcessException("Erro ao enviar o documento" + message, e, response);
//        }
//    }
//
//    @Override
//    public String executeProcess(String processId) {
//        try {
//            ExecuteProcessResponse response = consult(a -> processClient.executeProcess(a, processId));
//
//            logger.info(Log.Builder.of()
//                .setHeader("Processo executado")
//                .addRow("process", processId)
//                .addRow("response", response)
//                .build()
//            );
//
//            return response.getId();
//        } catch (HttpClientResponseException e) {
//            Optional<ErrorProcessResponse> response = e.getResponse().getBody(ErrorProcessResponse.class);
//
//            String message = response.map(r -> r.getError().getDescription()).orElse(e.getMessage());
//
//            Log.Builder logBuilder = Log.Builder.of()
//                .addRow("process", processId)
//                .addRow("status", e.getStatus())
//                .addRow("response", response)
//                .addRow("message", message);
//
//            if (response.isPresent() && response.get().getError().getCode().equals(50005)) {
//                logger.info(logBuilder.setHeader("Processo não executado").build());
//                return processId;
//            }
//
//            logger.error(logBuilder.setHeader("Erro durante a execução do processo").build(), e);
//
//            throw new ProcessException("Erro ao executar o processo - " + message, e, response);
//        }
//    }
//
//    @Override
//    public GetProcessResponse getProcess(String processId) {
//        try {
//            GetProcessResponse response = consult(a -> processClient.getProcess(a, processId));
//
//            logger.info(Log.Builder.of()
//                .setHeader("Resultado do Processo")
//                .addRow("process", processId)
//                .addRow("response", response)
//                .build()
//            );
//
//            return response;
//        } catch (HttpClientResponseException e) {
//            Optional<ErrorProcessResponse> response = e.getResponse().getBody(ErrorProcessResponse.class);
//            String message = response.map(r -> r.getError().getDescription()).orElse(e.getMessage());
//
//            logger.error(Log.Builder.of()
//                .setHeader("Erro durante a recuperação do processo")
//                .addRow("process", processId)
//                .addRow("status", e.getStatus())
//                .addRow("response", response.orElse(null))
//                .addRow("message", message)
//                .build(),
//                e
//            );
//
//            throw new ProcessException("Erro ao recuperar o processo - " + message, e, response);
//        }
//    }
//
//    private <T> T consult(ConsultCaller<T> caller) {
//        String authorization = authService.getAuthorization();
//
//        try {
//            return caller.call(authorization);
//        } catch (HttpClientResponseException e) {
//            if (e.getStatus().equals(HttpStatus.FORBIDDEN)) {
//                authorization = authService.genNewToken(authorization);
//                return caller.call(authorization);
//            }
//
//            throw e;
//        }
//    }
//
}
