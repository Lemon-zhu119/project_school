package org.one.afternoon.service;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public interface RedisService {

    // String
    // String
    void setValue(String key, Object value);
    Object getValue(String key);
    void delete(String key);

    // Hash
    void hashPut(String key, Object field, Object value);
    Object hashGet(String key, Object field);
    void hashDelete(String key, Object... fields);
    boolean hashHasKey(String key, Object field);
    Map<Object, Object> hashEntries(String key);

    // 过期时间（以后几乎一定会用到）
    void expire(String key, long timeout, TimeUnit unit);
}
