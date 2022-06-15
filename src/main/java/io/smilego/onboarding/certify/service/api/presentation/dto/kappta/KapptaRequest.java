package io.smilego.onboarding.certify.service.api.presentation.dto.kappta;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KapptaRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("etapa")
    private Long etapaId;
    private List<Property> propriedades;
    private List<Parametro> parametros;

    public Long getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Long etapaId) {
        this.etapaId = etapaId;
    }

    public List<Property> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(List<Property> propriedades) {
        this.propriedades = propriedades;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return "etapaId=" + etapaId + ", propriedades=[" + propriedades + "]";
    }

}
