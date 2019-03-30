package cn.com.mydisruptor;

import com.lmax.disruptor.EventFactory;
import lombok.Data;

import java.util.Date;

/**
 * @author shenjx
 * 事件对象
 */
@Data
public class Event {
    /**
     * 唯一编号
     */
    private String uid;

    /**
     * 队列任务处理者
     */
    private Class<? extends AbstractEventHandle> eventHandle;

    /**
     * 待处理数据
     */
    private Object data;

    /**
     * 待处理数据对象的类型申明，可以不传，处理者类进行强转化
     * 如果有多种类型需要处理，传值才有意义
     */
    private Class dataClazz;

    /**
     * 事件触发时间
     */
    private Date time;

    /**
     * 被处理的时间
     */
    private String processTime;

    /**
     * 清空数据
     */
    public void clear() {
        uid = null;
        eventHandle = null;
        data = null;
        dataClazz = null;
        time = null;
        processTime = null;
    }

    /**
     * 事件工厂
     */
    public final static EventFactory<Event> EVENT_FACTORY = () -> new Event();


}
