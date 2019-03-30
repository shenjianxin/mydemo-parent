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
            int workerpoolSize = 10;
            int ringBufferSize = 1 << 12;
            EventHandle eventHandle = new EventHandle(workerpoolSize);
            disruptor = new DisruptorStart(ringBufferSize, "异步处理框架", eventHandle);
        }
    }

    /**
     * 放入对列
     *
     * @param eventHandle 队列任务处理者，每次会创建新对象
     * @param msg         消息体
     */
    public static void put(Class<? extends AbstractEventHandle> eventHandle, Object msg) {
        initWorkPool();
        disruptor.produce(eventHandle, msg, null);
    }

    /**
     * 放入对列
     *
     * @param eventHandle 队列任务处理者，每次会创建新对象
     * @param msg         消息体
     */
    public static void put(Class<? extends AbstractEventHandle> eventHandle, Object msg, Class dataClazz) {
        initWorkPool();
        disruptor.produce(eventHandle, msg, dataClazz);
    }

}
