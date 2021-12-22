package top.alexmmd.redis.delay;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import top.alexmmd.redis.CommonRedisApplicationTests;
import top.alexmmd.redis.delay.bean.Student;

import javax.annotation.Resource;

/**
 * @author 汪永晖
 * @date 2021/12/22 14:14
 */
@Slf4j
public class DelayQueueTest extends CommonRedisApplicationTests {

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void testRedisClient() {
        RKeys keys = redissonClient.getKeys();
        for (String key : keys.getKeys()) {
            System.out.println("key = " + key);
        }
    }

    @Test
    public void testDelayQueue() {
        log.info("start time" + DateUtil.now());
        DelayClient.offer("str-test", "test-body1", 5);
        DelayClient.offer("str-test", "test-body2", 5);
        DelayClient.offer("type-test", Student.builder()
                .id(1)
                .level("primary school")
                .name("汪永晖")
                .build(), 3);
        DelayClient.offer("type-test", Student.builder()
                .id(2)
                .level("high school")
                .name("陈")
                .build(), 3);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test end time" + DateUtil.now());
    }
}
