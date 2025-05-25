package com.jaeshim.java.codingtest.study.book;

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
            int current = i - 1;
            while (current >= 0 && key < nums[current]) {
                nums[current + 1] = nums[current];
                current--;
            }
            nums[current+1] = key;
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
        long n = 118372;
        long expect = 873211;

        long actual = p04Solution(n);
        assertThat(actual).isEqualTo(expect);
    }

    long p04Solution(long n) {
        String[] digits = String.valueOf(n).split("");

        Arrays.sort(digits, Comparator.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (String digit : digits) {
            sb.append(digit);
        }

        return Long.parseLong(sb.toString());
    }

    /**
     * K번째 수 (*)
     */
    @Test
    void p05() {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {
                {2, 5, 3},
                {4, 4, 1},
                {1, 7, 3}
        };

        int[] expect = {5, 6, 3};
        int[] actual = p05Solution(array, commands);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p05Solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];

            int[] subset = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(subset);
            answer.add(subset[k - 1]);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 가장 큰 수 (***)
     */
    @Test
    void p06() {
//        int[] numbers = {6, 10, 2};
//        String expect = "6210";

        int[] numbers = {3, 30, 34, 5, 9};
        String expect = "9534330";

        String actual = p06Solution(numbers);
        assertThat(actual).isEqualTo(expect);
    }

    String p06Solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        for (int n : numbers) {
            list.add("" + n);
        }

        list.sort((o1, o2) -> {
            String a1 = o1 + o2;
            String a2 = o2 + o1;

            if (a1.compareTo(a2) > 0) {
                return -1;
            }
            return 1;
        });

        StringBuilder answer = new StringBuilder();
        list.forEach(answer::append);

        if (answer.toString().charAt(0) == '0') {
            return "0";
        }

        return answer.toString();
    }

    /**
     * 튜플 (**)
     */
    @Test
    void p07() {
//        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
//        int[] result = {2, 1, 3, 4};

        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        int[] expect = {2, 1, 3, 4};

        int[] actual = p07Solution(s);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p07Solution(String s) {
        String substr = s.substring(2, s.length() - 2);
        String[] subsets = substr.split("\\},\\{");

        Arrays.sort(subsets, Comparator.comparingInt(String::length));

        List<Integer> answer = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (String subset : subsets) {
            String[] split = subset.split(",");

            for (String ss : split) {
                int num = Integer.parseInt(ss);
                if (set.add(num)) {
                    answer.add(num);
                }
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 지형 이동 (****)
     */
    @Test
    void p08() {

    }

    /**
     * 전화번호 목록 (**)
     */
    @Test
    void p09() {
//        String[] phoneBook = {"119", "97674223", "1195524421"};
//        boolean expect = false;

//        String[] phoneBook = {"123","456","789"};
//        boolean expect = true;

        String[] phoneBook = {"12","123","1235","567","88"};
        boolean expect = false;

        boolean actual = p09Solution(phoneBook);
        assertThat(actual).isEqualTo(expect);
    }

    boolean p09Solution(String[] phoneBook) {
        Arrays.sort(phoneBook);

        for (int i = 0; i < phoneBook.length - 1; i++) {
            String src = phoneBook[i];
            String target = phoneBook[i + 1];

            if (target.startsWith(src)) {
                return false;
            }
        }

        return true;
    }
}
