package cn.com.mydesinmode.chainmode;

/**
 * Author:   shenjx
 * Date:     2018/4/20 9:26
 * Description:
 */
public class Handler3 extends AbstractHandler {
    @Override
    boolean doRequest() {
        System.out.println("王五开始处理请求");
        return false;
    }
}
