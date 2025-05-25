package com.jaeshim.java.codingtest.study.sort;

public class ch5_QuickSort {

    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 4, 2, 7, 1, 6};

        quickSort(nums, 0, nums.length - 1);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;

        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot - 1); // 왼쪽
        quickSort(arr, pivot + 1, end);   // 오른쪽
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end]; // 피벗: 마지막 원소
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
