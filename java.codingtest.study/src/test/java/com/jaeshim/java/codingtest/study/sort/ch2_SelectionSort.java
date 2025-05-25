package com.jaeshim.java.codingtest.study.sort;

public class ch2_SelectionSort {

    public static void main(String[] args) {
        int[] nums = {5, 3, 4, 1, 2};

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            // swap
            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp;
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }

    }
}
