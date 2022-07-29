package com.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:15
 */
@Service
public class BookProducerServiceImpl implements BookProducerService{

    private static final Logger logger = LoggerFactory.getLogger(BookProducerServiceImpl.class);

    @Autowired
    private  KafkaTemplate kafkaTemplate;



    @Override
    public void sendMessage(String topic, String o) {
        kafkaTemplate.send(topic, o);
    }
}