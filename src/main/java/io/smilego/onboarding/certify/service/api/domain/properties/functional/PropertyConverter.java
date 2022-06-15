package io.smilego.onboarding.certify.service.api.domain.properties.functional;

@FunctionalInterface
public interface PropertyConverter {
    public Object convert(String alias, Object value);
}