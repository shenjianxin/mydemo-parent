package cn.com.jdkdemo.myconcurrent;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author:   shenjx
 * Date:     2018/4/10 9:13
 * Description:
 */
public class MyCountDownLatch {

    public static void main(String[] args) {
        int concurrentNum = 100;//并发数量
        final CountDownLatch concurrentCountDownLatch = new CountDownLatch(concurrentNum);
        final CountDownLatch begin = new CountDownLatch(1);  //为0时开始执行
        final ExecutorService exec = Executors.newFixedThreadPool(concurrentNum);
        for (int i = 0; i < concurrentNum; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        try {
                            //todo 需要并发执行的任务
                            System.out.println(1111);
                        } finally {
                            concurrentCountDownLatch.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("开始执行");
        begin.countDown();
        try {
            concurrentCountDownLatch.await();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束");
        exec.shutdown();
    }


}



