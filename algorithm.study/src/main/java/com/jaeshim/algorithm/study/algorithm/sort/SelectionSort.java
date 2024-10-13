package com.jaeshim.algorithm.study.algorithm.sort;

import java.util.Scanner;

/**
 * 선택정렬.
 * O(n^2)이 소모된다.
 */
public class SelectionSort {

    static int[] solution(int n, int[] arr) {

        for (int i = 0; i < n-1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
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
