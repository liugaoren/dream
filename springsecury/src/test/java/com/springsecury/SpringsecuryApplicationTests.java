package com.springsecury;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class SpringsecuryApplicationTests {

    @Test
    void contextLoads() {

        final int a=getA();
        System.out.println(a);
        getA();

    }

    public static void main(String[] args) {


    }
    public static AtomicInteger b=new AtomicInteger();
    public static int getA(){

        int c=b.getAndAdd(100);
        return c;
    }

}
