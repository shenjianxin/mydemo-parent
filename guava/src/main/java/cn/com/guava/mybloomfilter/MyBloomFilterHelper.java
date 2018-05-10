package cn.com.guava.mybloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

public class MyBloomFilterHelper {

    private static final int insertions = 1000000;

    public static void main(String[] args) {
        //默认误判率 0.03
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, 0.01);
        Set<String> sets = new HashSet<>(insertions);
        List<String> lists = new ArrayList<>(insertions);
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int right = 0;
        int wrong = 0;
        for (int i = 0; i < 10000; i++) {
            String test = i % 100 == 0 ? lists.get(i) : UUID.randomUUID().toString();
            if (bf.mightContain(test)) {//布隆过滤器说有不一定有
                if (sets.contains(test)) {
                    right++;
                } else {
                    wrong++;
                }
            } else {//说没有一定是没有
                if (sets.contains(test)) {
                    System.out.println("不会出现此打印");
                    wrong++;
                } else {
                    right++;
                }
            }
        }


        System.out.println("=======right=======" + right);
        System.out.println("=======wrong=======" + wrong);


    }


}
