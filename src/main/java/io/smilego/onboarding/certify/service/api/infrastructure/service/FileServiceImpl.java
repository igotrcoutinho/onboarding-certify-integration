package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.micronaut.discovery.exceptions.NoAvailableServiceException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.smilego.onboarding.certify.service.api.application.configuration.property.ApplicationProperties;
import io.smilego.onboarding.certify.service.api.infrastructure.client.ArquivoClient;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.NetworkException;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.RequestException;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.RequestFormatException;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.ArquivoResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class FileServiceImpl implements FileService {

    @Inject
    private ArquivoClient arquivoClient;

    @Inject
    private ApplicationProperties applicationProperties;

    private static final String HEADER_BASE64 = "data:image/jpeg;base64,";

    @Override
    public Optional<byte[]> download(Integer id) throws NetworkException, RequestException, RequestFormatException {
        try {
            final String systemToken = applicationProperties.getSecurity().getUserSystemToken();
            HttpResponse<byte[]> response = arquivoClient.download(systemToken, id);
            return response.status() == HttpStatus.OK ? Optional.of(response.body()) : Optional.empty();
        } catch (HttpClientResponseException e) {
            Integer status = e.getStatus().getCode();

            if (status >= HttpStatus.BAD_REQUEST.getCode() && status < HttpStatus.INTERNAL_SERVER_ERROR.getCode()) {
                throw new RequestFormatException(e.getMessage());
            }

            throw new RequestException(e.getMessage());
        } catch (HttpClientException | NoAvailableServiceException e) {
            throw new NetworkException(e.getMessage());
        }
    }

    @Override
    public Optional<String> downloadAsBase64(Integer id) throws NetworkException, RequestException, RequestFormatException {
        try {
            final String systemToken = applicationProperties.getSecurity().getUserSystemToken();
            HttpResponse<ArquivoResponse> response = arquivoClient.downloadAsBase64(systemToken, id);
            return response.status() == HttpStatus.OK ? Optional.of(HEADER_BASE64 + response.body().getData()) : Optional.empty();
        } catch (HttpClientResponseException e) {
            Integer status = e.getStatus().getCode();

            if (status >= HttpStatus.BAD_REQUEST.getCode() && status < HttpStatus.INTERNAL_SERVER_ERROR.getCode()) {
                throw new RequestFormatException(e.getMessage());
            }

            throw new RequestException(e.getMessage());
        } catch (HttpClientException | NoAvailableServiceException e) {
            throw new NetworkException(e.getMessage());
        }
    }

}