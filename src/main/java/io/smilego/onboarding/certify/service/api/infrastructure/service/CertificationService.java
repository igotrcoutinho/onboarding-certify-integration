package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.detail.DetailResponse;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.certification.receive.ReceiveRequest;

public interface CertificationService {

    public String receive(ReceiveRequest request);
    public DetailResponse getById(String id);

}
