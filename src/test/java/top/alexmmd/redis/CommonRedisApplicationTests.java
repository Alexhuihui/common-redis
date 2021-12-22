package top.alexmmd.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.alexmmd.redis.delay.DelayClient;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UtApplication.class)
class CommonRedisApplicationTests {

    @Resource
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        RKeys keys = redissonClient.getKeys();
        for (String key : keys.getKeys()) {
            System.out.println("key = " + key);
        }
        DelayClient.offer("test", "test-body", 10);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
