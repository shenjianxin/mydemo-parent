package cn.com.jdkdemo.mylocktest.mysyntest;

/**
 * Author:   shenjx
 * Date:     2018/4/23 8:44
 * Description:方法同步锁
 */
public class MyMethodSynchronizedTest {

    public static void main(String[] args) {
        //main1();//非静态方法锁
        main2();//静态方法锁
    }

    //非静态对象方法锁
    public static void main1() {
        final MyMethodSynchronizedTest mySynchronizedTest = new MyMethodSynchronizedTest();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    mySynchronizedTest.nonStaticcMethod1(Thread.currentThread().getName() + "==>");
                }
            };
            t.setName("线程" + i);
            t.start();
        }

        new Thread() {
            @Override
            public void run() {
                mySynchronizedTest.nonStaticcMethod2(Thread.currentThread().getName() + "==>");
            }
        }.start();
    }

    //非静态方法锁
    public synchronized void nonStaticcMethod1(String ss) {
        System.out.println(ss + "====nonStaticcMethod1开始");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ss + "====nonStaticcMethod1结束");
        nonStaticcMethod2(ss);
    }

    //非静态方法锁
    public synchronized void nonStaticcMethod2(String ss) {
        System.out.println(ss + "====nonStaticcMethod2开始");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ss + "====nonStaticcMethod2结束");
    }

    //对象静态方法锁
    public static void main2() {
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    MyMethodSynchronizedTest mySynchronizedTest = new MyMethodSynchronizedTest();
                    mySynchronizedTest.staticMethod1(Thread.currentThread().getName() + "==>");
                }
            };
            t.setName("线程：" + i);
            t.start();
        }


        new Thread() {
            @Override
            public void run() {
                MyMethodSynchronizedTest mySynchronizedTest2 = new MyMethodSynchronizedTest();
                mySynchronizedTest2.staticMethod2(Thread.currentThread().getName() + "==>");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                MyMethodSynchronizedTest mySynchronizedTest2 = new MyMethodSynchronizedTest();
                mySynchronizedTest2.staticMethod1(Thread.currentThread().getName() + "==>");
            }
        }.start();
    }

    //方法锁
    public synchronized static void staticMethod1(String ss) {
        System.out.println(ss + "====staticMethod1开始");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ss + "====staticMethod1结束");
        staticMethod2(ss);
    }

    public synchronized static void staticMethod2(String ss) {
        System.out.println(ss + "====staticMethod2开始");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ss + "====staticMethod2结束");
    }


}
