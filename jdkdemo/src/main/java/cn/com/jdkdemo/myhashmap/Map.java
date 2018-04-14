package cn.com.jdkdemo.myhashmap;

public interface Map<K, V> {
    V get(V k);

    V put(K k, V v);

    int size();


    interface Entry<K, V> {
        K getKey();

        V getValue();

    }


}
