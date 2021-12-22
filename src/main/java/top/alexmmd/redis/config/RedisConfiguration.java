package top.alexmmd.redis.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 汪永晖
 * @date 2021/12/21 13:35
 */
@SuppressWarnings("ALL")
@Configuration
public class RedisConfiguration {
    @Value("${spring.redis.host:localhost}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private String port;
    @Value("${spring.redis.password:}")
    private String password;

    /**
     * redisson客户端
     */
    @Bean
    public RedissonClient redissonClient() {
        return getRedissonClient();
    }

    /**
     * redisson客户端
     */
    @Bean
    public RedissonReactiveClient redissonReactiveClient() {
        return getRedissonClient().reactive();
    }

    /**
     * redisson客户端
     */
    @Bean
    public RedissonRxClient redissonRxClient() {
        return getRedissonClient().rxJava();
    }


    private RedissonClient getRedissonClient() {
        Config config = new Config();
        SingleServerConfig server = config.useSingleServer();
        String address = StrUtil.format("redis://{}:{}", host, port);
        server.setAddress(address);
        if (StrUtil.isNotEmpty(password)) {
            server.setPassword(password);
        }
        return Redisson.create(config);
    }
}
