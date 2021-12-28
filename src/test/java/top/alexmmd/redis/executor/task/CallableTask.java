package top.alexmmd.redis.executor.task;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.RInject;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * @author 汪永晖
 * @date 2021/12/22 19:31
 */
public class CallableTask implements Callable<String>, Serializable {

    @RInject
    RedissonClient redisson;

    @Override
    public String call() throws Exception {
        RMap<String, String> map = redisson.getMap("myMap");
        map.put("1", "2");
        return map.get("3");
    }

}
