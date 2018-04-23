package cn.com.jdkdemo.mylocktest.mysyntest;

/**
 * Author:   shenjx
 * Date:     2018/4/23 8:44
 * Description:方法同步锁
 */
public class MyMethodSynchronizedTest {

    public static void main(String[] args) {
        main1();//非静态方法锁
        main2();//静态方法锁
    }

    //非静态对象方法锁
    public static void main1() {
        final MyMethodSynchronizedTest mySynchronizedTest = new MyMethodSynchronizedTest();
        new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    mySynchronizedTest.nonStaticcMethod1(i);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    mySynchronizedTest.nonStaticcMethod1(i);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                MyMethodSynchronizedTest mySynchronizedTest2 = new MyMethodSynchronizedTest();
                for (int i = 0; i < 5; i++) {
                    mySynchronizedTest2.nonStaticcMethod2(i);
                }
            }
        }.start();
    }

    //非静态方法锁
    public synchronized void nonStaticcMethod1(int i) {
        System.out.println(Thread.currentThread().getName() + "：" + i);
    }

    //非静态方法锁
    public synchronized void nonStaticcMethod2(int i) {
        System.out.println("method2:" + Thread.currentThread().getName() + "：" + i);
    }

    //对象静态方法锁
    public static void main2() {
        new Thread() {
            @Override
            public void run() {
                MyMethodSynchronizedTest mySynchronizedTest = new MyMethodSynchronizedTest();
                for (int i = 0; i < 5; i++) {
                    mySynchronizedTest.staticMethod1(i);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                MyMethodSynchronizedTest mySynchronizedTest = new MyMethodSynchronizedTest();
                for (int i = 0; i < 5; i++) {
                    mySynchronizedTest.staticMethod1(i);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                MyMethodSynchronizedTest mySynchronizedTest2 = new MyMethodSynchronizedTest();
                for (int i = 0; i < 5; i++) {
                    mySynchronizedTest2.staticMethod2(i);
                }
            }
        }.start();
    }

    //方法锁
    public synchronized static void staticMethod1(int i) {
        System.out.println(Thread.currentThread().getName() + "：" + i);
    }

    public synchronized static void staticMethod2(int i) {
        System.out.println("method2:" + Thread.currentThread().getName() + "：" + i);
    }


}
