package cn.com.jdkdemo.mylocktest.mylocktest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:   shenjx
 * Date:     2018/4/23 12:50
 * Description:
 */
public class MyRentrant {
    class Account {

        private int money;

        public void add(int m) {
            System.out.println(Thread.currentThread().getName() + "存钱开始...");
            this.money += m;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "存钱完成...");

        }

        public void sub(int m) {
            System.out.println(Thread.currentThread().getName() + "取钱开始...");
            this.money -= m;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "取钱完成...");
        }

        public int getMoney() {
            return money;
        }
    }


    public static void main(String[] args) {
        MyRentrant myRentrant = new MyRentrant();
        myRentrant.main1();
    }


    public void main1() {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Account account = new Account();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        reentrantLock.lock();
                        account.add(10);
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            };
            t.setName("线程" + i);
            t.start();
        }
    }


}
