package cn.com.mydesinmode.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author:   shenjx
 * Date:     2018/4/20 10:28
 * Description:代理类
 */
public class JdkProxyObject2 implements InvocationHandler {

    private boolean filter(Object proxy,Method method) {
        Method[] methods=proxy.getClass().getMethods();
        for (Method m:methods){
            if(method.getName().equals(m.getName())){
              return true;
            }
        }
        return false;
    }

    private Object targetObject;
    public JdkProxyObject2(Object targetObject){
        this.targetObject=targetObject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (filter(proxy,method)) {
            System.out.println("执行前22");

            Object object = method.invoke(targetObject, args);

            System.out.println("执行后22");
            return object;
        } else {
            return method.invoke(targetObject, args);
        }
    }
}
