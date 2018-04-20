package cn.com.mydesinmode.chainmode;

/**
 * Author:   shenjx
 * Date:     2018/4/20 9:26
 * Description:
 */
public class Handler1 extends AbstractHandler{
    @Override
    boolean doRequest() {
        System.out.println("张三开始处理请求");
        return true;
    }
}
