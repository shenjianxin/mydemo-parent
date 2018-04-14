package cn.com.jdkdemo.mycollection;

import java.util.Arrays;

/**
 * Author:   shenjx
 * Date:     2018/4/9 14:03
 * Description:
 */
public class MyArrayList<E> {

    public MyArrayList() {
        size = 0;
    }


    private int size;

    public int size() {
        return size;
    }

    Object[] table = new Object[4];

    public void add(E t) {
        if (size >= table.length) {
            table = Arrays.copyOf(table, table.length * 2);
        }
        table[size] = t;
        size++;
    }

    public void set(int index, E object) throws Exception {
        if (index > -1 && index >= size) {
            throw new Exception("超出长度");
        }
        table[index] = object;
    }

    public E get(int index) throws Exception {
        if (index > -1 && index >= size) {
            throw new Exception("超出长度");
        }
        return (E) table[index];
    }

    public void clear() {
        table = new Object[4];
        size = 0;
    }

    public void removeAt(int index) throws Exception {
        if (index > -1 && index >= size) {
            throw new Exception("超出长度");
        }
        for (int i = index; i < size; i++) {
            if (i == size - 1) {
                table[i] = null;
            } else {
                table[i] = table[i + 1];
            }
        }
        size--;
    }


}
