package cn.com.myjvm;

/**
 * 当要实例化一个类时，JVM会首先加载该类，并且在加载过程中检查这个类是否有静态属性以及静态代码块，如果有，就按顺序分配内存并初始化他们，并且只在类加载的过程中初始化一次。
 * <p>
 * 对于构造代码块，以及普通属性，是在类实例化时进行的，并且每次实例化都会调用，并且构造代码块先于构造方法执行。
 *
 *
 * 会按照如下顺序加载，1、父类中的静态块、静态方法；2、子类中的静态块、静态方法；3、父类的构造块；4、父类的构造方法；5、子类的构造块；6、子类的构造方法。
 */
public class MyTest1 {
    private static String staticVal1 = System.currentTimeMillis() + "静态属性1";
    static {
        System.out.println(System.currentTimeMillis() + "静态代码块1");
    }

    static {
        System.out.println(System.currentTimeMillis() + "静态代码块2");
    }
    private static String staticVal2 = System.currentTimeMillis() + "静态属性2";


    private String val1 = System.currentTimeMillis() + "普通属性1";
    {
        System.out.println(System.currentTimeMillis() + "构造代码块2");
    }
    private String val3 = System.currentTimeMillis() + "普通属性3";
    public MyTest1() {
        System.out.println(System.currentTimeMillis() + "构造方法");
    }
    private String val4 = System.currentTimeMillis() + "普通属性4";
    {
        System.out.println(System.currentTimeMillis() + "构造代码块1");
    }


    private String val2 = System.currentTimeMillis() + "普通属性2";




    public static void main(String[] args) throws InterruptedException {
        System.out.println("**************");
        MyTest1 myTest1 = new MyTest1();
        MyTest1 myTest2 = new MyTest1();
        Thread.sleep(1000);
        System.out.println("**************");


        System.out.println(MyTest1.staticVal1);
        System.out.println(MyTest1.staticVal2);


    }


}
