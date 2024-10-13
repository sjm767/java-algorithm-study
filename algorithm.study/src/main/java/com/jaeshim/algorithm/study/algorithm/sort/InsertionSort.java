package com.jaeshim.algorithm.study.algorithm.sort;

public class InsertionSort {
    static int[] solution(int n, int[] arr) {

        for (int i = 1; i < n; i++) {
            int tmp = arr[i];
            int j = i-1;
            for (;j > 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = tmp;
        }

        return arr;
    }
    public static void main(String[] args) {
        int n = 6;
        int[] arr = {5, 6, 7, 9, 10, 11};

        int[] answer = solution(n, arr);
        for (int a : answer) {
            System.out.print(a+" ");
        }
    }
}

