package cn.com.jdkdemo.myconcurrent;

import java.util.concurrent.Semaphore;

/**
 * Author:   shenjx
 * Date:     2018/4/10 9:09
 * Description:
 */
public class MySemaphore {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(2);
        for (int i = 1; i <= 10; i++) {
            final int user = i;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(user + "进入");
                        Thread.sleep((long) (Math.random() * 10000) % 4000);
                        System.out.println(user + "完成");
                        Thread.sleep((long) (Math.random() * 10000) % 4000);
                        System.out.println(user + "离开");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
