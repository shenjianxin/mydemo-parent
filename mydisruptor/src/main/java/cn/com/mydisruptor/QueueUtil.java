package cn.com.mydisruptor;


/**
 * @author shenjx
 * 队列工具类
 */
public class QueueUtil {
    /**
     * 处理器
     */
    private static DisruptorStart disruptor;

    private synchronized static void initWorkPool() {
        if (disruptor == null) {
            int corePoolSize = 1 << 4;
            int maximumPoolSize = 1 << 8;
            int keepAliveTime = 45;
            int blockingQueuequeueSize = 1 << 10;
            int ringBufferSize = 1 << 12;

            WorkPoolConfig config = new WorkPoolConfig(corePoolSize, maximumPoolSize, keepAliveTime, blockingQueuequeueSize);
            EventHandle eventHandle = new EventHandle(config);
            disruptor = new DisruptorStart(ringBufferSize, eventHandle);
        }
    }

    /**
     * 放入对列
     *
     * @param eventHandle 队列任务处理者，为了保障线程安全，建议eventHandle使用多例
     * @param msg         消息体
     */
    public static void put(AbstractEventHandle eventHandle, Object msg) {
        initWorkPool();
        disruptor.produce(eventHandle, msg, null);
    }

    /**
     * 放入对列
     *
     * @param eventHandle 队列任务处理者，每次会创建新对象
     * @param msg         消息体
     */
    public static void put(AbstractEventHandle eventHandle, Object msg, Class dataClazz) {
        initWorkPool();
        disruptor.produce(eventHandle, msg, dataClazz);
    }

    public static String showCapacity() {
        if (disruptor == null) {
            return "disruptor未启动";
        }
        return disruptor.showCapacity();
    }

}
