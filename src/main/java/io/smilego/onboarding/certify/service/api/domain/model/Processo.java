package io.smilego.onboarding.certify.service.api.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.Parametro;

import static java.util.stream.Collectors.toMap;

public class Processo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Long etapaId;
    private List<String> erros;
    private List<Property> propriedades;
    private List<Parametro> parametros;
    private boolean executado;
    private boolean documentosTipificado;
    private Integer tentativasConsulta;

    @JsonProperty("ocr")
    private Set<Property> propriedadesOcr;

    public Processo() { }

    public Processo(Long etapaId, String id, List<String> erros, List<Property> propriedades, List<Parametro> parametros) {
        this.etapaId = etapaId;
        this.id = id;
        this.erros = erros;
        this.propriedades = propriedades;
        this.propriedadesOcr = null;
        this.executado = false;
        this.parametros = parametros;
        this.tentativasConsulta = 0;
    }

    public static Processo of(Long etapaId, List<Property> propriedades, List<Parametro> parametros) {
        return new Processo(etapaId, null, null, propriedades, parametros);
    }

    public static Processo of(Long etapaId, String id, List<String> erros, List<Property> propriedades, List<Parametro> parametros) {
        return new Processo(etapaId, id, erros, propriedades, parametros);
    }

    public Long getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Long etapaId) {
        this.etapaId = etapaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getErros() {
        if (Objects.isNull(erros))
            erros = new ArrayList<>();
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public void addErro(String message) {
        getErros().add(message);
    }

    public int getErrosCount() {
        return getErros().size();
    }

    public List<Property> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(List<Property> propriedades) {
        this.propriedades = propriedades;
    }

    public List<Parametro> getParametros() {
        return Objects.nonNull(parametros) ? parametros : Collections.emptyList();
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public Set<Property> getPropriedadesOcr() {
        return Objects.nonNull(propriedadesOcr) ? propriedadesOcr : Collections.emptySet();
    }

    public void setPropriedadesOcr(Set<Property> propriedadesOcr) {
        this.propriedadesOcr = propriedadesOcr;
    }

    public boolean isExecutado() {
        return executado;
    }

    public void setExecutado(boolean executado) {
        this.executado = executado;
    }

    public boolean isDocumentosTipificado() {
        return documentosTipificado;
    }

    public void setDocumentosTipificado(boolean documentosTipificado) {
        this.documentosTipificado = documentosTipificado;
    }

    public Integer getTentativasConsulta() {
        return tentativasConsulta;
    }

    public void setTentativasConsulta(Integer tentativasConsulta) {
        this.tentativasConsulta = tentativasConsulta;
    }

    public Integer incrementarTentativasConsulta() {
        return tentativasConsulta++;
    }

    public boolean isDocumentosInseridos() {
        return Objects.nonNull(propriedadesOcr);
    }

    public Map<String, Parametro> getParametroList() {
        return getParametros().stream().collect(toMap(p -> p.getCodigo(), p -> p));
    }

    public Parametro getParametro(String codigo) {
        return getParametros().stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    public boolean isEtapaTentativaUnica() {
        Parametro parametro = getParametro(Parametro.CODIGO_PARAMETRO_TENTATIVA_UNICA);
        return Objects.nonNull(parametro) && Boolean.parseBoolean(parametro.getValor());
    }

    @Override
    public String toString() {
        return "etapaId=" + etapaId + ", id=" + id + ", executado=" + executado +
            ", erros=[" + erros + "]" + ", propriedades=[" + propriedades + "], propriedadesOcr=[" + propriedadesOcr + "]";
    }

}
