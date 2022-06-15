package io.smilego.onboarding.certify.service.api.infrastructure.exception;

public class ArchitetureException extends RuntimeException {

    private static final long serialVersionUID = 3472126337960620537L;

    public ArchitetureException() {
    }

    public ArchitetureException(String message) {
        super(message);
    }

    public ArchitetureException(Throwable cause) {
        super(cause);
    }

    public ArchitetureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchitetureException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
