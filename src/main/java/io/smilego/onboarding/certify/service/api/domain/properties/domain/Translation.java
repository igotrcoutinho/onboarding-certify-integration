package io.smilego.onboarding.certify.service.api.domain.properties.domain;

import java.util.Map;

public interface Translation {

    Map<Object, Object> getMap();

    default Object getDefault() {
        return null;
    }

}
