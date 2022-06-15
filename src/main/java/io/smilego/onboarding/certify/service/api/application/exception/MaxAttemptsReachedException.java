package io.smilego.onboarding.certify.service.api.application.exception;

import io.smilego.onboarding.certify.service.api.infrastructure.exception.ArchitetureException;

public class MaxAttemptsReachedException extends ArchitetureException {

    public MaxAttemptsReachedException() {
    }

    public MaxAttemptsReachedException(String message) {
        super(message);
    }

    public MaxAttemptsReachedException(Throwable cause) {
        super(cause);
    }

    public MaxAttemptsReachedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaxAttemptsReachedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
