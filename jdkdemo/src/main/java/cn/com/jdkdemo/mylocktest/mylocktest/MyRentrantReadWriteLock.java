package cn.com.jdkdemo.mylocktest.mylocktest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.SimpleFormatter;

/**
 * Author:   shenjx
 * Date:     2018/4/23 12:50
 * Description:
 */
public class MyRentrantReadWriteLock {
    class Account {

        private int opcnt;

        private int money;

        public void add(int m) {
            System.out.println(new SimpleDateFormat("mm:ss").format(new Date()) + "==>" + Thread.currentThread().getName() + "存钱开始>>");
            this.money += m;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            opcnt++;
            System.out.println(new SimpleDateFormat("mm:ss").format(new Date()) + "==>" + Thread.currentThread().getName() + "存钱完成<<");

        }

        public int getMoney() {
            System.out.println(new SimpleDateFormat("mm:ss").format(new Date()) + "==>" + Thread.currentThread().getName() + "查询余额开始>>");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("mm:ss").format(new Date()) + "==>第" + opcnt + "次存钱后余额：" + money);
            return money;
        }
    }


    public static void main(String[] args) {
        MyRentrantReadWriteLock myRentrant = new MyRentrantReadWriteLock();
        myRentrant.main1();
    }


    public void main1() {
        final ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        final Account account = new Account();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        reentrantLock.writeLock().lock();
                        account.add(10);
                    } finally {
                        reentrantLock.writeLock().unlock();
                    }
                }
            };
            t.setName("线程write" + i);
            t.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        reentrantLock.readLock().lock();
                        account.getMoney();
                    } finally {
                        reentrantLock.readLock().unlock();
                    }
                }
            };
            t.setName("线程read" + i);
            t.start();
        }

    }


}
