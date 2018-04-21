package cn.com.mydesinmode.proxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Author:   shenjx
 * Date:     2018/4/21 13:13
 * Description:
 */
public class DefaultMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前");
        Object retObject = methodProxy.invokeSuper(o, objects);
        System.out.println("执行后");
        return retObject;
    }
}
