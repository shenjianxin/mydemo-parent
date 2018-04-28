package cn.com.mydisruptor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadTest test = new ThreadTest();
        test.runTask();
    }

    public String getTime() {
        return new SimpleDateFormat("mm:ss").format(new Date());
    }

    public void runTask()  {
        System.out.println(getTime() + "执行任务开始");

        Thread t = getThread();
        t.start();
        System.out.println(getTime() + "准备挂起5秒");
        t.suspend();
        System.out.println(getTime() + "挂起5秒");
        try {
            Thread.currentThread().wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getTime() + "唤醒");
        t.resume();


        System.out.println(getTime() + "结束");
    }

    public Thread getThread() {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println(getTime() + "任务running1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getTime() + "任务running2");
            }
        };
        return t;


    }


}
