package com.team2.slind.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 레디스 사용 예시 파일
 * 추후 삭제 예정
 */
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void setData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setData(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value, time);
    }

    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

}
