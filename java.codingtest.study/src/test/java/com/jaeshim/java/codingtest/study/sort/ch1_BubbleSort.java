package com.jaeshim.java.codingtest.study.sort;

public class ch1_BubbleSort {

    public static void main(String[] args) {
        int[] nums = {5, 3, 4, 1, 2};
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i -1; j++) {
                if (nums[j+1] < nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j+1] = temp;
                }
            }
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
