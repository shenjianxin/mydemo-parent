package cn.com.myjvm.classinit;

public class ClassA {


    static {
        System.out.println("2");
    }

    public ClassA() {
        System.out.println("3");
    }

    {
        System.out.println("1");
    }
}
