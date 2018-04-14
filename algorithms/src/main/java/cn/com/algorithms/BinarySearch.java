package cn.com.algorithms;

import java.util.Arrays;

/**
 * Author:   shenjx
 * Date:     2018/4/8 13:40
 * Description:插入排序与二分查找法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] ints = new int[100];
        for (int i = 0; i < 100; i++) {
            ints[i] = (int) (System.nanoTime() % 100);
        }

        sort(ints);
        System.out.println(Arrays.toString(ints));
        System.out.println("首次出现位置下标：" + binarySearch(ints, 50));
    }

    static void sort(int[] ints) {
        //从小到大排序
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] < ints[i - 1]) {//插入排序
                for (int j = i - 1; j >= 0; j--) {
                    if (ints[j + 1] < ints[j]) {
                        ints[j + 1] = ints[j + 1] ^ ints[j];
                        ints[j] = ints[j + 1] ^ ints[j];
                        ints[j + 1] = ints[j + 1] ^ ints[j];
                    } else {
                        break;
                    }
                }
            }
        }
    }

    static int binarySearch(int[] ints, int search) {
        int start = 0;
        int end = ints.length - 1;
        int count = 0;
        try {
            while (true) {
                count++;
                if (start > end) {
                    return -1;
                }
                int mid = (start + end) / 2;
                if (search < ints[mid]) {
                    end = mid - 1;
                } else if (search > ints[mid]) {
                    start = mid + 1;
                } else {
                    //如果有相同的数据，找到第一个的下标
                    for (int i = mid; mid > 0 && i > 0; i--) {
                        count++;
                        if (search > ints[i - 1]) {
                            return i;
                        }
                        if (i - 1 == 0) {
                            return 0;
                        }
                    }
                    return mid;
                }
            }
        } finally {
            System.out.println("循环了：" + count + "次");
        }
    }


}
