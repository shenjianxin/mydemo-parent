package cn.com.jdkdemo.myconcurrent;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多服务高并发模拟测试
 */
public class ConcurrentDemo {

    public static final int appConcurrentNum = 50;//并发的客户端数量
    public static final int taskConcurrentNum = 500;//每个客户端并发的任务数量
    public static final int random = 0;//请求错开0-当前数值毫秒的时间，防止瞬间高并发,连接数耗尽

    final static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch concurrentCountDownLatch = new CountDownLatch(appConcurrentNum * taskConcurrentNum);
        final ExecutorService exec1 = Executors.newFixedThreadPool(appConcurrentNum);
        for (int i = 0; i < appConcurrentNum; i++) {
            exec1.execute(new Runnable() {
                @Override
                public void run() {
                    final ExecutorService exec2 = Executors.newFixedThreadPool(taskConcurrentNum);
                    try {
                        begin.await();
                    } catch (InterruptedException e) {
                    }

                    final CyclicBarrier cyclicBarrier = new CyclicBarrier(taskConcurrentNum, new Runnable() {
                        @Override
                        public void run() {
                            exec2.shutdown();
                        }
                    });

                    for (int j = 0; j < taskConcurrentNum; j++) {
                        if (random > 0) {
                            try {
                                Thread.sleep((long) (Math.random() * 1000) % random);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        exec2.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    atomicInteger.incrementAndGet();
                                } finally {
                                    try {
                                        cyclicBarrier.await();
                                    } catch (InterruptedException e) {
                                    } catch (BrokenBarrierException e) {
                                    }
                                    concurrentCountDownLatch.countDown();
                                }
                            }
                        });
                    }
                }
            });
        }
        System.out.println("多服务高并发模拟测试开始执行...");
        begin.countDown();
        concurrentCountDownLatch.await();
        stopwatch.stop();
        long elapsed = stopwatch.getTime();
        System.out.println("执行完成，耗时：" + elapsed + "豪秒，期望值：" + appConcurrentNum * taskConcurrentNum + "，实际值：" + atomicInteger.get());
        System.out.println("多服务高并发模拟测试执行结束...");
        exec1.shutdown();

    }
}
