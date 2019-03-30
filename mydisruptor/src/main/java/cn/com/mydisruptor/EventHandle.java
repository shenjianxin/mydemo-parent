package cn.com.mydisruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author shenjx
 * 事件处理，消费者
 */
public class EventHandle implements EventHandler<Event> {
    /**
     * 线程池
     */
    private EventHandleWorkPool workPool;

    /**
     * 构造
     *
     * @param size 线程大小
     */
    public EventHandle(int size) {
        workPool = new EventHandleWorkPool(size);
    }

    public EventHandleWorkPool getWorkPool() {
        return workPool;
    }

    /**
     * 执行事件
     *
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) throws Exception {
        try {
            AbstractEventHandle task = event.getEventHandle().newInstance();
            Event eventNew = new Event();
            eventNew.setData(event.getData());
            eventNew.setEventHandle(event.getEventHandle());
            eventNew.setUid(event.getUid());
            eventNew.setProcessTime(event.getProcessTime());
            eventNew.setTime(event.getTime());
            eventNew.setDataClazz(event.getDataClazz());
            event.clear();
            task.setEvent(eventNew);
            workPool.execute(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
