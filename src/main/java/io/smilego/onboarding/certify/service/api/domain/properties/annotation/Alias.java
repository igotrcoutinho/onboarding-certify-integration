package io.smilego.onboarding.certify.service.api.domain.properties.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD , TYPE})
@Retention(RUNTIME)
public @interface Alias {

    String value();

    boolean root() default false;

}
