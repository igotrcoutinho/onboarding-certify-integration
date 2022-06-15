package io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.client;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import io.micronaut.rabbitmq.annotation.RabbitProperty;
import io.smilego.onboarding.certify.service.api.domain.model.Processo;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.configuration.RabbitMQConfig;

@RabbitClient(RabbitMQConfig.AMQP_EXCHANGE_ASLEEP_NAME)
public interface RabbitMQAsleepClient {

    @Binding(RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME)
    @RabbitProperty(name = "expiration", value = "${unico.process.retry.interval:`3000`}", type = Integer.class)
    @RabbitProperty(name = "contentType", value = "application/json")
    public void suspend(Processo process);

    @Binding(RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME)
    @RabbitProperty(name = "expiration", value = "${unico.error.retry.interval:`5000`}", type = Integer.class)
    @RabbitProperty(name = "contentType", value = "application/json")
    public void suspendByError(Processo process);

}
