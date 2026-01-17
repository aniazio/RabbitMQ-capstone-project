package org.example.producerapp.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmqcapstoneproject.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final AtomicInteger messageCount = new AtomicInteger(0);

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.SECONDS)
    public void run() {
        sendMessage();
    }

    private void sendMessage() {
        String message = "Message " + messageCount.getAndIncrement();
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, message);
        log.info("Sent message: {}", message);
    }
}
