package io.smilego.onboarding.certify.service.api.application.service;

import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.smilego.onboarding.certify.service.api.application.exception.ReceiveException;
import io.smilego.onboarding.certify.service.api.infrastructure.client.CertifyCertificationClient;
import io.smilego.onboarding.certify.service.api.infrastructure.log.ApplicationLogger;
import io.smilego.onboarding.certify.service.api.infrastructure.log.Log;
import io.smilego.onboarding.certify.service.api.infrastructure.service.CertificationService;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.detail.DetailResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.error.ErrorReceiveResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class CertificationServiceImpl implements CertificationService {

    @Inject
    private CertifyCertificationClient certifyCertificationClient;

    @Inject
    private ApplicationLogger logger;

    public String receive(ReceiveRequest request) {

        try {
            ReceiveResponse response = certifyCertificationClient.receive(request);
            logger.info(Log.Builder.of()
                    .setHeader("Receive created")
                    .addRow("request", request)
                    .addRow("response", response)
                    .build()
            );
            return response.getId();
        } catch (HttpClientResponseException e) {
            Optional<ErrorReceiveResponse> response = e.getResponse().getBody(ErrorReceiveResponse.class);
            String message = response.map(r -> r.getErrors()).orElse(e.getMessage());

            logger.error(Log.Builder.of()
                            .setHeader("Error creating receive")
                            .addRow("status", e.getStatus())
                            .addRow("request", request)
                            .addRow("response", response.orElse(null))
                            .addRow("message", message)
                            .build(),
                    e
            );

            throw new ReceiveException("Erro ao criar processo - " + message, e, response);
        }

    }

    @Override
    public DetailResponse getById(String id) {

        try {
            DetailResponse response = certifyCertificationClient.details(id);
            logger.info(Log.Builder.of()
                    .setHeader("receive detail")
                    .addRow("id", id)
                    .addRow("response", response)
                    .build()
            );
            return response;
        } catch (HttpClientResponseException e) {
            Optional<ErrorReceiveResponse> response = e.getResponse().getBody(ErrorReceiveResponse.class);
            String message = response.map(r -> r.getErrors()).orElse(e.getMessage());

            logger.error(Log.Builder.of()
                            .setHeader("Error creating receive")
                            .addRow("status", e.getStatus())
                            .addRow("id", id)
                            .addRow("response", response.orElse(null))
                            .addRow("message", message)
                            .build(),
                    e
            );

            throw new ReceiveException("Error finding receive detail - " + message, e, response);
        }

    }

}
