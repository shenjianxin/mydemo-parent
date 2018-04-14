package cn.com.algorithms;

/**
 * Author:   shenjx
 * Date:     2018/4/14 15:26
 * Description:大数字乘法运算
 */
public class MyMultiplication {

    public static void main(String[] args) {
        int num1 = 9999;
        int num2 = 1111;

        int[] num1_int_arr = new int[4];
        num1_int_arr[num1_int_arr.length - 1] = 9;
        num1_int_arr[num1_int_arr.length - 2] = 9;
        num1_int_arr[num1_int_arr.length - 3] = 9;
        num1_int_arr[num1_int_arr.length - 4] = 9;


        int[] num2_int_arr = new int[4];
        num2_int_arr[num2_int_arr.length - 1] = 1;
        num2_int_arr[num2_int_arr.length - 2] = 1;
        num2_int_arr[num2_int_arr.length - 3] = 1;
        num2_int_arr[num2_int_arr.length - 4] = 1;


        int[] result1_arr = cal1(num1_int_arr, num2_int_arr);

        for (int tmp : result1_arr) {
            System.out.print(tmp);
        }
        System.out.println();
        System.out.println("num1*num2:");
        System.out.println(num1 * num2);
    }


    static int[] cal1(int[] num2_int_arr, int[] num1_int_arr) {
        int[] result1_arr=new int[num2_int_arr.length+num2_int_arr.length];
        int len = result1_arr.length;
        int len1 = num1_int_arr.length;
        int len2 = num2_int_arr.length;
        for (int j = num2_int_arr.length - 1; j >= 0; j--) {
            int n = 0;
            for (int i = num1_int_arr.length - 1; i >= 0; i--) {
                result1_arr[len - 1 - (len1 + len2 - (i + j + 2))] = result1_arr[len - 1 - (len1 + len2 - (i + j + 2))] + num2_int_arr[j] * num1_int_arr[i];
            }
        }
        int m = 10;
        for (int i = result1_arr.length - 1; i > 0; i--) {
            result1_arr[i - 1] += result1_arr[i] / m;
            result1_arr[i] = result1_arr[i] % m;
        }
        return result1_arr;
    }
}
