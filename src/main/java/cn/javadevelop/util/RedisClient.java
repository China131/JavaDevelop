package cn.javadevelop.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class RedisClient<T> {
    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private RedisTemplate<String, T> redisTemplate;

    public RedisClient() {
    }

    public void setRedisTemplate(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public T getObject(String key) {
        try {
            return this.redisTemplate.opsForValue().get(key);
        } catch (Exception var3) {
            logger.error("redis读取数据异常", var3);
            return null;
        }
    }

    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    public void setObject(String key, T value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public void setObject(String key, T value, long timeout) {
        this.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void setObject(String key, T value, long timeout, TimeUnit unit) {
        this.redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public T getsetObject(String key, T value, long timeout) {
        T oldValue = this.redisTemplate.opsForValue().getAndSet(key, value);
        this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return oldValue;
    }

    public void setHash(String key, Map<?, ?> map) {
        this.delete(key);
        this.redisTemplate.opsForHash().putAll(key, map);
    }

    public void setHash(String key, Map<?, ?> map, long timeout) {
        this.delete(key);
        this.redisTemplate.opsForHash().putAll(key, map);
        this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void setHash(String key, Map<?, ?> map, long timeout, TimeUnit unit) {
        this.delete(key);
        this.redisTemplate.opsForHash().putAll(key, map);
        this.redisTemplate.expire(key, timeout, unit);
    }

    public T getHashObject(String key, String hashkey) {
        try {
            return (T) this.redisTemplate.opsForHash().get(key, hashkey);
        } catch (Exception var4) {
            logger.error("redis读取数据异常", var4);
            return null;
        }
    }

    public Boolean hasKey(String key) {
        return this.redisTemplate.hasKey(key);
    }

    public Long getSequenceId(String key, Long start) {
        Long curr = this.redisTemplate.opsForValue().increment(key, 1L);
        return Long.valueOf(start.longValue() + curr.longValue());
    }

    public Long getKeyTimes(String key, Long timeout) {
        Long times = this.redisTemplate.opsForValue().increment(key, 1L);
        if(times.equals(Long.valueOf(1L))) {
            this.redisTemplate.expire(key, timeout.longValue(), TimeUnit.SECONDS);
        }

        return times;
    }

    public Long getSequenceId(String key, Long start, Long max, Long timeout) {
        Long curr = this.redisTemplate.opsForValue().increment(key, 1L);
        if(curr.equals(Long.valueOf(1L))) {
            this.redisTemplate.expire(key, timeout.longValue(), TimeUnit.SECONDS);
        }

        if(curr.longValue() >= max.longValue()) {
            try {
                TimeUnit.SECONDS.sleep(timeout.longValue() + 1L);
            } catch (InterruptedException var7) {
                logger.error("暂停异常", var7);
            }
        }

        return Long.valueOf(start.longValue() + curr.longValue());
    }
}

