package cn.com.mydisruptor;

/**
 * @author shenjx
 * 队列中任务的处理者抽象类
 */
public abstract class AbstractEventHandle implements Runnable {
    /**
     * 事件
     */
    private Event event;

    protected void setEvent(Event event) {
        this.event = event;
    }

    /**
     * 线程运行
     */
    @Override
    public void run() {
        execute(event);
    }

    /**
     * 执行任务接口
     *
     * @param event 事件
     */
    public abstract void execute(Event event);

}
