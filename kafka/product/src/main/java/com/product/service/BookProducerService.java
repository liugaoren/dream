package com.product.service;

import java.util.Objects;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:21
 */
public interface BookProducerService {
    public void sendMessage(String topic, Object o);
}
