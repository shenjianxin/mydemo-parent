package cn.com.mydesinmode.chainmode;

/**
 * Author:   shenjx
 * Date:     2018/4/20 9:28
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        Handler1 handler1=new Handler1();
        Handler2 handler2=new Handler2();
        Handler3 handler3=new Handler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        handler1.doChain();



    }

}
