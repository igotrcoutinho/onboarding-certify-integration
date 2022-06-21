package io.smilego.onboarding.certify.service.api.application.exception;

import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.error.ErrorReceiveResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.error.ErrorProcessResponse;

import java.util.Optional;

public class ProcessException extends RuntimeException {

    private Optional<ErrorReceiveResponse> response;

    public ProcessException() {
    }

    public ProcessException(String message, Optional<ErrorReceiveResponse> response) {
        super(message);
        this.response = response;
    }

    public ProcessException(Throwable cause, Optional<ErrorReceiveResponse> response) {
        super(cause);
        this.response = response;
    }

    public ProcessException(String message, Throwable cause, Optional<ErrorReceiveResponse> response) {
        super(message, cause);
        this.response = response;
    }

    public ProcessException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace, Optional<ErrorReceiveResponse> response) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.response = response;
    }

    public Optional<ErrorReceiveResponse> getResponse() {
        return response;
    }

    public void setResponse(Optional<ErrorReceiveResponse> response) {
        this.response = response;
    }

}
