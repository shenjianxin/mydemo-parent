package cn.com.jdkdemo.mythread;

//Java中如何让多线程按照自己指定的顺序执行？
public class JoinDemo extends Thread {
    int i;

    Thread previousThread; //上一个线程

    public JoinDemo(Thread previousThread, int i) {
        this.previousThread = previousThread;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            //调用上一个线程的join方法
            //thread.join的含义是当前线程需要等待previousThread线程终止之后才从thread.join返回。简单来说，就是线程没有执行完之前，会一直阻塞在join方法处。
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:" + i + ",上一个线程执行完成后，释放自身的锁，当前线程从join阻塞进入执行阶段");
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            JoinDemo joinDemo = new JoinDemo(previousThread, i);
            joinDemo.start();
            previousThread = joinDemo;
        }
        System.out.println("主线程最先执行完，才会释放锁，让previousThread获取锁进入wait状态");
    }
}