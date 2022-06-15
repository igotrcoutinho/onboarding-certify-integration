package io.smilego.onboarding.certify.service.api.domain.properties.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {

    @JsonProperty("campoCodigo")
    private final String key;

    @JsonProperty("ordem")
    private final String order;

    @JsonProperty("valor")
    private final String value;

    @JsonCreator
    public Property(@JsonProperty("campoCodigo") String key,
                    @JsonProperty("valor") String value,
                    @JsonProperty("ordem") String order) {

        this.key = key;
        this.value = value;
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public String getOrder() {
        return order;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Property{" +
                "codigo='" + key + '\'' +
                ", valor='" + value + '\'' +
                ", ordem='" + order + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return (key + order).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (Objects.isNull(object) || object.getClass() != this.getClass()) {
            return false;
        }

        final Property property = (Property) object;

        return Objects.equals(key, property.getKey()) &&
               Objects.equals(order, property.getOrder()) &&
               Objects.equals(value, property.getValue());
    }

}
