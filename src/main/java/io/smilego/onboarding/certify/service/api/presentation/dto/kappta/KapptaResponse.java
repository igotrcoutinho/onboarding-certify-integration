package io.smilego.onboarding.certify.service.api.presentation.dto.kappta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Order;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.domain.resultado.Resultado;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;

public class KapptaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long etapaId;
    private Resultado resultado;
    private List<Property> propriedades;
    private String observacao;

    public KapptaResponse() { }

    public KapptaResponse(Long etapaId, Resultado resultado, List<Property> propriedades, String observacao) {
        this.etapaId = etapaId;
        this.resultado = resultado;
        this.propriedades = propriedades;
        this.observacao = observacao;
    }

    public static KapptaResponse of(Long etapaId, Resultado resultado, List<Property> propriedades, String observacao) {
        return new KapptaResponse(etapaId, resultado, propriedades, observacao);
    }

    public Long getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Long etapaId) {
        this.etapaId = etapaId;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public List<Property> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(List<Property> propriedades) {
        this.propriedades = propriedades;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void adicionarRespostaBiometria(String processoId, boolean documentoTipificado) {
        if (Objects.isNull(propriedades)) {
            propriedades = new ArrayList<>();
        }
        propriedades.add(new Property(
                IdBiometria.CHAVE_CAMPO_BIOMETRIA_CONSULTA,
                StringUtils.writeValueAsString(IdBiometria.of(processoId, documentoTipificado)),
                Order.INIT_ORDER.getStringValue()
        ));
    }

    @Override
    public String toString() {
        return "etapaId=" + etapaId + ", resultado=" + resultado + ", propriedades=[" + propriedades
                + "], observacao=" + observacao;
    }

}
