package org.example.producerapp.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {

    public final static String QUEUE_NAME = "test_queue";
    public final static String EXCHANGE_NAME = "test_exchange";
    public final static String ROUTING_KEY = "test_routing_key";

    @Bean
    public Queue queue() {
        log.info("Creating queue: {}", QUEUE_NAME);
        return QueueBuilder.durable(QUEUE_NAME)
                .quorum()
                .build();
    }

    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
