package com.jaeshim.algorithm.study.algorithm.sort;

public class BubbleSort {

    static int[] solution(int n, int[] arr) {

        for (int i = 0; i < n -1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }
    public static void main(String[] args) {
        int n = 6;
        int[] arr = {13, 5, 11, 7, 23, 15};

        int[] answer = solution(n, arr);
        for (int a : answer) {
            System.out.print(a+" ");
        }
    }
}
