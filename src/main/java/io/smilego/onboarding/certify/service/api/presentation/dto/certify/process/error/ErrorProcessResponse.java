package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorProcessResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Error")
    private UnicoErrorResponse error;

    public UnicoErrorResponse getError() {
        return error;
    }

    public void setError(UnicoErrorResponse error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "error=[" + error + "]";
    }

    public static class UnicoErrorResponse {

        @JsonProperty("Code")
        private Integer code;

        @JsonProperty("Description")
        private String description;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "code=" + code + ", description=" + description;
        }

    }

}
