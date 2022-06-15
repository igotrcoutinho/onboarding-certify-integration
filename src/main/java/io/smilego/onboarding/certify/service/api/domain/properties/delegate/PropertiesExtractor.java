package io.smilego.onboarding.certify.service.api.domain.properties.delegate;

import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Alias;
import io.smilego.onboarding.certify.service.api.domain.properties.annotation.Ignore;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Properties;
import io.smilego.onboarding.certify.service.api.domain.properties.exception.FailToExtractPropertiesException;
import org.apache.commons.lang3.StringUtils;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Order;
import io.smilego.onboarding.certify.service.api.domain.properties.domain.Property;
import io.smilego.onboarding.certify.service.api.domain.properties.util.GenericsUtils;
import io.smilego.onboarding.certify.service.api.domain.properties.util.ReflectionUtils.FinalType;

import static io.smilego.onboarding.certify.service.api.domain.properties.util.ReflectionUtils.isFinalType;
import static io.smilego.onboarding.certify.service.api.domain.properties.util.ReflectionUtils.FinalType.getFindType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PropertiesExtractor {

    public static PropertiesExtractor create() {
        return new PropertiesExtractor();
    }

    public Properties extract(Object object) {
        try {
            List<Property> propertyList = process(object, new ArrayList<>(), null, null);
            return Properties.create(propertyList);

        } catch (IllegalAccessException e) {
           throw new FailToExtractPropertiesException(e);
        }
    }

    private List<Property> process(Object object, List<Property> propertyList, String keyPrefix, Order order) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();

        String updatedKeyPrefix = getUpdatedKeyPrefix(keyPrefix, object);

        for (Field f : fields) {

            if(this.isIgnored(f))
                continue;

            f.setAccessible(true);
            String updatedKey = getUpdatedKey(updatedKeyPrefix, f);

            Object propertyValue = f.get(object);
            if (Objects.isNull(propertyValue))
                continue;

            if (isFinalType(f)) {
                FinalType finalType = getFindType(f.getType());
                propertyList = this.processSimpleObject(propertyList, finalType, propertyValue, updatedKey, order);

            } else if (List.class.isAssignableFrom(f.getType())) {
                propertyList = this.processList(f, (List<?>) propertyValue, propertyList, updatedKey, order);

            } else {
                propertyList = this.processComplexObject(propertyValue, propertyList, updatedKey, order);
            }
        }

        return propertyList;
    }

    private boolean isIgnored(Field f) {
        return f.isAnnotationPresent(Ignore.class);
    }

    private List<Property> processSimpleObject(List<Property> propertyList, FinalType finalType, Object valueProperty, String key, Order order) {
        order = Objects.isNull(order) ? Order.INIT_ORDER : order;
        String value = finalType.formatAsString(valueProperty);
        propertyList.add(new Property(key, value, order.getStringValue()));
        return propertyList;
    }

    private List<Property> processComplexObject(Object object, List<Property> propertyList, String key, Order order)
            throws  IllegalAccessException {

        if (Enum.class.isAssignableFrom(object.getClass()))
            return processEnumObject((Enum<?>) object, propertyList, key, order);

        return this.process(object, propertyList, key, order);
    }

    private List<Property> processEnumObject(Enum<?> object, List<Property> propertyList, String key, Order order) {
        order = Objects.isNull(order) ? Order.INIT_ORDER : order;
        propertyList.add(new Property(key, object.name(), order.getStringValue()));
        return propertyList;
    }

    private List<Property> processList(Field field, List<?> list, List<Property> propertyList, String key, Order order) throws IllegalAccessException {

        order = Objects.isNull(order) ? Order.INIT_ORDER : order.next();

        Class<?> listType = GenericsUtils.getParametizedClass(field);
        boolean isSimpleProperty = isFinalType(listType);

        for (Object value : list) {

            if (!isSimpleProperty) {
                propertyList = processComplexObject(value, propertyList, key, order);
            } else {
                FinalType finalType = getFindType(listType);
                propertyList = processSimpleObject(propertyList, finalType, value, key, order);
            }
            order = order.incrementValue();

        }

        return propertyList;
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

            if(alias.root())
                return alias.value();

            return keyPrefix.concat(alias.value());
        }

        return keyPrefix.concat(field.getName());
    }
}
