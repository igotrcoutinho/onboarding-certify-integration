package io.smilego.onboarding.certify.service.api.domain.service;

import io.smilego.onboarding.certify.service.api.domain.model.Processo;

import java.util.List;

public interface ConsultaService {

    public void iniciarProcesso(Processo processo, Boolean isOnlySelfie);
    public void enviarDocumentos(Processo processo, List<?> documentos);
    public void executarProcesso(Processo processo);
    public void verificarProcesso(Processo processo);

}
