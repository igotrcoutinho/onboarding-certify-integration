package io.smilego.onboarding.certify.service.api.infrastructure.exception;

public class RequestFormatException extends RuntimeException {

    private static final long serialVersionUID = -8449419242239421799L;

    public RequestFormatException(String message) {
        super(message);
    }

}