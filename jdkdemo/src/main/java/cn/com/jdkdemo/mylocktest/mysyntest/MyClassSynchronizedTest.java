package cn.com.jdkdemo.mylocktest.mysyntest;

/**
 * Author:   shenjx
 * Date:     2018/4/23 8:44
 * Description:类锁
 */
public class MyClassSynchronizedTest {

    //账户类
    class Account {
        public Account() {

        }

        private int opcnt;

        private int money = 0;

        public void add(int index, int money) {
            System.out.println(index + "存钱开始...");
            this.money += money;
            opcnt++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(index + "存钱结束...");
        }

        public void sub(int index, int money) {
            System.out.println(index + "取钱开始...");
            this.money -= money;
            opcnt++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(index + "取钱结束...");
        }

        public int getMoney() {
            return money;
        }

        public int getOpcnt() {
            return opcnt;
        }

        public void otherMethod() {
            sub(-55, 10);
        }
    }


    public static void main(String[] args) {
        new MyClassSynchronizedTest().classSynchronized();
        //new MyClassSynchronizedTest().objectSynchronized();
    }

    //类锁
    public void classSynchronized() {
        final Account account = new Account();
        new Thread() {
            @Override
            public void run() {
                synchronized (account.getClass()) {
                    account.add(-1, 10);
                }
            }
        }.start();
        for (int i = 0; i < 5; i++) {
            final Account account1 = new Account();
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    synchronized (Account.class) {
                        account1.sub(index, 10);
                    }
                }
            }.start();
        }

        final Account account2 = new Account();
        new Thread() {
            @Override
            public void run() {
                account2.otherMethod();
            }
        }.start();
    }


    //对象锁
    public void objectSynchronized() {
        final Account account = new Account();
        new Thread() {
            @Override
            public void run() {
                synchronized (account) {
                    account.add(-1, 10);
                }
            }
        }.start();

        final Account account1 = new Account();
        for (int i = 0; i < 5; i++) {
            //多个对象
            //final Account account1 = new Account();
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    synchronized (account1) {
                        account1.sub(index, 10);
                    }
                }
            }.start();
        }
    }


}
