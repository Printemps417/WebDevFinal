package com.example.todoSpringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    public void deleteKeysWithPrefixes(String... prefixes) {
        Set<String> keysToDelete = new HashSet<>();

        for (String prefix : prefixes) {
            // 使用 scan 避免大量键时的性能问题
            Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(ScanOptions.scanOptions().match(prefix + "*").count(1000).build());
            while (cursor.hasNext()) {
                keysToDelete.add(new String(cursor.next()));
            }
        }

        if (!keysToDelete.isEmpty()) {
            redisTemplate.delete(keysToDelete);
        }
    }
}
