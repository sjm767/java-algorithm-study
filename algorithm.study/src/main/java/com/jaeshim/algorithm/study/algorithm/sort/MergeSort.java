package com.jaeshim.algorithm.study.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;

        int mid = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        int[] mergedArr = new int[arr.length];
        int m = 0, l = 0, h = 0;
        while (l < left.length && h < right.length) {
            if (left[l] < right[h])
                mergedArr[m++] = left[l++];
            else
                mergedArr[m++] = right[h++];
        }

        // 남은 원소 추가
        while (l < left.length) {
            mergedArr[m++] = left[l++];
        }
        while (h < right.length) {
            mergedArr[m++] = right[h++];
        }
        return mergedArr;
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 1, 8, 7, 2, 4};

        int[] answer = mergeSort(arr);
        for (int a : answer) {
            System.out.print(a+" ");
        }
    }
}
