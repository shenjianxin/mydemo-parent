package cn.com.mydesinmode.proxy.jdkproxy;

public class Bbb {


    public interface Icc {
        void hello1(String ss);
    }

    protected interface Idd {
        void hello1();
    }

    protected interface Iee {
        void hello3() throws ClassCastException;
    }

    protected interface Iff {
        void hello3() throws NullPointerException;
    }

}
