package io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.configuration;

public class RabbitMQConfig {

    // Queues
    public static final String AMQP_QUEUE_INPUT_NAME   = "certify.consulta.entrada";
    public static final String AMQP_QUEUE_OUTPUT_NAME  = "certify.consulta.saida";
    public static final String AMQP_QUEUE_PROCESS_NAME = "certify.processo.processamento";
    public static final String AMQP_QUEUE_ASLEEP_NAME  = "certify.processo.suspensao";

    // Exchanges
    public static final String AMQP_EXCHANGE_MAIN_NAME   = "certify.exchange.principal";
    public static final String AMQP_EXCHANGE_ASLEEP_NAME = "certify.exchange.suspensao";

}
