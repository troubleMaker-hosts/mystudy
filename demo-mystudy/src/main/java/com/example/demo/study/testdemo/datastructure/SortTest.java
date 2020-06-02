package com.example.demo.study.testdemo.datastructure;


import java.util.Arrays;

/**
 * @ClassName: SortTest
 * @Description: SortTest 排序(多种排序方法) 方法 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class SortTest {
    public static void main(String[] args) {
        // 16 个数字
        int[] ints = {12, 3, 43, 53, 5242, 123, 534, 1, 44, 241, 5, 8, 456, 89, 88, 203};
        //int[] ints = {1,2,3,4,5,6,7,8,9} ;
        //int[] ints = {2,1};
        //int[] ints = {1,2};
        System.out.println(Arrays.toString(ints));
        System.out.println(ints.length);
        //直接插入排序
        //ints = directInsertionSort(ints) ;
        //System.out.println(Arrays.toString(ints));

        //shell排序
        //ints = shellSort(ints) ;

        //冒泡排序
        ints = bubbleSort(ints);
        //快速排序
        //ints = quickSort(ints , 0, ints.length - 1 ) ;
        //堆排序
        //ints = heapSort(ints) ;
        //并归排序
        //mergeSort(ints , 0 , ints.length - 1 ) ;
        System.out.println(Arrays.toString(ints));

        //ints = bubbleSort(ints) ;
    }

    /**
     * 直接插入排序
     * @param ints 目标数组(需要排序的数组)
     * @return  排序之后的数组
     */
    private static int[] directInsertionSort(int[] ints) {
        int count = 0;
        for (int i = 1; i < ints.length; i++) {
            int t = ints[i];
            int j = i - 1;
            while (j >= 0 && ints[j] > t) {
                ints[j + 1] = ints[j];
                j = j - 1;
                count++;
            }
            System.out.println(j + 1);
            ints[j + 1] = t;
            System.out.println(Arrays.toString(ints));

        }
        System.out.println("总共比较次数 = " + count);

        return ints;
    }

    /**
     * shell排序(错误)
     *
     * @param ints 目标数组
     * @return 排序之后的数组
     */
    private static int[] shellSort(int[] ints) {
        //初始集合间隔长度(每组长度)
        int dataLength = ints.length / 12;
        System.out.println(dataLength);
        //分组
        for (; dataLength != 0; dataLength = dataLength / 12) {
            //对每组之间的每个相对应的元素进行比较
            for (int j = dataLength; j < ints.length; j++) {
                int temp = ints[j];
                //计算进行处理的位置
                int pointer = j - dataLength;
                //直接插入排序
                while (pointer >= 0 && pointer < ints.length
                        && temp < ints[pointer]) {
                    ints[pointer + dataLength] = ints[pointer];
                    pointer = pointer - dataLength;
                }
                ints[pointer + dataLength] = temp;
                System.out.println(Arrays.toString(ints));
            }
        }


        return ints;
    }

    /**
     * 冒泡 排序
     *
     * @param ints 目标数组
     * @return  排序之后的有序数组
     */
    private static int[] bubbleSort(int[] ints) {
        System.out.println("冒泡排序");
        System.out.println(Arrays.toString(ints));
        System.out.println("进来了");
        int count = 0;

        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = ints.length - 1; j > i; j--) {
                System.out.println(++count);
                if (ints[j] < ints[j - 1]) {
                    int temp = ints[j - 1];
                    ints[j - 1] = ints[j];
                    ints[j] = temp;
                }
            }
        }
        return ints;
    }

    /**
     * 快速排序(前后指针法)
     *
     * @param ints  目标数组
     * @param left  左游标
     * @param right 右游标
     * @return  排序之后的数组
     */
    private static int[] quickSort(int[] ints, int left, int right) {
        if (left >= right) {
            return ints;
        }
        int point = ints[right];
        int current = left;
        int previous = left - 1;
        int temp;
        while (current < right) {
            while (ints[current] < point && ++previous != current) {
                temp = ints[current];
                ints[current] = ints[previous];
                ints[previous] = temp;
            }
            current++;
        }
        previous++;
        temp = ints[previous];
        ints[previous] = ints[right];
        ints[right] = temp;
        quickSort(ints, left, previous - 1);
        quickSort(ints, previous + 1, right);
        return ints;
    }

    /**
     * 堆排序
     * @param ints 目标数组
     * @return  排序之后的数组
     */
    private static int[] heapSort(int[] ints) {
        //构建 大根堆
        for (int i = ints.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子节点 从下至上， 从右至左 调整结构
            adjustHeap(ints, i, ints.length);
        }
        //调整 堆结构 + 交换 堆顶元素 与末尾元素
        for (int j = ints.length - 1; j > 0; j--) {
            int t = ints[0];
            ints[0] = ints[j];
            ints[j] = t;
            adjustHeap(ints, 0, j);
        }
        return ints;
    }

    /**
     * 堆调整
     * @param ints  目标数组
     * @param index 下标
     * @param length    长度
     * @return  调整之后的数组
     */
    public static int[] adjustHeap(int[] ints, int index, int length) {
        //拿到当前元素
        int temp = ints[index];
        //从index 节点的左子结点开始（2index + 1）
        for (int k = index * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点 小于 右子节点 ， k 指向右子节点
            if (k + 1 < length && ints[k] < ints[k + 1]) {
                k++;
            }
            //如果子节点大于父节点， 将子节点赋值给父节点（不用进行交换）
            if (ints[k] > temp) {
                ints[index] = ints[k];
                index = k;
            } else {
                break;
            }
        }
        //将 temp 值 放到最终位置
        ints[index] = temp;
        return ints;
    }

    /**
     * 并归排序 (迭代算法)
     * @param ints 目标数组
     * @param left  左点下标
     * @param right 右点下标
     */
    public static void mergeSort(int[] ints, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(ints, left, mid);
            mergeSort(ints, mid + 1, right);
            mergeArray(ints, left, mid, right);
        }
        //return  ints ;
    }

    /**
     * 数组二合一（合并两个数组）
     * @param ints 数组
     * @param left  左点
     * @param mid   中间点
     * @param right 右点
     */
    private static void mergeArray(int[] ints, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        //测试
        //System.out.println(left +"     "+ mid +"    "+ right); 测试
        int[] temp = new int[ints.length];
        int k = 0;
        while (i <= mid && j <= right) {
            if (ints[i] <= ints[j]) {
                temp[k++] = ints[i++];
            } else {
                temp[k++] = ints[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = ints[i++];
        }
        while (j <= right) {
            temp[k++] = ints[j++];
        }

        //因为上面用的是K++
        for (i = 0; i < k; i++) {
            ints[left + i] = temp[i];
        }
        //return  ints ;
    }

}
