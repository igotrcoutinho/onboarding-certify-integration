package io.smilego.onboarding.certify.service.api.presentation.dto.certify.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.smilego.onboarding.certify.service.api.infrastructure.util.StringUtils;

import java.io.Serializable;

public class IcaoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @JsonProperty("Check")
    private Check check;

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public static class Check implements Serializable {

        private static final long serialVersionUID = 1L;

        @NonNull
        @JsonProperty("Image")
        private String image;

        @Override
        public String toString() {
            return "Image=" + StringUtils.truncate(image, 100);
        }

    }

}
