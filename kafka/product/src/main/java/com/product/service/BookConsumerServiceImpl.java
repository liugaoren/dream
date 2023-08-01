package com.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.controller.ProductController;
import com.product.pojo.Book;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:38
 */
@Service
public class BookConsumerServiceImpl implements BookConsumerService{
    @Value("${kafka.topic.my-topic}")
    private String myTopic;
    @Value("${kafka.topic.my-topic2}")
    private String myTopic2;
    private final Logger logger = LoggerFactory.getLogger(BookProducerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    @KafkaListener(topics = {"${kafka.topic.my-topic}"}, groupId = "group2")
    public void consumeMessage(ConsumerRecord<String, Object> bookConsumerRecord, Acknowledgment ack) {
        try {
            ProductController.number++;
            System.out.println(bookConsumerRecord.value());
            logger.info("消费者消费topic:{} partition:{}的消息 -> {}", bookConsumerRecord.topic(), bookConsumerRecord.partition(),bookConsumerRecord.value());
            ack.acknowledge();
            Map map= new HashMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @KafkaListener(topics = {"${kafka.topic.my-topic2}"}, groupId = "group2")
    public void consumeMessage2(ConsumerRecord<String, Map<String,Object>> bookConsumerRecord, Acknowledgment ack) throws JsonProcessingException {
        Map<String, Object> value = bookConsumerRecord.value();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(value);
        logger.info("消费者消费{}的消息 -> {}", myTopic2, s);
        ack.acknowledge();
    }
}
