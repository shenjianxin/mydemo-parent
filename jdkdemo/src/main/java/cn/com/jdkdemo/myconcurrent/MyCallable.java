package cn.com.jdkdemo.myconcurrent;

import java.util.concurrent.*;

/**
 * Author:   shenjx
 * Date:     2018/4/10 9:12
 * Description:
 */
public class MyCallable {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyRunable myRunable = new MyRunable(new Person("张三", 18, "1444"));
        Future<Person> future1 = executorService.submit(myRunable);
        MyRunable myRunable2 = new MyRunable(new Person("李四", 19, "1444"));
        Future<Person> future2 = executorService.submit(myRunable2);

        try {
            Person person1 = future1.get();
            Person person2 = future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private static class MyRunable implements Callable<Person> {
        private Person p;

        public MyRunable(Person p) {
            this.p = p;
        }

        @Override
        public Person call() throws Exception {
            System.out.println(p.getAge());
            p.setAge(22);
            return p;
        }
    }
}
