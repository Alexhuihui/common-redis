package top.alexmmd.redis.delay.listener;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.alexmmd.redis.annos.QueueConsumer;
import top.alexmmd.redis.delay.AbstractQueueListener;

/**
 * @author 汪永晖
 * @date 2021/12/21 15:50
 */
@QueueConsumer(topic = "str-test")
@Lazy
@Slf4j
@Component
public class StrDelayListener extends AbstractQueueListener<String> {
    @Override
    public void handle(String body) {
        log.info("{}开始处理{}, 时间{}", Thread.currentThread().getName(), body, DateUtil.now());
    }
}
