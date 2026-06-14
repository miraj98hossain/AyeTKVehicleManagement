package com.aye.backendservice.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisIdLockService {

    private final StringRedisTemplate redisTemplate;

    public RedisIdLockService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String key(String transactionName, Long id, String eventName) {
        return "TRANSACTION-LOCK:" + transactionName + ":" + id + ":" + eventName;
    }

    public boolean tryLock(String transactionName, String eventName, Long id, long seconds) {
        Boolean result = redisTemplate.opsForValue()
                .setIfAbsent(key(transactionName, id, eventName), eventName, Duration.ofSeconds(seconds));

        return Boolean.TRUE.equals(result);
    }

    public void unlock(String transactionName, Long id, String eventName) {
        redisTemplate.delete(key(transactionName, id, eventName));
    }
}