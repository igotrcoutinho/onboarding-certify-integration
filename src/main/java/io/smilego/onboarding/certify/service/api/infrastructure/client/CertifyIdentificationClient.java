package io.smilego.onboarding.certify.service.api.infrastructure.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.identification.ScoreResponse;

import javax.validation.constraints.NotBlank;

@Header(name = "Ocp-Apim-Subscription-Key", value = "${certify.authentication.apiKey}")
@Client(value = "${certify.process.url}")
public interface CertifyIdentificationClient {

    @Get("/identification/score/v1?cpf={cpf}&certificationRequestId={certificationRequestId}")
    public ScoreResponse getScore(@NotBlank String cpf, @NotBlank String certificationRequestId);

}
