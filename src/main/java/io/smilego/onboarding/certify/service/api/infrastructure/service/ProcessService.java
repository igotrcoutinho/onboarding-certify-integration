package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create.CreateProcessRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.get.GetProcessResponse;

public interface ProcessService {

    public String createProcess(CreateProcessRequest request);
    public DocumentInsertResponse sendDocument(String processId, DocumentInsertRequest request);
    public String executeProcess(String processId);
    public GetProcessResponse getProcess(String processId);

}
