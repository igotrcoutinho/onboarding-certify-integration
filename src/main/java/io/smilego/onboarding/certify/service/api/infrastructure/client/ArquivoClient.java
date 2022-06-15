package io.smilego.onboarding.certify.service.api.infrastructure.client;

import io.micronaut.discovery.exceptions.NoAvailableServiceException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.ArquivoResponse;

import javax.validation.constraints.NotBlank;

@Client(id = "${kappta-services.arquivo}")
public interface ArquivoClient {

    @Get("/arquivos/{id}/baixar")
    HttpResponse<byte[]> download(@Header String authorization, @NotBlank Integer id) throws HttpClientException, HttpClientResponseException, NoAvailableServiceException;

    @Get("/arquivos/{id}/baixar/base64")
    HttpResponse<ArquivoResponse> downloadAsBase64(@Header String authorization, @NotBlank Integer id) throws HttpClientException, HttpClientResponseException, NoAvailableServiceException;

}
