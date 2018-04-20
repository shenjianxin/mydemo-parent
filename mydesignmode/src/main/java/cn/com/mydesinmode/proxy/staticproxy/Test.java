package cn.com.mydesinmode.proxy.staticproxy;

/**
 * Author:   shenjx
 * Date:     2018/4/20 10:34
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        TargetObject targetObject = new TargetObject();
        ProxyObject proxyObject = new ProxyObject(targetObject);
        proxyObject.hello();


    }


}
