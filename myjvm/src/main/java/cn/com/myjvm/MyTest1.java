package cn.com.myjvm;

/**
 * 当要实例化一个类时，JVM会首先加载该类，并且在加载过程中检查这个类是否有静态属性以及静态代码块，如果有，就按顺序分配内存并初始化他们，并且只在类加载的过程中初始化一次。
 * <p>
 * 对于构造代码块，以及普通属性，是在类实例化时进行的，并且每次实例化都会调用，并且构造代码块先于构造方法执行。
 */
public class MyTest1 {


    public MyTest1() {
        System.out.println(System.currentTimeMillis() + "构造方法");
    }
    {
        System.out.println(System.currentTimeMillis() + "构造代码块");
    }
    private String val = System.currentTimeMillis() + "普通属性";

    private static String staticVal = System.currentTimeMillis() + "静态属性";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("**************");
        MyTest1 myTest1 = new MyTest1();
        MyTest1 myTest2 = new MyTest1();
        Thread.sleep(1000);
        System.out.println("**************");


        System.out.println(MyTest1.staticVal);
        System.out.println(myTest1.val);
        System.out.println(myTest2.val);

    }


}
