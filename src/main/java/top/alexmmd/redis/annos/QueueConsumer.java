package top.alexmmd.redis.annos;

import java.lang.annotation.*;

/**
 * @author 汪永晖
 * @date 2021/12/21 14:49
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueueConsumer {

    /**
     * 可消费的topic
     */
    String topic() default "";

    /**
     * 可消费的topic数组
     */
    String[] topics() default {};
}
