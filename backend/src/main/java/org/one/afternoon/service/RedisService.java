package org.one.afternoon.service;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {
    void setValue(String key, Object value);
    Object getValue(String key);
}
