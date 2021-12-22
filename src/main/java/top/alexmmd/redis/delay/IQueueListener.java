package top.alexmmd.redis.delay;

/**
 * 队列监听
 *
 * @author 汪永晖
 * @date 2021/12/21 14:10
 */
public interface IQueueListener {

    /**
     * 消费指定队列中的消息
     *
     * @param body 消息内容
     */
    void consumer(String body);
}
