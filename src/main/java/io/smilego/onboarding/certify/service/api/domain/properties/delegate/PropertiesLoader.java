package io.smilego.onboarding.certify.service.api.domain.properties.delegate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Alias;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Ignore;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Translate;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Properties;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Translation;
import io.smilego.onboarding.certify.service.api.domain.properties.exception.FailToLoadPropertiesException;
import io.smilego.onboarding.certify.service.api.domain.properties.exception.ObjectNotFilledException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Order;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.domain.properties.functional.PropertyConverter;
import io.smilego.onboarding.certify.service.api.domain.properties.util.ReflectionUtils;
import io.smilego.onboarding.certify.service.api.domain.properties.util.ReflectionUtils.FinalType;

public class PropertiesLoader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    public static PropertiesLoader create() {
        return new PropertiesLoader();
    }

    public <T> T load(T object, Properties properties, String keyPrefix, Order order, PropertyConverter converter) {
        try {
            return process(object, properties, keyPrefix, order, converter);

        } catch (ObjectNotFilledException e) {
            return object;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new FailToLoadPropertiesException(e);

        }
    }

    private <T> T process(T object, Properties properties, String keyPrefix, Order order, PropertyConverter converter)
            throws IllegalAccessException, InstantiationException {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean filled = false;

        String updatedKeyPrefix = getUpdatedKeyPrefix(keyPrefix, object);

        for (Field f : fields) {

            if (this.isIgnored(f))
                continue;

            f.setAccessible(true);
            String updatedKey = getUpdatedKey(updatedKeyPrefix, f);

            Object propertyValue = f.get(object);
            if (Objects.nonNull(propertyValue))
                continue;

            if (ReflectionUtils.isFinalType(f)) {
                FinalType finalType = FinalType.getFindType(f.getType());
                propertyValue = this.getSimplePropertyValue(finalType, properties, updatedKey, order);

            } else if (List.class.isAssignableFrom(f.getType())) {
                propertyValue = this.getListPropertyValue(f, properties, updatedKey, order, converter);

            } else {

                try {
                    propertyValue = this.getComplexPropertyValue(f.getType(), properties, updatedKey, order, converter);
                } catch (ObjectNotFilledException e) {
                    propertyValue = null;
                }

            }

            if (Objects.nonNull(propertyValue)) {
                propertyValue = translate(f, propertyValue);

                if (Objects.nonNull(converter)) {
                    propertyValue = converter.convert(updatedKey, propertyValue);
                }

                f.set(object, propertyValue);
                filled = true;
            }
        }

        if (!filled)
            throw new ObjectNotFilledException();

        return object;
    }

    @SuppressWarnings("deprecation")
    private Object translate(Field f, Object propertyValue) {
        Translation translation = null;
        if (f.isAnnotationPresent(Translate.class)) {
            try {
                translation = f.getAnnotation(Translate.class).value().newInstance();
                propertyValue = translation.getMap().get(propertyValue);
                propertyValue = Objects.isNull(propertyValue) ? translation.getDefault() : propertyValue;
                translation = null;
            } catch (NullPointerException | InstantiationException | IllegalAccessException e) {}
        }
        return propertyValue;
    }

    private boolean isIgnored(Field f) {
        return f.isAnnotationPresent(Ignore.class);
    }

    private Object getSimplePropertyValue(FinalType finalType, Properties properties, String key, Order order) {
        order = this.getOrderOrDefault(order);
        Property property = properties.findValue(key, order);

        if (Objects.nonNull(property))
            return finalType.formatAsObject(property.getValue());

        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <A, V, R> Object getComplexPropertyValue(Class<?> type, Properties properties, String key, Order order, PropertyConverter converter)
            throws InstantiationException, IllegalAccessException {

        if (Enum.class.isAssignableFrom(type))
            return getEnumPropertyValue((Class<Enum>) type, properties, key, order, converter);

        Object objectProperty = ReflectionUtils.getInstance(type);
        return this.process(objectProperty, properties, key, order, converter);
    }

    private <T extends Enum<T>> T getEnumPropertyValue(Class<T> type, Properties properties, String key, Order order, PropertyConverter converter) {
        order = this.getOrderOrDefault(order);
        Property property = properties.findValue(key, order);

        if (Objects.nonNull(property)) {
            try {
                return Enum.valueOf(type, property.getValue().toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid enum value for the property:" + property, e);
            }
        }
        return null;
    }

    private Order getOrderOrDefault(Order order) {
        return Objects.isNull(order) ? Order.INIT_ORDER : order;
    }

    private <A, V, R> List<?> getListPropertyValue(Field field, Properties properties, String key, Order order, PropertyConverter converter)
            throws InstantiationException, IllegalAccessException {

        order = Objects.isNull(order) ? Order.INIT_ORDER : order.next();

        Class<?> listType = ReflectionUtils.getParametizedClass(field);
        List<Object> list = new ArrayList<>();
        boolean hasNextValue = true;
        do {
            Object objectProperty = this.getListSinglePropertyValue(listType, properties, key, order, converter);

            if (Objects.nonNull(objectProperty)) {
                list.add(objectProperty);
                order = order.incrementValue();
                continue;
            }

            hasNextValue = false;
        } while (hasNextValue);

        return list.isEmpty() ? null : list;
    }

    private <A, V, R> Object getListSinglePropertyValue(Class<?> listType, Properties properties, String key, Order order, PropertyConverter converter)
            throws InstantiationException, IllegalAccessException {

        boolean isSimpleProperty = ReflectionUtils.isFinalType(listType);

        if (!isSimpleProperty) {
            try {
                Object objectProperty = ReflectionUtils.getInstance(listType);
                return this.process(objectProperty, properties, key, order, converter);

            } catch (ObjectNotFilledException e) {
                return null;
            }
        }

        FinalType finalType = FinalType.getFindType(listType);
        return this.getSimplePropertyValue(finalType, properties, key, order);
    }

    private String getUpdatedKeyPrefix(String keyPrefix, Object object) {
        if (Objects.nonNull(keyPrefix))
            return keyPrefix;

        Alias alias = object.getClass().getAnnotation(Alias.class);
        if (Objects.nonNull(alias))
            return alias.value();

        return StringUtils.uncapitalize(object.getClass().getSimpleName());
    }

    private String getUpdatedKey(String keyPrefix, Field field) {
        if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix.concat(".");

        Alias alias = field.getAnnotation(Alias.class);
        if (Objects.nonNull(alias)) {

            if (alias.root())
                return alias.value();

            return keyPrefix.concat(alias.value());
        }

        return keyPrefix.concat(field.getName());
    }

}
