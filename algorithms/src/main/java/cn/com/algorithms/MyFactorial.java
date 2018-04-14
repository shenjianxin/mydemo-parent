package cn.com.algorithms;

/**
 * Author:   shenjx
 * Date:     2018/4/14 15:25
 * Description:阶乘
 */
public class MyFactorial {
    public static void main(String[] args) {
        //50!
        int[] num1_int_arr = new int[500];
        num1_int_arr[num1_int_arr.length - 1] = 1;
        //50!
        int n = 100;
        for (int i = 1; i <= n; i++) {
            num1_int_arr = cal1(num1_int_arr, i);
        }
        for (int tmp : num1_int_arr) {
            System.out.print(tmp);
        }
    }


    static int[] cal1(int[] num1_int_arr, int num2) {
        for (int i = 0; i < num1_int_arr.length; i++) {
            num1_int_arr[i] = num1_int_arr[i] * num2;
        }
        int m = 10;
        for (int i = num1_int_arr.length - 1; i > 0; i--) {
            num1_int_arr[i - 1] += num1_int_arr[i] / m;
            num1_int_arr[i] = num1_int_arr[i] % m;
        }
        return num1_int_arr;
    }
}
