package cn.com.mydesinmode.chainmode;

/**
 * Author:   shenjx
 * Date:     2018/4/20 9:09
 * Description:
 */
public abstract class AbstractHandler {
    private AbstractHandler nextHandler;

    void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler=nextHandler;
    }

    abstract boolean doRequest();

    final void doChain() {
       if(this.doRequest()){
           nextHandler.doChain();
       }
    }

}
