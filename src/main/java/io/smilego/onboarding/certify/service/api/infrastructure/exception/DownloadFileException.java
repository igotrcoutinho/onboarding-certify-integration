package io.smilego.onboarding.certify.service.api.infrastructure.exception;

public class DownloadFileException extends RuntimeException {

    public DownloadFileException() {
    }

    public DownloadFileException(String message) {
        super(message);
    }

    public DownloadFileException(Throwable cause) {
        super(cause);
    }

    public DownloadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadFileException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
