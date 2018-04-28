package cn.com.mydisruptor;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private ThreadPoolExecutor executor;

    public ThreadPool(int size) {
        executor = new ThreadPoolExecutor(size, Integer.MAX_VALUE,
                0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

}
