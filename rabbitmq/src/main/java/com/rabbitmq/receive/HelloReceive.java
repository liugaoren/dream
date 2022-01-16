package com.rabbitmq.receive;

import com.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class HelloReceive {

    @RabbitHandler
    public void process(String a) {
        System.out.println("Receiver  我收到的你的来信: " + a);
    }


    //接受者
    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver object : " + user);
    }

}
