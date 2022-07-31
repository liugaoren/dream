package com.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/count/{uid}")
@Component
public class WebSocketCountServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketCountServer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static final AtomicInteger num = new AtomicInteger(0);

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static CopyOnWriteArraySet<Session> sessionPools = new CopyOnWriteArraySet<Session>();

    /**
     * 有客户端连接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "uid") String uid){
        sessionPools.add(session);
        num.incrementAndGet();
        log.info(uid + "加入webSocket！当前人数为" + num);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessionPools.remove(session);
        int cnt = num.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 发送消息
     */
    public void sendMessage(Session session, String message) throws IOException {
        log.info("发送消息：{}", message);
        if(session != null){
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 群发消息
     */
    public void broadCastInfo(String message) throws IOException {
        log.info("广播消息：{}", message);
        for (Session session : sessionPools) {
            if(session.isOpen()){
                sendMessage(session, message);
            }
        }
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        log.error("发生错误");
        throwable.printStackTrace();
    }


    /**
     * 接收客户端消息
     */
    @OnMessage
    public void message(Session session,String message) throws IOException {
       log.info(session.getBasicRemote()+"接收到消息：{}", message);
       session.getBasicRemote().sendText("发送成功");
    }

}