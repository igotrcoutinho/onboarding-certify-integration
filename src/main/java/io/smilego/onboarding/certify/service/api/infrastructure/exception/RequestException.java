package io.smilego.onboarding.certify.service.api.infrastructure.exception;

public class RequestException extends RuntimeException {

    private static final long serialVersionUID = -919799394363266664L;

    public RequestException(String message) {
        super(message);
    }

}