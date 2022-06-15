package io.smilego.onboarding.certify.service.api.infrastructure.amqp.rabbitmq.configuration;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import io.micronaut.rabbitmq.connect.ChannelInitializer;
import jakarta.inject.Singleton;

@Singleton
public class RabbitMQChannelInitializer extends ChannelInitializer {

    @Override
    public void initialize(Channel channel) throws IOException {
        // Creates the input queue
        channel.queueDeclare(RabbitMQConfig.AMQP_QUEUE_INPUT_NAME, true, false, false, null);

        // Creates the process queue
        channel.queueDeclare(RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME, true, false, false, null);

        // Creates the output queue
        channel.queueDeclare(RabbitMQConfig.AMQP_QUEUE_OUTPUT_NAME, true, false, false, null);

        // Creates the main exchange
        channel.exchangeDeclare(RabbitMQConfig.AMQP_EXCHANGE_MAIN_NAME, BuiltinExchangeType.DIRECT, true);

        // Bind the queues to main exchange channel
        channel.queueBind(RabbitMQConfig.AMQP_QUEUE_INPUT_NAME, RabbitMQConfig.AMQP_EXCHANGE_MAIN_NAME, RabbitMQConfig.AMQP_QUEUE_INPUT_NAME);
        channel.queueBind(RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME, RabbitMQConfig.AMQP_EXCHANGE_MAIN_NAME, RabbitMQConfig.AMQP_QUEUE_PROCESS_NAME);

        // Creates the asleep exchange channel
        channel.exchangeDeclare(RabbitMQConfig.AMQP_EXCHANGE_ASLEEP_NAME, BuiltinExchangeType.FANOUT, true);

        // Creates the asleep queue
        Map<String, Object> requestAsleepQueueArgs = Collections.singletonMap("x-dead-letter-exchange", RabbitMQConfig.AMQP_EXCHANGE_MAIN_NAME);
        channel.queueDeclare(RabbitMQConfig.AMQP_QUEUE_ASLEEP_NAME, true, false, false, requestAsleepQueueArgs);

        // Bind the asleep queue to asleep exchange channel
        channel.queueBind(RabbitMQConfig.AMQP_QUEUE_ASLEEP_NAME, RabbitMQConfig.AMQP_EXCHANGE_ASLEEP_NAME, RabbitMQConfig.AMQP_QUEUE_ASLEEP_NAME);

    }

}
