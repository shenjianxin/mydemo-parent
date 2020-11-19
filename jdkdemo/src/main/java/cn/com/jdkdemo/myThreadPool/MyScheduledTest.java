package cn.com.jdkdemo.myThreadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2018-2020, 广东领端科技有限公司
 * FileName: Aa
 * Author:   shenjx
 * Date:     2020/9/19 23:15
 * Description:
 */
public class MyScheduledTest {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);


        System.out.println("固定速率开始：" + new Date());
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("固定速率" + new Date());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2, 3, TimeUnit.SECONDS);//固定速率


        //上一次执行完成后，等待延时时间才开始执行
//        System.out.println("固定延时开始：" + new Date());
//        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("固定延时" + new Date());
//                try {
//                    Thread.sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 2, 3, TimeUnit.SECONDS);

    }
}
