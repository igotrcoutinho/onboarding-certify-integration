package io.smilego.onboarding.certify.service.api.domain.properties;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Properties;
import io.smilego.onboarding.certify.service.api.domain.properties.functional.PropertyConverter;

public interface PropertiesHandler {

    <T> T load(Class<T> type, Properties properties);

    <T> T load(Class<T> type, Properties properties, PropertyConverter converter);

    <T> T load(T object, Properties properties);

    <T> T load(T object, Properties properties, String keyPrefix);

    Properties extract(Object object);
}
