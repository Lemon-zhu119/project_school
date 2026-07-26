package org.one.afternoon.service.impl;

import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    // ================= String =================

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // ================= Hash =================

    @Override
    public void hashPut(String key, Object field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public Object hashGet(String key, Object field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public void hashDelete(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    @Override
    public boolean hashHasKey(String key, Object field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    @Override
    public Map<Object, Object> hashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    // ================= Expire =================

    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }
}