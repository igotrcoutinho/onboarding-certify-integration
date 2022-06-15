package io.smilego.onboarding.certify.service.api.domain.properties.exception;

public class FailToLoadPropertiesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FailToLoadPropertiesException(Exception cause) {
		super(cause);
	}

}