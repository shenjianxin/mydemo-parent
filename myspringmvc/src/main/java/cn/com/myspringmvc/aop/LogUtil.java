package cn.com.myspringmvc.aop;

/**
 * Author:   shenjx
 * Date:     2018/4/17 16:00
 * Description:
 */
public class LogUtil {

    private final static ThreadLocal<Log> myLogThreadLocal = new ThreadLocal<>();

    public static Log getLog() {
        Log log = myLogThreadLocal.get();
        if (log == null) {
            System.out.println(Thread.currentThread().getId());
            myLogThreadLocal.set(new Log());
        }
        return log;
    }
}
