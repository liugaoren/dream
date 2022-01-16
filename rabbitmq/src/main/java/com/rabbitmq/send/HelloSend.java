package com.rabbitmq.send;

import com.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class HelloSend {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello 我来了" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    //发送者
    public void sendUser(User user) {
        user.setUsername("zhhangsan");
        user.setPassword("123456");
        System.out.println("Sender object: " + user.toString());
        this.rabbitTemplate.convertAndSend("hello", user);
    }

}

