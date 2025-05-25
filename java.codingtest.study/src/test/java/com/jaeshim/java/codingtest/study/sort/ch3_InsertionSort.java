package com.jaeshim.java.codingtest.study.sort;

public class ch3_InsertionSort {
    public static void main(String[] args) {
        int[] nums = {3, 5, 4, 1, 2};
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j;
            for (j = i - 1; j >= 0 && key < nums[j]; j--) {
                if(key < nums[j]) {
                    nums[j + 1] = nums[j];
                }
            }
            nums[j+1] = key;
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
