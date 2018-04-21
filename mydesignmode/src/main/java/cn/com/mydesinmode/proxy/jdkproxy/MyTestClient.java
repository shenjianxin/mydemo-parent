package cn.com.mydesinmode.proxy.jdkproxy;

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
        try {
            JdkProxyObject jdkProxyObject = new JdkProxyObject(new TargetObject());
            ITargetObject iTargetObject = (ITargetObject) Proxy.newProxyInstance(TargetObject.class.getClassLoader(), TargetObject.class.getInterfaces(), jdkProxyObject);
            iTargetObject.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //多层动态代理
    public static void main2() {
        try {
            JdkProxyObject jdkProxyObject = new JdkProxyObject(new TargetObject());
            ITargetObject iTargetObject = (ITargetObject) Proxy.newProxyInstance(TargetObject.class.getClassLoader(), TargetObject.class.getInterfaces(), jdkProxyObject);

            JdkProxyObject2 jdkProxyObject2 = new JdkProxyObject2(iTargetObject);
            iTargetObject = (ITargetObject) Proxy.newProxyInstance(TargetObject.class.getClassLoader(), TargetObject.class.getInterfaces(), jdkProxyObject2);

            iTargetObject.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
