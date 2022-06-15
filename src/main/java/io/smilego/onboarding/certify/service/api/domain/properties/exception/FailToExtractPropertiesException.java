package io.smilego.onboarding.certify.service.api.domain.properties.exception;

public class FailToExtractPropertiesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public FailToExtractPropertiesException(Exception cause) {
        super(cause);
    }

}