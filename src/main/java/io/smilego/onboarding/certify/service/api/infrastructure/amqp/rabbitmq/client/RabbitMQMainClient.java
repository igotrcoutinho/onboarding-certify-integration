package io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.client;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import io.micronaut.rabbitmq.annotation.RabbitProperty;
import io.smilego.onboarding.certify.service.api.domain.model.Processo;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.configuration.RabbitMQConfig;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.KapptaResponse;

@RabbitClient
public interface RabbitMQMainClient {

    @Binding(RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME)
    @RabbitProperty(name = "contentType", value = "application/json")
    public void send(Processo process);

    @Binding(RabbitMQConfig.AMQP_QUEUE_OUTPUT_NAME)
    @RabbitProperty(name = "contentType", value = "application/json")
    public void send(KapptaResponse response);

}
