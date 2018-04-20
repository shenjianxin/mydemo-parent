package cn.com.mydesinmode.proxy.cglibproxy;

import cn.com.mydesinmode.proxy.jdkproxy.ITargetObject;

/**
 * Author:   shenjx
 * Date:     2018/4/20 10:28
 * Description:被代理的类，无需实现类
 */
public class TargetObject {

    public String hello() {
        String ss = "hello!";
        System.out.println(ss);
        return ss;
    }

}
