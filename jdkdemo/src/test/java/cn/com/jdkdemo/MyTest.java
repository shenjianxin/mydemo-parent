package cn.com.jdkdemo;

import cn.com.jdkdemo.mycollection.MyArrayList;
import cn.com.jdkdemo.mycollection.MyLinkedList;
import org.junit.Test;

/**
 * Author:   shenjx
 * Date:     2018/4/14 14:09
 * Description:
 */
public class MyTest {

    @Test
    public void linkedDemo() throws Exception {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
/*        list.set(0, "1");
        list.set(1, "2");
        list.set(2, "3");*/
        //list.clear();
       /* list.removeAt(0);
        list.removeAt(0);
        list.removeAt(0);*/
        System.out.println(list.get(7));
    }
    @Test
    public void arrayDemo() throws Exception {
        MyArrayList<String> list = new MyArrayList();
        list.add("4");
        list.add("5");
        list.add("6");
        list.set(0, "2");
        //list.clear();
        list.removeAt(2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


}
