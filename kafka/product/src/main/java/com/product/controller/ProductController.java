package com.product.controller;

import com.product.pojo.Book;
import com.product.service.BookProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author liugaoren
 * @Date 2022/7/29 11:20
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    BookProducerService bookProducerService;

    public static Integer number = 0;

    @RequestMapping("send")
    public String send(String topic, String message){
        bookProducerService.sendMessage(topic,message);
        return "往"+topic+"发送消息："+message;
    }

    @RequestMapping("sendObject")
    public String sendObject(String topic, String message){

        bookProducerService.sendMessage(topic,new Book(1L,"eaf"));
        return "往"+topic+"发送消息："+message;
    }

    @RequestMapping("get")
    public Integer get(){
       return number;
    }

}
