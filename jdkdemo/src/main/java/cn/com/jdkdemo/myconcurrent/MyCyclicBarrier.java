package cn.com.jdkdemo.myconcurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Author:   shenjx
 * Date:     2018/4/10 9:12
 * Description:
 */
public class MyCyclicBarrier {

    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("全部到达，开始活动");
            }
        });

        for (int i = 1; i <= 5; i++) {
            final int user = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(user + "出发...");
                        Thread.sleep((long) (Math.random() * 10000) % 4000);
                        System.out.println(user + "到达,进入等待区...");
                       /* if (cyclicBarrier.getNumberWaiting() == cyclicBarrier.getParties() - 1) {
                            System.out.println("全部到达，开始活动");
                        }*/
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


    }


}
