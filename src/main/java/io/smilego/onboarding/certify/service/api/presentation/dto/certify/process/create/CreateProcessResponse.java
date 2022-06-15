package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.create;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreateProcessResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }

}
