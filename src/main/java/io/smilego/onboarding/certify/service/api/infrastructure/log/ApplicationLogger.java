package io.smilego.onboarding.certify.service.api.infrastructure.log;

import io.smilego.onboarding.certify.service.api.application.configuration.property.ApplicationProperties;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ApplicationLogger {
    @Inject
    private LoggerFactory loggerFactory;

    @Inject
    private ApplicationProperties appProperties;

    public void info(String message) {
        loggerFactory.getSingletonLogger().info(processMessage(message));
    }

    public void error(String message) {
        loggerFactory.getSingletonLogger().error(processMessage(message));
    }

    public void debug(String message) {
        loggerFactory.getSingletonLogger().debug(processMessage(message));
    }

    public void info(String message, Throwable throwable) {
        loggerFactory.getSingletonLogger().info(processMessage(message), throwable);
    }

    public void error(String message, Throwable throwable) {
        loggerFactory.getSingletonLogger().error(processMessage(message), throwable);
    }

    public void debug(String message, Throwable throwable) {
        loggerFactory.getSingletonLogger().debug(processMessage(message), throwable);
    }

    private String processMessage(String message) {
        return message;
    }

}
