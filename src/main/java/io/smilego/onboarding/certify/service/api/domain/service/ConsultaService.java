package io.smilego.onboarding.certify.service.api.domain.service;

import io.smilego.onboarding.certify.service.api.domain.model.Processo;
import io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.document.DocumentInsertRequest;

import java.util.List;

public interface ConsultaService {

    public void iniciarProcesso(Processo processo, List<DocumentInsertRequest> documentos);
    public void executarProcesso(Processo processo);
    public void verificarProcesso(Processo processo);

}
