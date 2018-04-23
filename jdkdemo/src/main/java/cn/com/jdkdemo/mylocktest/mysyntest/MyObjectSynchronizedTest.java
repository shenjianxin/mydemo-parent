package cn.com.jdkdemo.mylocktest.mysyntest;

/**
 * Author:   shenjx
 * Date:     2018/4/23 8:44
 * Description:对象锁
 */
public class MyObjectSynchronizedTest {

    //账户类
    class Account {
        public Account() {

        }

        private int opcnt;

        private int money = 0;

        public void add(int money) {
            this.money += money;
            opcnt++;
        }

        public void sub(int money) {
            this.money -= money;
            opcnt++;
        }

        public int getMoney() {
            return money;
        }

        public int getOpcnt() {
            return opcnt;
        }
        public void otherMethod() {
            sub(10);
            System.out.println("=======执行其他方法============");
        }

    }


    public static void main(String[] args) {
        new MyObjectSynchronizedTest().objectSynchronized();
    }





    //对象锁 正确用法
    public void objectSynchronized() {
        final MyObjectSynchronizedTest.Account account = new MyObjectSynchronizedTest.Account();
        //final CountDownLatch latch = new CountDownLatch(200);
        for (int i = 0; i < 10000; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    synchronized (account) {
                        account.add(10);
                    }
                    //latch.countDown();
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    synchronized (account) {
                        account.sub(10);
                    }
                    //latch.countDown();
                }
            }.start();
        }

        account.otherMethod();

        try {
            //latch.await();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("余额：" + account.getMoney());
        System.out.println("操作次数：" + account.getOpcnt());
    }


}
