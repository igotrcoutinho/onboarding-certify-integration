package io.smilego.onboarding.certify.service.api.infrastructure.handler.error;

import io.smilego.onboarding.certify.service.api.domain.model.Processo;

public interface ErrorHandler {

    public void handlerError(Processo processo, Exception exception);

}
