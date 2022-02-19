package com.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author lgr
 * @date 2022-01-05 15:33:00
 */
public class TicketLock implements Runnable {
    private InterProcessMutex lock;

    public TicketLock() {
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);
        //2.第二种方式
        //CuratorFrameworkFactory.builder();
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(retryPolicy)
                .build();

        //开启连接
        client.start();

        lock = new InterProcessMutex(client, "/lock");
    }

    public Integer ticker = 20;

    @Override
    public void run() {
        while (true) {
            try {
                lock.acquire(3, TimeUnit.SECONDS);
                if (ticker <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "购买了" + ticker);
                ticker--;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TicketLock target = new TicketLock();
        new Thread(target, "携程").start();

        new Thread(target, "飞猪").start();
    }
}
