package org.example.consumerapp.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class RabbitConsumer {

    @RabbitListener(queues = "test_queue")
    public void onMessage(String message) {
        log.info("Received message at timestamp {}: {}", new Date(), message);
    }
}
