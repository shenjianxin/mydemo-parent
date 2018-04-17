package cn.com.jdkdemo.myconcurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:   shenjx
 * Date:     2018/4/15 17:27
 * Description:
 */
public class MyConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap map=new ConcurrentHashMap();
        map.put("张三","11");
    }
}
