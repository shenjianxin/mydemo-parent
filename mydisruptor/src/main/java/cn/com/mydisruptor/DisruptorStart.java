package cn.com.mydisruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Date;
import java.util.concurrent.ThreadFactory;

/**
 * @author shenjx
 * 异步框架队列初始化
 */
public class DisruptorStart {
    /**
     * ringbuffer容量，最好是2的N次方
     */
    private RingBuffer<Event> ringBuffer;
    private Disruptor<Event> disruptor;
    private int bufferSize;

    /**
     * 构造
     *
     * @param bufferSize 队列大小
     * @param name       名称
     * @param handler    事件处理
     */
    public DisruptorStart(int bufferSize, String name, EventHandler<Event> handler) {
        this.bufferSize = bufferSize;
        //这种策略在linux下，没有压力下CPU占用率小，其他的策略都在200%左右
        ThreadFactory threadFactory = ThreadUtil.buildJobFactory("异步框架服务-%d");
        disruptor = new Disruptor<>(Event.EVENT_FACTORY, bufferSize, threadFactory, ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWith(handler);
        ringBuffer = disruptor.start();
    }


    /**
     * 关闭
     */
    public void shutdown() {
        disruptor.shutdown();
    }

    /**
     * 放入队列
     *
     * @param eventHandle 处理者
     * @param msg         消息
     */
    private void produce0(Class<? extends AbstractEventHandle> eventHandle, Object msg, Class dataClazz) {
        //获取下一个序列号
        long sequence = ringBuffer.next();//如果满了这里会阻塞
        try {
            //将状态报告存入ringBuffer的该序列号中
            Event info = ringBuffer.get(sequence);
            info.setData(msg);
            info.setEventHandle(eventHandle);
            info.setTime(new Date());
            info.setUid(String.valueOf(sequence));
            info.setDataClazz(dataClazz);
        } finally {  //通知消费者该资源可以消费
            ringBuffer.publish(sequence);
        }
    }

    /**
     * 将状态报告放入资源队列，等待处理
     *
     * @param eventHandle 处理者
     * @param msg         消息
     */
    public void produce(Class<? extends AbstractEventHandle> eventHandle, Object msg, Class dataClazz) {
        // if capacity less than 10%, don't use ringbuffer anymore
        if (ringBuffer.remainingCapacity() < bufferSize * 0.01) {
            System.out.println("目前队列剩余数量已经小于1%");
        } else if (ringBuffer.remainingCapacity() < bufferSize * 0.2) {
            System.out.println("目前队列剩余数量已经小于20%");
        }
        produce0(eventHandle, msg, dataClazz);
    }
}
