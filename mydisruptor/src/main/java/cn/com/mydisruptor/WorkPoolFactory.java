package cn.com.mydisruptor;

import java.util.concurrent.*;

/**
 * 工作池
 */
public class WorkPoolFactory {

    public enum ThreadGroupEnum {
        DEFAULT(0, "WorkPool默认池"),
        DISRUPTOR(1, "Disruptor event线程池");

        private String name;
        private int index;

        ThreadGroupEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }


    }


    private final static WorkPoolFactory[] workPoolFactories = new WorkPoolFactory[ThreadGroupEnum.values().length];
    /**
     * 分配线程池
     */
    private ExecutorService executorService;


    /**
     * CallerRunsPolicy当线程池不能处理时，用当前线程处理。那么后续处理必须当前线程弄完了才能执行
     * 第一步，初始的poolSize < corePoolSize，提交的runnable任务，会直接做为new一个Thread的参数，立马执行
     * 第二步，当提交的任务数超过了corePoolSize，就进入了第二步操作。会将当前的runable提交到一个block queue中
     * 第三步，workQueue 为LinkedBlockingQueue（无界阻塞队列），队列最大值为Integer.MAX_VALUE。
     * 如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势；
     * 第四步，如果第三步救急方案也无法处理了，就会走到第四步执行reject操作。
     */
    private WorkPoolFactory(WorkPoolConfig config, ThreadGroupEnum groupName) {
        BlockingQueue queue;
        if (config.getBlockingQueuequeueSize() == null) {
            queue = new LinkedBlockingQueue();
        } else {
            queue = new ArrayBlockingQueue(config.getBlockingQueuequeueSize());
        }
        executorService = new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaximumPoolSize(), config.getKeepAliveTime(), config.getUnit(),
                queue, ThreadUtil.buildJobFactory(groupName.name + "-%d"), new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public static WorkPoolFactory initWorkPool(WorkPoolConfig config, ThreadGroupEnum groupName) {
        if (groupName == null) {
            throw new IllegalArgumentException("ThreadGroupEnum is must");
        }
        if (workPoolFactories[groupName.index] == null) {
            synchronized (groupName) {
                if (workPoolFactories[groupName.index] == null) {
                    workPoolFactories[groupName.index] = new WorkPoolFactory(config, groupName);
                }
            }
        }
        return workPoolFactories[groupName.index];
    }

    public static WorkPoolFactory getInstance(ThreadGroupEnum groupName) {
        return workPoolFactories[groupName.index];
    }

    public void execute(Runnable command) {
        executorService.execute(command);
    }

    public Future<?> submit(Callable<?> task) {
        return executorService.submit(task);
    }

}
