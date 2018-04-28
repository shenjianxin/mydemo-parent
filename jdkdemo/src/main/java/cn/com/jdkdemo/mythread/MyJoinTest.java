package cn.com.jdkdemo.mythread;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyJoinTest {

    public String getTime() {
        return new SimpleDateFormat("mm:ss:ms").format(new Date());
    }

    public static void main(String[] args) {
        MyJoinTest myJoinTest = new MyJoinTest();
        Thread t1 = myJoinTest.getThreadA();
        t1.start();

        System.out.println(myJoinTest.getTime() + "子线程A单独执行中,主线程也在继续执行...");
        System.out.println(myJoinTest.getTime() + "子线程A被join，使主线程阻塞，直到子线程A执行完成...");
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myJoinTest.getTime() + "主线程执行完成");


    }


    public Thread getThreadA() {
        return new Thread() {
            @Override
            public void run() {
                System.out.println(getTime() + "线程A开始执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getTime() + "线程A执行完成");
            }
        };
    }


}
