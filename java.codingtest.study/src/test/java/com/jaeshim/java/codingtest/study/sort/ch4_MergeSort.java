package com.jaeshim.java.codingtest.study.sort;

import java.util.Arrays;

public class ch4_MergeSort {

    public static void main(String[] args) {
        int[] nums = {5, 3, 4, 1, 2};

        mergeSort(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }

    }

    public static void mergeSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, nums);
    }

    public static void merge(int[] left, int[] right, int[] nums) {
        int l = 0;
        int r = 0;
        int k = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                nums[k++] = left[l++];
            } else{
                nums[k++] = right[r++];
            }
        }

        while (l < left.length) {
            nums[k++] = left[l++];
        }
        while (r < right.length) {
            nums[k++] = right[r++];
        }

    }


}
