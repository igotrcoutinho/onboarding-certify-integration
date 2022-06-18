package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create.CreateProcessRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.get.GetProcessResponse;

public interface ProcessService {

    public String createProcess(ReceiveRequest request);
//    public DocumentInsertResponse sendDocument(String processId, DocumentInsertRequest request);
//    public String executeProcess(String processId);
//    public GetProcessResponse getProcess(String processId);

}
