package cn.com.mydesinmode.proxy.staticproxy;

/**
 * Author:   shenjx
 * Date:     2018/4/20 10:28
 * Description:被代理的实现类
 */
public class TargetObject implements ITargetObject{

    public String hello() {
        String ss = "hello!";
        System.out.println(ss);
        return ss;
    }

}
