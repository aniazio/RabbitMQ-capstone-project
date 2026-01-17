package org.example.rabbitmqcapstoneproject;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RabbitMqCapstoneProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqCapstoneProjectApplication.class, args);
    }

}
