package io.smilego.onboarding.certify.service.api.domain.properties;

import io.smilego.onboarding.certify.service.api.domain.properties.delegate.PropertiesExtractor;
import io.smilego.onboarding.certify.service.api.domain.properties.delegate.PropertiesLoader;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Properties;
import io.smilego.onboarding.certify.service.api.domain.properties.functional.PropertyConverter;
import io.smilego.onboarding.certify.service.api.domain.properties.util.GenericsUtils;
import jakarta.inject.Singleton;

@Singleton
public class PropertiesHandlerImpl implements PropertiesHandler {

    public PropertiesHandlerImpl() {

    }

    @Override
    public <T> T load(Class<T> type, Properties properties) {
        try {
            T object = GenericsUtils.getInstance(type);
            return load(object, properties);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
    }

    @Override
    public <T> T load(Class<T> type, Properties properties, PropertyConverter converter) {
        try {
            T object = GenericsUtils.getInstance(type);
            PropertiesLoader loader = PropertiesLoader.create();
            return loader.load(object, properties, null, null, converter);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
    }

    @Override
    public <T> T load(T object, Properties properties) {
        PropertiesLoader loader = PropertiesLoader.create();
        return loader.load(object, properties, null, null, null);
    }

    @Override
    public <T> T load(T object, Properties properties, String keyPrefix) {
        PropertiesLoader loader = PropertiesLoader.create();
        return loader.load(object, properties, keyPrefix, null, null);
    }

    @Override
    public Properties extract(Object object) {
        PropertiesExtractor extractor = PropertiesExtractor.create();
        return extractor.extract(object);
    }
}