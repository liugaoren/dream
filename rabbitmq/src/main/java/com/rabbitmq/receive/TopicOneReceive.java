package com.rabbitmq.receive;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicOneReceive {

    @RabbitListener(queues = "topic.one")
    public void process(String name) {
        System.out.println("Receiver  你好: " + name);
    }

    @RabbitListener(queues = "topic.two")
    public void process1(String name) {
        System.out.println("Receiver  你好: " + name);
    }
}
