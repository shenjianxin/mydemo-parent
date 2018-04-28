package cn.com.jdkdemo.mythread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此方法使用不可控
 * 将当前线程重置到就绪状态，等待CPU分配执行权限，可能等待后CPU还是分给自己
 */
public class MyYieldTest {

    public String getTime() {
        return new SimpleDateFormat("mm:ss:ms").format(new Date());
    }

    public static void main(String[] args) {
        MyYieldTest myYieldTest = new MyYieldTest();
        Thread t1 = myYieldTest.getThreadA();
        Thread t2 = myYieldTest.getThreadB();
        t1.start();
        t2.start();


    }


    public Thread getThreadA() {
        return new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("线程A执行：" + i);
                    Thread.yield();
                }

            }
        };
    }


    public Thread getThreadB() {
        return new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("线程B执行：" + i);
                }
            }
        };
    }

}
