package cn.com.mydesinmode.proxy.cglibproxy;


import java.lang.reflect.Proxy;

/**
 * Author:   shenjx
 * Date:     2018/4/20 11:45
 * Description:
 */
public class MyTestClient {


    public static void main(String[] args) {
        main1();
    }

    //单层动态代理
    public static void main1() {
        TargetObject targetObject=new TargetObject();
        DefaultMethodInterceptor defaultMethodInterceptor=new DefaultMethodInterceptor();
        CglibProxyObject cglibProxyObject=new CglibProxyObject(defaultMethodInterceptor);
        TargetObject targetObject1=(TargetObject)cglibProxyObject.getTargetInstance(targetObject);
        targetObject1.hello("张三");
    }


}
