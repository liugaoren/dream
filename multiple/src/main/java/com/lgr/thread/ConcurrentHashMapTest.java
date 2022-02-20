package com.lgr.thread;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        Integer integer = new Integer(5);
        System.out.println(System.identityHashCode(integer));
        Integer key = getKey(integer);
        System.out.println(System.identityHashCode(key));
        Integer key3 = new Integer(5);
        System.out.println(integer==key3);
        System.out.println(System.identityHashCode(key3));
        Integer key1 = getKey(key3);
        System.out.println(System.identityHashCode(key1));
        Integer key2 = getKey(new Integer(5));
        System.out.println(System.identityHashCode(key2));
        System.out.println(map.size());
    }

    private static ConcurrentHashMap<Integer, Integer> map=new ConcurrentHashMap<>();

    private static Integer getKey(Integer key){
        Integer integer = map.get(key);
        if(integer==null){
            map.putIfAbsent(key,key);
            integer=key;
        }
        return integer;
    }
}
