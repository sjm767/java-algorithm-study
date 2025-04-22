package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

import java.util.*;

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

    /**
     * 계수 정렬 구현하기 (*)
     */
    @Test
    void p01() {
        String s = "hello";
        String expect = "ehllo";

        String actual = p01Solution(s);
        assertThat(expect).isEqualTo(actual);
    }

    String p01Solution(String s) {
        int[] alphabet = new int[26];

        for (char ch : s.toCharArray()) {
            alphabet[ch-'a']++;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < alphabet.length; i++) {
            if(alphabet[i] > 0) {
                for (int j = 0; j < alphabet[i]; j++) {
                    answer.append((char) (i + 'a'));
                }
            }
        }

        return answer.toString();
    }

    /**
     * 정렬이 완료된 두 배열 합치기 (*)
     */
    @Test
    void p02() {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};

        int[] expect = {1, 2, 3, 4, 5, 6};
        int[] actual = p02Solution(arr1, arr2);

        assertThat(expect).isEqualTo(actual);
    }
    int[] p02Solution(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while(i < arr1.length) merged[k++] = arr1[i++];
        while(j < arr2.length) merged[k++] = arr2[j++];

        return merged;
    }

    /**
     * 문자열 내 마음대로 정렬하기 (*)
     */
    @Test
    void p03() {
//        List<String> strs = new ArrayList<>(List.of("sun", "bed", "car"));
//        int n = 1;
//        List<String> expect = List.of("car", "bed", "sun");

        List<String> strs = new ArrayList<>(List.of("abce", "abcd", "cdx"));
        int n = 2;
        List<String> expect = List.of("abcd", "abce", "cdx");

        List<String> actual = p03Solution(strs, n);
        assertThat(expect).isEqualTo(actual);
    }

    List<String> p03Solution(List<String> strs, int n) {
        strs.sort((o1, o2) -> {
            char c1 = o1.charAt(n);
            char c2 = o2.charAt(n);

            if (c1 == c2) {
                return o1.compareTo(o2); // 사전순
            }
            return Character.compare(c1, c2); // 안정적인 문자 비교
        });

        return strs;
    }

    /**
     * 정수 내림차순으로 배치하기 (*)
     */
    @Test
    void p04() {
        int n = 118372;
        int expect = 873211;

        int actual = p04Solution(n);
    }

    int p04Solution(int n) {
        List<Integer> answer = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        while (n > 0) {

            max = Math.max(max, n % 10);
            list.add(n % 10);
            n = n / 10;
        }

        int[] rank = new int[max + 1];

        for (Integer l : list) {
            rank[l]++;
        }

        for (int r = rank.length - 1; r >= 0; r--) {
            if (rank[r] > 0) {
                for (int i = 0; i < rank[r]; i++) {
                    answer.add(r);
                }
            }
        }

        return 0;
    }
}
