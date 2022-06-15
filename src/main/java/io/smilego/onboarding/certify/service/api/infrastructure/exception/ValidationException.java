package io.smilego.onboarding.certify.service.api.infrastructure.exception;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 7132676114092204714L;

    public ValidationException(String message) {
        super(message);
    }

}