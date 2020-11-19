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
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:" + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            JoinDemo joinDemo = new JoinDemo(previousThread, i);
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}