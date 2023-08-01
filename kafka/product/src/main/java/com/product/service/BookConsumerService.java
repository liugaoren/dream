package com.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.pojo.Book;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Map;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:38
 */
public interface BookConsumerService {
    public void consumeMessage(ConsumerRecord<String, Object> bookConsumerRecord, Acknowledgment ack);

    public void consumeMessage2(ConsumerRecord<String, Map<String,Object>> bookConsumerRecord, Acknowledgment ack) throws JsonProcessingException;
}
