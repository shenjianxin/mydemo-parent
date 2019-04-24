package cn.com.mydisruptor;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class WorkPoolConfig {


    private Integer corePoolSize;//核心线程池大小

    private Integer maximumPoolSize;//最大线程池大小

    private Long keepAliveTime;//线程最大空闲时间

    private TimeUnit unit = TimeUnit.MILLISECONDS;//时间单位

    private Integer blockingQueuequeueSize;//线程等待队列大小


    public WorkPoolConfig() {

    }

    public WorkPoolConfig(int corePoolSize, int maximumPoolSize, long keepAliveTime, int blockingQueuequeueSize) {
        this.blockingQueuequeueSize = blockingQueuequeueSize;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }


}
