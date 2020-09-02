package cn.com.jdkdemo.mythread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * wait()、notify()、notifyAll()
 * Java提供了一种内联机制可以让线程在等待信号时进入非运行状态。当一个线程调用任何对象上的wait()方法时便会进入非运行状态，直到另一个线程调用同一个对象上的notify()或notifyAll()方法。
 * <p>
 * 为了能够调用一个对象的wait()、notify()方法，调用线程必须先获得这个对象的锁。因为线程只有在同步块中才会占用对象的锁，所以线程必须在同步块中调用wait()、notify()方法。
 * <p>
 * 我们把上面通过共享对象通信的例子改成调用对象wait()、notify()方法来实现：
 * <p>
 * 首先我们先构造一个任意对象，我们又把它称作监控对象：
 * <p>
 * 在这个例子中，线程C因调用了监控对象的wait()方法而挂起，线程D通过调用监控对象的notify()方法唤醒挂起的线程C。我们还可以看到，两个线程都是在同步块中调用的wait()和notify()方法。如果一个线程在没有获得对象锁的前提下调用了这个对象的wait()或notify()方法，方法调用时将会抛出 IllegalMonitorStateException异常。
 * <p>
 * 注意，当一个线程调用一个对象的notify()方法，则会唤醒正在等待这个对象所有线程中的一个线程（唤醒的线程是随机的），当线程调用的是对象的notifyAll()方法，则会唤醒所有等待这个对象的线程（唤醒的所有线程中哪一个会执行也是不确定的）。
 * <p>
 * 这里还有一个问题，既然调用对象wait()方法的线程需要获得这个对象的锁，那么这会不会阻塞其它线程调用这个对象的notify()方法呢？答案是不会阻塞，当一个线程调用监控对象的wait()方法时，它便会释放掉这个监控对象锁，以便让其它线程能够调用这个对象的notify()方法或者wait()方法。
 * <p>
 * 另外，当一个线程被唤醒时不会立刻退出wait()方法，只有当调用notify()的线程退出它的同步块为止。也就是说，被唤醒的线程只有重新获得监控对象锁时才会退出wait()方法，因为wait()方法在同步块中，它的执行需要再次获得对象锁。所以，当通过notifyAll()方法唤醒被阻塞的线程时，一次只能有一个线程会退出wait()方法，同样是因为每个线程都需要先获得监控对象锁才能执行同步块中的wait()方法退出。
 */
public class MyWaitTimeTest {


    public String getTime() {
        return new SimpleDateFormat("mm:ss:ms").format(new Date());
    }

    public static void main(String[] args) {


        MyWaitTimeTest myWaitTest = new MyWaitTimeTest();
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


    public Thread getThreadA(final MyWaitTimeTest myWaitTest) {
        return new Thread() {
            @Override
            public void run() {
                synchronized (myWaitTest) {
                    System.out.println(getTime() + "线程A开始执行任务");
                    System.out.println(getTime() + "线程A进入等待锁定池");

                    //让当前线程进入阻塞状态，交出CPU，并且释放锁，加入到myWaitTest对象的等待锁定池
                    try {
                        myWaitTest.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getTime() + "线程A被自动唤醒，且重新获取到锁，执行完毕");
                }
            }
        };
    }


    public Thread getThreadB(final MyWaitTimeTest myWaitTest) {
        return new Thread() {
            @Override
            public void run() {
                synchronized (myWaitTest) {//如果这里不做该对象同步锁，那么线程A到时间自动唤醒第一时间将获取到该对象的锁并执行
                    System.out.println(getTime() + "线程B获取对象锁开始执行任务");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getTime() + "线程B执行完成释放类锁，自动唤醒的线程A将获取锁后继续执行");
                }
            }
        };
    }
}
