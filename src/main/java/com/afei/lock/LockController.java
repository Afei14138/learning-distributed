package com.afei.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lock")
    public void lock(){
        // 获取锁
        RLock lock = redissonClient.getLock("locktest");
        try {
            lock.lock(5, TimeUnit.SECONDS);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("解锁了.....");
        }
    }

}
