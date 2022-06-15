package io.smilego.onboarding.certify.service.api.application.exception;

import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.error.ErrorReceiveResponse;

import java.util.Optional;

public class ReceiveException extends RuntimeException {

    private Optional<ErrorReceiveResponse> response;

    public ReceiveException() {
    }

    public ReceiveException(String message, Optional<ErrorReceiveResponse> response) {
        super(message);
        this.response = response;
    }

    public ReceiveException(Throwable cause, Optional<ErrorReceiveResponse> response) {
        super(cause);
        this.response = response;
    }

    public ReceiveException(String message, Throwable cause, Optional<ErrorReceiveResponse> response) {
        super(message, cause);
        this.response = response;
    }

    public ReceiveException(String message, Throwable cause, boolean enableSuppression,
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
