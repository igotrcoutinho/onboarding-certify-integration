package io.smilego.onboarding.certify.service.api.infrastructure.service;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;

import java.util.List;

public interface DocumentService {

    public List<DocumentInsertRequest> convert(List<Property> properties);

}
