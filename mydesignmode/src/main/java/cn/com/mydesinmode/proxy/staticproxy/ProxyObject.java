package cn.com.mydesinmode.proxy.staticproxy;

/**
 * Author:   shenjx
 * Date:     2018/4/20 10:28
 * Description:代理类
 */
public class ProxyObject implements ITargetObject {

    private ITargetObject iTargetObject;

    public ProxyObject(ITargetObject iTargetObject) {
        this.iTargetObject = iTargetObject;
    }

    public String hello() {
        System.out.println("执行前");
        String ss = iTargetObject.hello();
        System.out.println("执行后");
        return ss;
    }

}
