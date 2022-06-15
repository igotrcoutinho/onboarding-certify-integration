package io.smilego.onboarding.certify.service.api.domain.properties.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Properties {

    private final Map<String, Property> values;

    private Properties(Collection<Property> collection) {
        this.values = collection.stream()
                                .collect(toMap(this::generateHashKey, p -> p));
    }

    public static Properties create(Collection<Property> collection) {
        return new Properties(collection);
    }

    private String generateHashKey(Property property) {
        return generateHashKey(property.getKey(), property.getOrder());
    }

    private String generateHashKey(String key, String order) {
        return key.concat("{").concat(order).concat("}");
    }

    public Property findValue(String key, Order order) {
        return values.get(generateHashKey(key, order.getStringValue()));
    }

    public List<Property> asList() {
        return values.values()
                     .stream()
                     .collect(Collectors.toList());
    }

}
