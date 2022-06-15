package io.smilego.onboarding.certify.service.api.infrastructure.client;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.detail.DetailResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveResponse;

import javax.validation.constraints.NotBlank;

@Header(name = "Ocp-Apim-Subscription-Key", value = "${certify.authentication.apiKey}")
@Client(value = "${certify.process.url}")
public interface CertifyCertificationClient {

    @Post("/certification/certification-request/v1/receive")
    @Header(name = "Content-Type", value = "application/json")
    public ReceiveResponse receive(@Body ReceiveRequest request);

    @Get("/certification/certification-request/v1/details?id={id}")
    public DetailResponse details(@NotBlank String id);

}
