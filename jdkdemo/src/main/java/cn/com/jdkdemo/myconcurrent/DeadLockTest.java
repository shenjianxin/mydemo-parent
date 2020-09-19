package cn.com.jdkdemo.myconcurrent;

/**
 * Copyright (C), 2018-2020, 广东领端科技有限公司
 * FileName: DeadLockTest
 * Author:   shenjx
 * Date:     2020/9/10 18:44
 * Description:
 */
public class DeadLockTest {

    private final static Object m1=new Object();
    private final static Object m2=new Object();



    private void aa(){
        synchronized (m1){
            System.out.println("aa获取到m1对象锁");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            synchronized (m2){
//                System.out.println("aa获取到m2对象锁");
//                System.out.println("aa");
//            }
            System.out.println("aa");
        }
    }

    private void bb(){
        synchronized (m2){
            System.out.println("bb获取到m2对象锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (m1){
                System.out.println("bb获取到m1对象锁");
                System.out.println("bb");
            }
        }
    }

    public static void main(String[] args) {

        final DeadLockTest test=new DeadLockTest();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                test.aa();
            }
        });


        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                test.bb();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("结束");


    }


}
