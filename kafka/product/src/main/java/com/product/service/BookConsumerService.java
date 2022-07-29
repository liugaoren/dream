package com.product.service;

import com.product.pojo.Book;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:38
 */
public interface BookConsumerService {
    public void consumeMessage(ConsumerRecord<String, String> bookConsumerRecord, Acknowledgment ack);

    public void consumeMessage2(Book book);
}
