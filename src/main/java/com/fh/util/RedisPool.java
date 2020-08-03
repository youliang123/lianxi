package com.fh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private RedisPool(){

    }

    private static JedisPool jedisPool;

    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMinIdle(500);
        jedisPoolConfig.setMaxIdle(500);

        jedisPool = new JedisPool(jedisPoolConfig, "172.20.10.6", 6379);
    }
    //只会执行一次
    static {
        initPool();
    }

    public static Jedis getResource(){
        return jedisPool.getResource();
    }
}
