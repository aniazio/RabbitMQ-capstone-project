package org.example.rabbitmqcapstoneproject.consumer;

import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmqcapstoneproject.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class RabbitConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void onMessage(String message) {
        log.info("Received message at timestamp {}: {}", new Date(), message);
    }
}
