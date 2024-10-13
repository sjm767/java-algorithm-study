package com.jaeshim.algorithm.study.algorithm.sort;

public class QuickSort {

    static int partition (int[] arr, int p, int r){
        int low, high;
        int pivot = arr[p]; // pivot 값 설정

        low = p + 1;
        high = r;

        while(low <= high){
            while(arr[low] < pivot) low++;
            while(arr[high] > pivot) high--;

            if (low <= high){
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }

        int temp = arr[p];
        arr[p] = arr[high];
        arr[high] = temp;

        return high;
    }

    static void quickSort(int[] arr, int left, int right){
        if (left < right){
            int pivot = partition(arr, left, right);

            quickSort(arr, left, pivot-1);
            quickSort(arr, pivot+1, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 7, 9, 6};

        quickSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
