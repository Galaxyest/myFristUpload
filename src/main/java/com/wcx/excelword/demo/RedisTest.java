package com.wcx.excelword.demo;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisTest extends Thread {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1",6379);
//        jedis.auth("123456");
        System.out.println(jedis.ping());
        jedis.select(7);
        jedis.set("uname","wcx");
        jedis.set("age","24");
        jedis.set("sex","男");
        System.out.println(jedis.get("uname") + ";" + jedis.get("age") + "；" + jedis.get("sex"));

        Long age = jedis.del("age");
        System.out.println(jedis.get("uname") + ";" + jedis.get("age") + "；" + jedis.get("sex") + age);
        List a = new ArrayList();
        a.remove(1);
    }


}
