package cn.com.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * *解决CLOSE_WAIT的问题，造成many open file 实现方式
 * MultiThreadedHttpConnectionManager，类似jdbc连接池，他来管理连接，
 * 使用多线程的主要目的，是为了实现并行的下载。在httpclient运行的过程中，每个http协议的方法，
 * 使用一个HttpConnection实例。由于连接是一种有限的资源，每个连接在某一时刻只能供一个线程和方法使用，
 * 所以需要确保在需要时正确地分配连接。HttpClient采用了一种类似jdbc连接池的方法来管理连接， 这个管理工作由
 * MultiThreadedHttpConnectionManager完成。
 * 这里用到设计模式的单例模式，并且实现线程同步
 */
public class Http {
    private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

    private static HttpClient client = new HttpClient(connectionManager);

    private static Http http = null;

    private Http() {
        int maxThreadsTotal = 100;
        int maxThreadsPerHost = 5;
        // connectionManager.closeIdleConnections(1);
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setConnectionTimeout(10 * 1000);    //建立连接超时时长
        params.setSoTimeout(10 * 1000);    //取内容超时时长
        params.setMaxTotalConnections(maxThreadsTotal);
        params.setDefaultMaxConnectionsPerHost(maxThreadsPerHost);
        /**
        new Thread() {
            public void run() {
                while (!Thread.interrupted()) {
                    connectionManager.closeIdleConnections(10 * 1000);
                    try {
                        Thread.sleep(10 * 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
         **/
    }

    public synchronized HttpClient getClient() {
        return client;
    }

    private static synchronized void syncInit() {
        if (http == null) {
            http = new Http();
        }
    }


    public static Http getInstance() {
        if (http == null) {
            syncInit();
        }
        return http;
    }

}
