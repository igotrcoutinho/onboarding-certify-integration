package io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.listener;

import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import io.smilego.onboarding.certify.service.api.domain.model.Processo;
import io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.configuration.RabbitMQConfig;
import io.smilego.onboarding.certify.service.api.presentation.dto.kappta.KapptaRequest;
import jakarta.inject.Inject;

@RabbitListener(executor = "certify-listener")
public class RabbitMQListener {

    @Inject
    private ApplicationEventPublisher<Processo> eventProcessPublisher;

    @Queue(RabbitMQConfig.AMQP_QUEUE_INPUT_NAME)
    public void onRequestReceived(KapptaRequest request) {
        Processo processo = Processo.of(request.getEtapaId(), request.getPropriedades(), request.getParametros());
        eventProcessPublisher.publishEvent(processo);
    }

    @Queue(RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME)
    public void onProcessReceived(Processo process) {
        eventProcessPublisher.publishEvent(process);
    }

}
