package top.alexmmd.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.alexmmd.redis.annos.QueueConsumer;
import top.alexmmd.redis.delay.IQueueListener;

/**
 * @author 汪永晖
 * @date 2021/12/21 15:50
 */
@QueueConsumer(topic = "test")
@Lazy
@Slf4j
@Component
public class TestListener implements IQueueListener {
    @Override
    public void consumer(String body) {
        log.info("{}开始处理{}", Thread.currentThread().getName(), body);
    }
}
