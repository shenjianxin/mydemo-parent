package cn.com.myjvm;

/**
 * 通过将这个单实例的引用变量定义在静态内部类中，来实现单例，这样可以做到不用if条件进行判断，并且是多线程安全的(由jvm保证)
 */
public class MyNestClass {
    private static class NestClass {
        private static MyNestClass instance;
        static {
            System.out.println("instance = new SingletonTest()");
            instance = new MyNestClass();
        }
    }

    // 不能直接new
    private MyNestClass() {
        System.out.println("private SingletonTest()");
    }

    public static MyNestClass getInstance() {
        System.out.println("SingletonTest getInstance()");
        return NestClass.instance;
    }

    public static void main(String[] args) {
        MyNestClass instance = MyNestClass.getInstance();
        System.out.println("========================================");
        MyNestClass instance01 = MyNestClass.getInstance();
        System.out.println("========================================");
        MyNestClass instance02 = MyNestClass.getInstance();
    }
}
