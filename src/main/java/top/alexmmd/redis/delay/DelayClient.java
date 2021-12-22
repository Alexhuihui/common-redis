package top.alexmmd.redis.delay;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author 汪永晖
 * @date 2021/12/21 15:15
 */
@SuppressWarnings("all")
public class DelayClient {

    private static DelayQueue delayQueue = DelayQueue.create("DELAY_QUEUE_01");

    public static <T> void offer(String topic, T body, long delay) {
        delayQueue.offer(topic, body, delay);
    }

    public static <T> void offer(String topic, T body, Date date) {
        delayQueue.offer(topic, body, DateUtil.between(new Date(), date, DateUnit.SECOND));
    }
}
