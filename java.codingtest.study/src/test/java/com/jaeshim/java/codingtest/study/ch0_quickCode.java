package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

/**
 * 생각나는 것 빠르게 짜보기 위해 사용
 */
public class ch0_quickCode {

    @Test
    void p01() {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        int[] expect = {3, 9, 10, 27, 38, 43, 82};

        int[] actual = mergeSort(arr);
        int a = 4;
    }

    int[] mergeSort(int[] arr) {
        if(arr.length == 1) return arr;

        int mid = arr.length / 2;

        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(arr, left, right);
    }

    int[] merge(int[] arr, int[] left, int[] right) {
        int l = 0;
        int r = 0;
        int k = 0;

        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                arr[k++] = left[l++];
            } else {
                arr[k++] = right[r++];
            }
        }

        while (l < left.length) {
            arr[k++] = left[l++];
        }

        while (r < right.length) {
            arr[k++] = right[r++];
        }

        return arr;
    }


}
