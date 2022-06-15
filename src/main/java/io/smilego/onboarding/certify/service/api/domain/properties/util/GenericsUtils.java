package io.smilego.onboarding.certify.service.api.domain.properties.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GenericsUtils {

    private GenericsUtils() {}

    public static Class<?> getParametizedClass(Object object) {
        if(object.getClass().getGenericSuperclass() instanceof ParameterizedType)  {

            ParameterizedType parameterizedType = (ParameterizedType) object.getClass().getGenericSuperclass();
            Type actualArgument = parameterizedType.getActualTypeArguments()[0];

            if (ParameterizedType.class.isAssignableFrom(actualArgument.getClass())) {
                return (Class<?>) ((ParameterizedType) actualArgument).getRawType();
            }
            return (Class<?>) actualArgument;

        }
        return Object.class;
    }

    public static Class<?> getParametizedClass(Field field) {
        if(field.getGenericType() instanceof ParameterizedType)  {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static List<Field> getAnnotatedFields(Object object, Class<? extends Annotation> annotationClass) {
        Field[] fields = object.getClass().getDeclaredFields();

        return Stream.of(fields)
                        .filter(f -> f.isAnnotationPresent(annotationClass))
                        .collect(Collectors.toList());
    }

    public static Object setValue(Object object, Field field, String value) throws IllegalAccessException {
        field.set(object, formatFieldValue(field, value));
        return object;
    }

    public static Object formatFinalTypeValue(Class<?> type, String value) {
        return FinalType.getFindType(type).format(value);
    }

    public static Object formatFieldValue(Field field, String value) {
        Class<?> type = field.getType();
        return FinalType.getFindType(type).format(value);
    }

    public static boolean isFinalType(Field f) {
        return GenericsUtils.isFinalType(f.getType());
    }

    public static boolean isFinalType(Class<?> type) {
        try {
            FinalType.getFindType(type);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public enum FinalType {
        STRING(String.class, String::toString),
        BOOLEAN_WRAPPER(Boolean.class, Boolean::valueOf),
        INTEGER_WRAPPER(Integer.class, Integer::valueOf),
        LONG_WRAPPER(Long.class, Long::valueOf),
        DOUBLE_WRAPPER(Double.class, Double::valueOf),
        FLOAT_WRAPPER(Float.class, Float::valueOf),
        BIG_DECIMAL(BigDecimal.class, BigDecimal::new),
        BOOLEAN(boolean.class, Boolean::parseBoolean),
        INTEGER(int.class, Integer::parseInt),
        LONG(long.class, Long::parseLong),
        DOUBLE(double.class, Double::parseDouble),
        FLOAT(float.class, Float::parseFloat),
        LOCAL_DATE(LocalDate.class, TemporalUtils::formatAsLocalDate),
        LOCAL_DATE_TIME(LocalDateTime.class, TemporalUtils::formatAsLocalDateTime);

        private final Class<?> type;

        private final Function<String,?> formatFunction;

        <T> FinalType(Class<T> type, Function<String, T> formatFunction) {
            this.type = type;
            this.formatFunction = formatFunction;
        }

        public boolean hasTheSameType(Class<?> type) {
            return this.type.equals(type);
        }

        public Object format(String value) {
            return this.formatFunction.apply(value);
        }

        public static FinalType getFindType(Class<?> type) {
            return Stream.of(FinalType.values())
                            .filter(finalType -> finalType.hasTheSameType(type))
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new);
        }

    }

    @SuppressWarnings("deprecation")
    public static <T> T getInstance(Class<T> type) throws IllegalAccessException, InstantiationException {
        return type.newInstance();
    }

    public static Object getPropertyValue(Object object, Field field, boolean withDefaultValue) throws IllegalAccessException, InstantiationException {
        Object objectProperty = field.get(object);

        if(withDefaultValue && Objects.isNull(objectProperty))
            objectProperty = GenericsUtils.getInstance(field.getType());

        field.set(object, objectProperty);
        return objectProperty;
    }

    public static Field getField(Class<?> type, String fieldName) throws NoSuchFieldException {
        return Stream.of(type.getDeclaredFields())
                        .filter(f -> f.getName().equals(fieldName))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchFieldException());
    }

}
