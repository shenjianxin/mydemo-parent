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

    public static void main(String[] args) throws InterruptedException {
        //在线程可以通过await（）之前必须调用countDown（）的次数
        //具有计数1的 CountdownLatch 可以用作”启动大门”，来立即启动一组线程；
        final CountDownLatch begin = new CountDownLatch(1);  //为0时开始执行
        final ExecutorService exec = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            final int NO = i + 1;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        //如果当前计数为零，则此方法立即返回。
                        //如果当前计数大于零，则当前线程将被禁用以进行线程调度，并处于休眠状态，直至发生两件事情之一：
                        //或调用countDown（）方法，计数达到零;
                        //或一些其他线程中断当前线程。

                        //等待直到 CountDownLatch减到1
                        begin.await();
                        SortedMap<Object, Object> sortedMap = new TreeMap<>();
                        sortedMap.put("appid", "5412916052207510000052159");
                        sortedMap.put("mch_id", "dhfkjadh");
                        sortedMap.put("nonce_str", "fasjkldfh");
                        //String sign = SignatureUtil.getSignature(sortedMap, "fhldhfkajhdfkjajsdh");
                        //System.out.println("sign" + String.valueOf(NO) + ": " + sign);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.submit(runnable);
        }
        System.out.println("开始执行");
        //减少锁存器的计数，如果计数达到零，释放所有等待的线程。
        // begin减一，开始并发执行
        begin.countDown();
        //此方法不等待先前提交的任务完成执行
        //exec.shutdown();
        //为了保证先前提交的任务完成执行 使用此方法
        exec.awaitTermination(4000, TimeUnit.MILLISECONDS);


    }


}



