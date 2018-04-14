package cn.com.jdkdemo.mysocket;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:   shenjx
 * Date:     2018/4/12 13:42
 * Description:c传统Socket
 */
public class MyIOSocket {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        Socket socket = serverSocket.accept();

        while (true) {
            InputStreamReader reader = new InputStreamReader(socket.getInputStream(), "GBK");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String acceptStr = bufferedReader.readLine();
            if (StringUtils.isNotEmpty(acceptStr)) {
                System.out.println(acceptStr);
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "GBK");
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("接收数据为：" + acceptStr + "\n");
                bufferedWriter.flush();
            }


        }

    }


}
