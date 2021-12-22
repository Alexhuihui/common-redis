package top.alexmmd.redis.annos;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 汪永晖
 * @date 2021/12/22 15:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    /**
     * redis锁 名字
     */
    String lockName() default "";

    /**
     * redis锁 key 支持spel表达式
     */
    String key() default "";

    /**
     * 过期毫秒数,默认为5000毫秒
     *
     * @return 轮询锁的时间
     */
    int expire() default 5000;

    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
