package cn.com.mydesinmode.proxy.cglibproxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * Author:   shenjx
 * Date:     2018/4/20 14:46
 * Description:
 */
public class CglibProxyObject {
    private Callback callback;

    public CglibProxyObject(Callback callback) {
        this.callback = callback;
    }


    public Object getTargetInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(callback);
        enhancer.setUseCache(true);

        return enhancer.create();
    }


}
