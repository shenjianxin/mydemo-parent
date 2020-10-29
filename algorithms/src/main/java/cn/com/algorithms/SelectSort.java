package cn.com.algorithms;

import java.util.Arrays;

/**
 * 算法思想：选择排序，从头至尾扫描序列，找出最小的一个元素，和第一个元素交换，接着从剩下的元素中继续这种选择和交换方式，最终得到一个有序序列。
 * 常见的选择排序可以分为直接选择排序（Straight selection sort）、树形选择排序（Tree-type selection sort）以及堆排序（Heap sort）。
 * （1）直接选择排序。①基本思想。实现思想是每步从排序记录中选出排序码最小（最大）的记录，放在已排序记录序列的最后（前）；
 *                  ②算法特点。直接选择排序算法n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果。
 * （2）树形选择排序。①基本思想。其实现思想是保存先前比较的结果以减少比较次数，是一种不稳定的排序方法。首先对n个记录的关键字进行两两比较，然后在n/2个较小者之间再进行两两比较，如此重复，直至选出最小的记录为止。
 * （3）堆排序。①基本思想。堆排序是一种树形选择排序，是对直接选择排序的有效改进；
 *             ②算法描述。从算法描述来看，堆排序需要两个过程，即建立堆和堆顶与堆的最后一个元素交换位置。所以堆排序有两个函数组成，一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数；
 *             ③算法特点。堆排序可通过树形结构保存部分比较结果，可减少比较次数。但由于建初始堆所需的比较次数较多，所以堆排序不适宜于记录数较少的文件。
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49};
        selectSort(arr, arr.length);
    }

    public static void selectSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            int j;
            // 找出最小值得元素下标
            for (j = i + 1; j < n; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int tmp = arr[index];
            arr[index] = arr[i];
            arr[i] = tmp;
            System.out.println(Arrays.toString(arr));
        }

    }
}
