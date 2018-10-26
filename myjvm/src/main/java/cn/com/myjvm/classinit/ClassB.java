package cn.com.myjvm.classinit;

public class ClassB extends ClassA {


    static {
        System.out.println("4");
    }

    public ClassB() {
        System.out.println("5");
    }

    {
        System.out.println("6");
    }

    /**
     * 当要实例化一个类时，JVM会首先加载该类
     * 静态代码块，静态属性（代码先后顺序）==》静态方法
     * 实例化操作
     * 构造代码块，普通属性（代码先后顺序）==》构造方法
     * 对于父类以及子类中的情况
     * 会按照如下顺序加载，1、父类中的静态块、静态方法；2、子类中的静态块、静态方法；3、父类的构造块；4、父类的构造方法；5、子类的构造块；6、子类的构造方法。
     * 类实例化的一般过程是：父类的类构造器() -> 子类的类构造器() -> 父类的成员变量和实例代码块 -> 父类的构造函数 -> 子类的成员变量和实例代码块 -> 子类的构造函数。
     */

    public static void main(String[] args) {
        ClassA b = new ClassB();//2 4 1 3 6 5

    }


}
