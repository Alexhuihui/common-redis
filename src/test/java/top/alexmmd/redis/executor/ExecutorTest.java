package top.alexmmd.redis.executor;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.RedissonNode;
import org.redisson.api.RExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;
import org.redisson.config.SingleServerConfig;
import top.alexmmd.redis.CommonRedisApplicationTests;
import top.alexmmd.redis.executor.task.CallableTask;
import top.alexmmd.redis.executor.task.RunnableTask;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author 汪永晖
 * @date 2021/12/22 18:44
 */
@Slf4j
public class ExecutorTest extends CommonRedisApplicationTests {

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void testBasic() {
        Config config = new Config();
        SingleServerConfig server = config.useSingleServer();
        String address = StrUtil.format("redis://{}:{}", "localhost", "6379");
        server.setAddress(address);
        RedissonClient redisson = Redisson.create(config);

        RedissonNodeConfig nodeConfig = new RedissonNodeConfig(config);
        nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("myExecutor", 1));
        RedissonNode node = RedissonNode.create(nodeConfig);
        node.start();

        RExecutorService e = redisson.getExecutorService("myExecutor");
        e.execute(new RunnableTask());
        e.submit(new CallableTask());

        e.shutdown();
        node.shutdown();
    }
}
