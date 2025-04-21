package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ch13_sort {

    /**
     * 연습1_삽입정렬 (O(n^2))
     */
    @Test
    void p000_insertionSort() {
        int[] nums = {5, 3, 4, 1, 2};
        int[] expect = {1, 2, 3, 4, 5};

        p000Solution(nums);

        assertThat(expect).isEqualTo(nums);

    }
    void p000Solution(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            while(j >= 0 && nums[j] > key) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = key;
        }
    }

    /**
     * 병합 정렬 (시간복잡도: O(NlogN)
     */
    @Test
    void p001_mergeSort() {
        int[] nums = {1, 5, 3, 7, 2, 6, 4, 8};
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8};

        p001MergeSort(nums);
        assertThat(expect).isEqualTo(nums);
    }

    void p001MergeSort(int[] nums) {
        if (nums.length / 2 < 1) {
            return;
        }
        int mid = nums.length / 2;
        int[] left = new int[mid];
        int[] right = new int[nums.length - mid];

        // 원본배열, 원본시작, 대상배열, 대상배열시작, 복사개수
        System.arraycopy(nums, 0, left, 0, mid);
        System.arraycopy(nums, mid, right, 0, nums.length - mid);

        p001MergeSort(left);
        p001MergeSort(right);

        p001Merge(nums, left, right);
    }

    void p001Merge(int[] nums, int[] left, int[] right) {
        int l = 0, r = 0, k = 0;

        while(l < left.length && r < right.length) {
            if(left[l] < right[r]) {
                nums[k++] = left[l++];
            } else {
                nums[k++] = right[r++];
            }
        }

        while(l < left.length) nums[k++] = left[l++];
        while(r < right.length) nums[k++] = right[r++];
    }

    /**
     * 힙 정렬 O(NlogN)
     */
    @Test
    void p002_heapSort() {
        int[] nums = {4, 8, 5, 7, 4, 3, 2, 6, 1};
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8};

        p002HeapSort(nums);

        assertThat(expect).isEqualTo(nums);
    }

    void p002HeapSort(int[] nums) {
        int n = nums.length;

        // Step1. Build Max Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            p002Heapify(nums, n, i);
        }

        // Step2. Heap Sort
        for (int i = n - 1; i >= 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;

            p002Heapify(nums, i, 0);
        }
    }
    void p002Heapify(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != rootIndex) {
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = temp;

            p002Heapify(arr, heapSize, largest);
        }
    }
}
