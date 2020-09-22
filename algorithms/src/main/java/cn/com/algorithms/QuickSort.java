package cn.com.algorithms;

/**
 * Copyright (C), 2018-2020, 广东领端科技有限公司
 * FileName: QuickSort
 * Author:   shenjx
 * Date:     2020/9/22 12:08
 * Description:
 */
public class QuickSort {

    private static int[] arr = {3, 5, 7, 8, 9, 2, 1, 4, 6};

    public static void main(String[] args) {
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " -> ");
        }
    }

    static void sort(int[] arrTmp, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arrTmp[left];

        int i = left;
        int j = right;
        while (i != j) {//j移动到i的位置，则终止
            while (arrTmp[j] >= base && i < j) {
                j--;
            }
            while (arrTmp[i] <= base && i < j) {
                i++;
            }

            if (i < j) {
                arrTmp[i] = arrTmp[i] ^ arrTmp[j];
                arrTmp[j] = arrTmp[i] ^ arrTmp[j];
                arrTmp[i] = arrTmp[i] ^ arrTmp[j];
            }
        }
        arrTmp[left] = arrTmp[i];
        arrTmp[i] = base;

        sort(arrTmp, left, i - 1);
        sort(arrTmp, i + 1, right);
    }


}
