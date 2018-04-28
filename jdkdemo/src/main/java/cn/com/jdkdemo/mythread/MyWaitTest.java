package cn.com.jdkdemo.mythread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWaitTest {

    public String getTime() {
        return new SimpleDateFormat("mm:ss:ms").format(new Date());
    }

    public static void main(String[] args) {
        MyWaitTest myWaitTest = new MyWaitTest();
        Thread t1 = myWaitTest.getThreadA(myWaitTest);
        Thread t2 = myWaitTest.getThreadB(myWaitTest);

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }


    public Thread getThreadA(final MyWaitTest myWaitTest) {
        return new Thread() {
            @Override
            public void run() {
                synchronized (myWaitTest) {
                    System.out.println(getTime() + "线程A开始执行任务");
                    System.out.println(getTime() + "线程A进入等待锁定池");

                    //让当前线程进入阻塞状态，交出CPU，并且释放锁，加入到myWaitTest对象的等待锁定池
                    try {
                        myWaitTest.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getTime() + "线程A被唤醒，且重新获取到锁，执行完毕");
                }
            }
        };
    }


    public Thread getThreadB(final MyWaitTest myWaitTest) {
        return new Thread() {
            @Override
            public void run() {
                synchronized (myWaitTest) {
                    System.out.println(getTime() + "线程B开始执行任务");
                    System.out.println(getTime() + "唤醒myWaitTest对象的等待锁定池中的线程");

                    //唤醒myWaitTest对象的等待锁定池中的线程
                    myWaitTest.notify();

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getTime() + "线程B执行完成释放类锁，被唤醒的线程A获取锁后继续执行");
                }
            }
        };
    }
}
