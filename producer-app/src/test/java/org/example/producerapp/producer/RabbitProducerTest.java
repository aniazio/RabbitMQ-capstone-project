package org.example.producerapp.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RabbitProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitProducer rabbitProducer;

    @Test
    void testRun() {
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString(), messageCaptor.capture());

        rabbitProducer.run();
        verify(rabbitTemplate, times(1))
                .convertAndSend(anyString(), anyString(), anyString());
        assertEquals("Message 0", messageCaptor.getValue());

        rabbitProducer.run();
        verify(rabbitTemplate, times(2))
                .convertAndSend(anyString(), anyString(), anyString());
        assertEquals("Message 1", messageCaptor.getValue());
    }
}
