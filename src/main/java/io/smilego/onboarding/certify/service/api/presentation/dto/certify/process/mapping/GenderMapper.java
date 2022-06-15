package io.smilego.onboarding.certify.service.api.presentation.dto.certify.process.mapping;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Translation;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenderMapper implements Translation {

    private final Map<Object, Object> map = Stream.of(new Object[][] {
        { "MASCULINO", "M" },
        { "FEMININO", "F" }
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    @Override
    public Map<Object, Object> getMap() {
        return this.map;
    }

}
