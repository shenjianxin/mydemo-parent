package cn.com.jdkdemo;

import cn.com.jdkdemo.myhashmap.MyHashMap;
import org.junit.Test;

import java.util.HashMap;

/**
 * Author:   shenjx
 * Date:     2018/4/20 17:55
 * Description:
 */
public class MyHashMapTest {

    @Test
    public void myHashMapTest(){
        try {
            MyHashMap myHashMap1 = new MyHashMap();
            myHashMap1.put("qqq","111");
            myHashMap1.get("122");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void hashMapTest(){
        HashMap myHashMap1=new HashMap();
        myHashMap1.put(null,"张三");
        myHashMap1.put(null,"李四");

        System.out.println();

        HashMap myHashMap2=new HashMap();
        myHashMap2.put("z","张三");
        myHashMap2.put("l","李四");

        System.out.println();

    }


}
