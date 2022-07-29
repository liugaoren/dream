package com.product.service;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:21
 */
public interface BookProducerService {
    public void sendMessage(String topic, String o);
}
