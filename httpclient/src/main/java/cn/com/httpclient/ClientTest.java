package cn.com.httpclient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Author:   shenjx
 * Date:     2018/4/16 19:07
 * Description:
 */
public class ClientTest {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executorService.setKeepAliveTime(10,TimeUnit.SECONDS);
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Map map = new HashMap();
                    map.put("name", "张三" + tmp);
                    try {
                        String retStr = HttpUtils.sendHttp("http://127.0.0.1:6666/mymvc/test/mytest", map);
                        System.out.println(retStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //countDownLatch.countDown();
                }
            };
           executorService.execute(r);
        }

        //countDownLatch.await();
        //if (countDownLatch.getCount() == 0) {
           // executorService.shutdown();
        //}
        executorService.shutdown();


    }


}
