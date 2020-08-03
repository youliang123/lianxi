package com.fh.util;

import redis.clients.jedis.Jedis;

import java.security.Key;
import java.util.List;
import java.util.Map;

public class RedisUtil {

    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            //抛出异常
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            String s = jedis.get(key);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            Long del = jedis.del(key);
            return del;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    //设置过期时间(seconds中设置60就为一分钟后过期)
    public static void expire(String Key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            jedis.expire(Key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    //判断redis中的value中是否有这个id为value的如果有返回treu否则返回false
    public static Boolean exist(String Key, String field) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            Boolean setbit = jedis.hexists(Key, field);
            return setbit;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    //判断redis中的value中是否有这个id为value的如果有返回treu否则返回false
    public static Boolean exist(String Key) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            Boolean setbit = jedis.exists(Key);
            return setbit;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    //如果有则可以对hash表中进行添加数据
    public static Long hset(String Key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            Long hset = jedis.hset(Key, field, value);
            return hset;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    //如果有则可以根据数据的id获取hash表中的值
    public static String hget(String Key, String field) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            String hget = jedis.hget(Key, field);
            return hget;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     *
     * @param key
     * @return Map<String ,   S tring>
     */
    public static List<String> hvals(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            List<String> hvals = jedis.hvals(key);
            return hvals;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null !=jedis){
                jedis.close();
            }
        }
    }

    /**
     * 删除hash的属性
     *
     * @param key
     * @param fields
     * @return
     */
        public static boolean hdel(String key, String... fields){
            Jedis jedis =null;
            try {
                jedis = RedisPool.getResource();
                Long hdel = jedis.hdel(key, fields);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if (null !=jedis){
                    jedis.close();
                }
            }
    }
}
