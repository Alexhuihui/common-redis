package top.alexmmd.redis.executor.task;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.RInject;

import java.io.Serializable;

/**
 * @author 汪永晖
 * @date 2021/12/22 18:57
 */
public class RunnableTask implements Runnable, Serializable {

    @RInject
    private RedissonClient redissonClient;

    private long param;

    public RunnableTask() {
    }

    public RunnableTask(long param) {
        this.param = param;
    }

    public long getParam() {
        return this.param;
    }

    @Override
    public void run() {
        System.out.println("开始执行");
        RAtomicLong atomic = redissonClient.getAtomicLong("myAtomic");
        this.param = atomic.addAndGet(param);
    }

    @Override
    public String toString() {
        return "RunnableTask{" +
                "param=" + param +
                '}';
    }
}
