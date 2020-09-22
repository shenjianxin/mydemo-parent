package cn.com.algorithms;

/**
 * Copyright (C), 2018-2020, 广东领端科技有限公司
 * FileName: MyMergeSort
 * Author:   shenjx
 * Date:     2020/9/22 12:08
 * Description:
 */
public class MyMergeSort {


    public static void main(String[] args) {
        int[] arr = {4, 7, 5, 2, 6, 3};
        div(arr, 0, arr.length - 1);


        for (int i : arr) {
            System.out.print(i + " -> ");
        }
    }


    public static void div(int[] arr, int s, int e) {
        if (s < e) {
            int mid = (s + e) / 2;
            div(arr, s, mid);
            div(arr, mid + 1, e);
            merge(arr, s, mid, e);
        }
        return;
    }

    public static void merge(int[] arr, int s, int m, int e) {
        int[] tmpArr = new int[e - s + 1];
        int k = 0;
        int i = s;
        int j = m + 1;
        while (i <= m && j <= e) {
            if (arr[i] < arr[j]) {
                tmpArr[k++] = arr[i++];
            } else {
                tmpArr[k++] = arr[j++];
            }
        }

        while (i <= m) {
            tmpArr[k++] = arr[i++];
        }
        while (j <= e) {
            tmpArr[k++] = arr[j++];
        }

        for (int i1 = 0; i1 < tmpArr.length; i1++) {
            arr[s + i1] = tmpArr[i1];
        }


    }


}
