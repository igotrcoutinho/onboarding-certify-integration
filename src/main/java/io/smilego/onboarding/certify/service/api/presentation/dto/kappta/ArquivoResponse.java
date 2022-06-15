package io.smilego.onboarding.certify.service.api.presentation.dto.kappta;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArquivoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "data=" + data;
    }

}
