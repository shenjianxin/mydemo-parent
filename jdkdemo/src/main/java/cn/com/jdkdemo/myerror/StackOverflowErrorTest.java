package cn.com.jdkdemo.myerror;

/**
 * Copyright (C), 2018-2020, 广东领端科技有限公司
 * FileName: StackOverflowErrorTest
 * Author:   shenjx
 * Date:     2020/9/1 17:39
 * Description:
 */
public class StackOverflowErrorTest {
    public static void main(String[] args) {
        recursion(0);
    }

    public static long recursion(long i){
        try {
            return recursion(++i);
        }catch (Throwable e){
            System.out.println(i);
            throw e;
        }
    }

}
