package top.alexmmd.redis.delay.listener;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.alexmmd.redis.annos.QueueConsumer;
import top.alexmmd.redis.delay.AbstractQueueListener;
import top.alexmmd.redis.delay.bean.Student;

/**
 * @author 汪永晖
 * @date 2021/12/21 15:50
 */
@QueueConsumer(topic = "type-test")
@Lazy
@Slf4j
@Component
public class StudentDelayListener extends AbstractQueueListener<Student> {
    @Override
    public void handle(Student body) {
        log.info("{}开始处理{}, 时间{}", Thread.currentThread().getName(), body, DateUtil.now());
    }
}
