package com.websocket.controller;

import com.websocket.config.WebSocketCountServer;
import com.websocket.config.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/open/socket")
public class WebSocketController {


    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private WebSocketCountServer webSocketServer2;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 手机客户端请求接口
     *
     * @param id 发生异常的设备ID
     * @throws IOException
     */
    @GetMapping(value = "/onReceive")
    public void onReceive(String id) throws IOException {
        System.out.println(id);
        webSocketServer.broadCastInfo(id);
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void onReceive2() throws IOException {
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
        webSocketServer2.broadCastInfo(String.valueOf(i));
    }

}
