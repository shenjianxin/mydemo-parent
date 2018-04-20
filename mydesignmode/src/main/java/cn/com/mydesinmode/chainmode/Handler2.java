package cn.com.mydesinmode.chainmode;

/**
 * Author:   shenjx
 * Date:     2018/4/20 9:26
 * Description:
 */
public class Handler2 extends AbstractHandler{
    @Override
    boolean doRequest() {
        System.out.println("李四开始处理请求");
        return true;
    }
}
