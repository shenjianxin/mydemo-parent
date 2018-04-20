package cn.com.mydesinmode.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author:   shenjx
 * Date:     2018/4/20 10:28
 * Description:代理类
 */
public class JdkProxyObject implements InvocationHandler {

    private boolean filter(Object proxy,Method method) {
        Method[] methods=proxy.getClass().getDeclaredMethods();
        for (Method m:methods){
            if(method.getName().equals(m.getName())){
              return true;
            }
        }
        return false;
    }

    private Object targetObject;
    public JdkProxyObject(Object targetObject){
        this.targetObject=targetObject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (filter(proxy,method)) {
            System.out.println("执行前");

            Object object = method.invoke(targetObject, args);

            System.out.println("执行后");
            return object;
        } else {
            return method.invoke(proxy, args);
        }
    }
}
