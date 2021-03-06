package cn.com.jdkdemo.myhashmap;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private int defaultLength = 1 << 4;

    private float defalutFactor = 0.75f;

    private Entry<K, V>[] table = null;

    public MyHashMap() {
        this(1 << 4, 0.75f);
    }

    public MyHashMap(int defaultLength, float defalutFactor) {
        this.defalutFactor = defalutFactor;
        this.defaultLength = defaultLength;
        this.table = new Entry[defaultLength];
    }


    private int hash(K k) {
        int m = defaultLength;
        System.out.println(k.hashCode());
        int i = k.hashCode() % m;
        System.out.println(i);
        return i >= 0 ? i : -i;
    }


    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new Entry<K, V>(k, v, null);
        } else {
            //先扩容在添加
            table[index] = new Entry<K, V>(k, v, entry);
        }
        return table[index].getValue();
    }


    @Override
    public V get(K k) {
        int index = hash(k);
        if (table[index] == null) {
            return null;
        }
        return find(k, table[index]);
    }

    private V find(K k, Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        }
        if (entry.next != null) {
            return find(k, entry.next);
        }
        return null;
    }


    @Override
    public int size() {
        return 0;
    }


    class Entry<K, V> implements MyMap.Entry<K, V> {

        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }


}
