package cn.com.jdkdemo.myhashmap;

public interface MyMap<K, V> {
    V get(K k);

    V put(K k, V v);

    int size();


    interface Entry<K, V> {
        K getKey();

        V getValue();

    }


}
