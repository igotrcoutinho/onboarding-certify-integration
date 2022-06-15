package io.smilego.onboarding.certify.service.api.infrastructure.log;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Factory
public class LoggerFactory {

    @Value("${micronaut.application.name:`service`}")
    private String LOG_NAME;
    
    @Singleton
    public org.slf4j.Logger getSingletonLogger() {
        return org.slf4j.LoggerFactory.getLogger(LOG_NAME);
    }

    public org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(LOG_NAME);
    }

}