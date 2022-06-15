package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.smilego.onboarding.certify.service.api.infrastructure.exception.NetworkException;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.RequestException;
import io.smilego.onboarding.certify.service.api.infrastructure.exception.RequestFormatException;

import java.util.Optional;

public interface FileService {

    Optional<byte[]> download(Integer id) throws NetworkException, RequestException, RequestFormatException;
    Optional<String> downloadAsBase64(Integer id) throws NetworkException, RequestException, RequestFormatException;

}