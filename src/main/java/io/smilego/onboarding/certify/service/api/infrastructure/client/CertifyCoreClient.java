package io.smilego.onboarding.certify.service.api.infrastructure.client;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.core.IcaoRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.core.IcaoResponse;

import javax.validation.constraints.NotBlank;

@Header(name = "Ocp-Apim-Subscription-Key", value = "${certify.authentication.apiKey}")
@Client(value = "${certify.process.url}")
public interface CertifyCoreClient {

    @Post("/image/v1/check-icao")
    @Header(name = "Content-Type", value = "application/json")
    public IcaoResponse checkIcao(@Body IcaoRequest request);

}
