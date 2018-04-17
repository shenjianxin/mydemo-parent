package cn.com.myspringmvc.aop;

/**
 * Author:   shenjx
 * Date:     2018/4/16 17:16
 * Description:
 */
public class Log {
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private String threadName;

    private Integer count;


    @Override
    public String toString() {
        return threadName+":"+count  ;
    }
}
