package com.rabbitmq;

import com.rabbitmq.entity.User;
import com.rabbitmq.send.HelloSend;
import com.rabbitmq.send.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitmqApplication.class})
public class RabbitmqApplicationTests {

    @Autowired
    private HelloSend helloSend;

    @Autowired
    private TopicSender topicSender;

    @Test
    public void hello() throws Exception {
        helloSend.send();
    }

    @Test
    public void user() throws Exception {
        helloSend.sendUser(new User());
    }

    @Test
    public void topic() throws Exception {
        topicSender.send_two();
    }
}
