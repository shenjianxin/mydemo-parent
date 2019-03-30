package cn.com.mydesinmode.proxy.jdkproxy;

public class IAaa implements Bbb.Icc, Bbb.Idd, Bbb.Iff, Bbb.Iee {


    @Override
    public void hello1(String ss) {

    }

    @Override
    public void hello1() {

    }

    @Override
    public void hello3() {
        throw new NullPointerException();
    }
}
