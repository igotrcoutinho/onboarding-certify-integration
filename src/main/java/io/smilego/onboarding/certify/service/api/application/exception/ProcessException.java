package io.smilego.onboarding.certify.service.api.application.exception;

import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.error.ErrorProcessResponse;

import java.util.Optional;

public class ProcessException extends RuntimeException {

    private Optional<ErrorProcessResponse> response;

    public ProcessException() {
    }

    public ProcessException(String message, Optional<ErrorProcessResponse> response) {
        super(message);
        this.response = response;
    }

    public ProcessException(Throwable cause, Optional<ErrorProcessResponse> response) {
        super(cause);
        this.response = response;
    }

    public ProcessException(String message, Throwable cause, Optional<ErrorProcessResponse> response) {
        super(message, cause);
        this.response = response;
    }

    public ProcessException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace, Optional<ErrorProcessResponse> response) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.response = response;
    }

    public Optional<ErrorProcessResponse> getResponse() {
        return response;
    }

    public void setResponse(Optional<ErrorProcessResponse> response) {
        this.response = response;
    }

}
