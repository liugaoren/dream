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
public class Ticket implements Runnable {

    public Integer ticker = 20;

    @Override
    public void run() {
        while (true) {
            if (ticker <= 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "购买了" + ticker);
            ticker--;
        }
    }

    public static void main(String[] args) {
        Ticket target = new Ticket();
        new Thread(target, "携程").start();

        new Thread(target, "飞猪").start();
    }
}
