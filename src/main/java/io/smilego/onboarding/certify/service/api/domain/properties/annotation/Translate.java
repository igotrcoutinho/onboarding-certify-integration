package io.smilego.onboarding.certify.service.api.domain.properties.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.smilego.onboarding.certify.service.api.domain.properties.domain.Translation;

@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface Translate {

    Class<? extends Translation> value();

}
