package com.jaeshim.java.codingtest.study.leetcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class ch1_easy {

    /**
     * Two Sum (https://leetcode.com/problems/two-sum/)
     */
    @Test
    void p01() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expect = {0, 1};
        int[] actual = p01Solution(nums, target);
        assertThat(actual).isEqualTo(expect);

        nums = new int[] {3,2,4};
        target = 6;
        expect = new int[] {1,2};
        actual = p01Solution(nums, target);
        assertThat(actual).isEqualTo(expect);

        nums = new int[] {3,3};
        target = 6;
        expect = new int[] {0,1};
        actual = p01Solution(nums, target);
        assertThat(actual).isEqualTo(expect);

        nums = new int[] {3,2,3};
        target = 6;
        expect = new int[] {0, 2};
        actual = p01Solution(nums, target);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p01Solution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            if (map.containsKey(find)) {
                return new int[]{map.get(find), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * Palindrome Number (https://leetcode.com/problems/palindrome-number/)
     */
    @Test
    void p02() {
        int x = 121;
        boolean expect = true;

//        int x = -121;
//        boolean expect = false;

//        int x = 10;
//        boolean expect = false;

        boolean actual = p02Solution(x);
        assertThat(actual).isEqualTo(expect);

    }
    // 시간복잡도 log10(x) : 자리수에 비례한다는 뜻.
    // 오 로그 엑스라고 읽음.
    public boolean p02Solution(int x) {
        if (x < 0) {
            return false;
        }

        int current = x;
        int rev = 0;
        while (current != 0) {
            int digit = current % 10;
            rev = rev * 10 + digit;
            current /= 10;
        }
        return x == rev;
    }

    /**
     * Roman to Integer (https://leetcode.com/problems/roman-to-integer/description/)
     */
    @Test
    void p03() {
//        String s = "MCMXCIV";
//        int expect = 1994;

        String s = "III";
        int expect = 3;

        int actual = p03Solution(s);
        assertThat(actual).isEqualTo(expect);
    }

    int p03Solution(String s) {
        int answer = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); ) {
            if (i+1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                answer += map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                i+=2;
            } else {
                answer += map.get(s.charAt(i));
                i++;
            }
        }

        return answer;
    }

    /**
     * Longest Common Prefix (https://leetcode.com/problems/longest-common-prefix/description/)
     */
    @Test
    void p04() {
        String[] str = {"flower", "flow", "flight"};
        String expect = "fl";

//        String[] str = {"dog","racecar","car"};
//        String expect = "";

        String actual = p04Solution(str);
        assertThat(actual).isEqualTo(expect);
    }

    String p04Solution(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        String base = strs[0];
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || c != strs[j].charAt(i)) {
                    return base.substring(0, i);
                }
            }
        }

        return base;
    }

    /**
     * Valid Parentheses (https://leetcode.com/problems/valid-parentheses/description/)
     */
    @Test
    void p05() {
//        String s = "()";
//        boolean expect = true;

        String s = "([]])";
        boolean expect = false;

        boolean actual = p05Solution(s);
        assertThat(actual).isEqualTo(expect);
    }

    boolean p05Solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = Map.of('(', ')', '{', '}', '[', ']');

        for (char c : s.toCharArray()){
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character v = map.get(stack.pop());
                if (c != v) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * Merge Two Sorted Lists (https://leetcode.com/problems/merge-two-sorted-lists/description/)
     */
    @Test
    void p06() {
        p06ListNode l13 = new p06ListNode(4);
        p06ListNode l12 = new p06ListNode(2, l13);
        p06ListNode l11 = new p06ListNode(1, l12);

        p06ListNode l23 = new p06ListNode(4);
        p06ListNode l22 = new p06ListNode(3, l23);
        p06ListNode l21 = new p06ListNode(1, l22);


        p06ListNode actual = p06Solution(l11, l21);
        int a = 4;
    }

    public p06ListNode p06Solution(p06ListNode list1, p06ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        p06ListNode root = new p06ListNode();
        p06ListNode node = root;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = new p06ListNode(list1.val);
                node = node.next;
                list1 = list1.next;
            } else {
                node.next = new p06ListNode(list2.val);
                node = node.next;
                list2 = list2.next;
            }
        }

        while (list1 != null) {
            node.next = new p06ListNode(list1.val);
            node = node.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            node.next = new p06ListNode(list2.val);
            node = node.next;
            list2 = list2.next;
        }

        return root.next;
    }

    public static class p06ListNode {

        int val;
        p06ListNode next;

        p06ListNode() {
        }

        p06ListNode(int val) {
            this.val = val;
        }

        p06ListNode(int val, p06ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Remove Duplicates from Sorted Array (https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/)
     */
    @Test
    void p07() {
        int[] nums = {1, 1, 2}; // {1, 2, 0} 으로 남아야 함
        int expect = 2;

        int actual = p07Solution(nums);
        assertThat(actual).isEqualTo(expect);
    }

    int p07Solution(int[] nums) {
        int i = 0; // 시작 인덱스

        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * Remove Element (https://leetcode.com/problems/remove-element/description/)
     */
    @Test
    void p08() {
//        int[] nums = {3,2,2,3};
//        int val = 3;
//        int expect = 2;

        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int expect = 5;

//        int[] nums = {1};
//        int val = 1;
//        int expect = 0;

        int actual = p08Solution(nums, val);
        assertThat(actual).isEqualTo(expect);
    }

    int p08Solution(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * Find the index of the First Occurrence in a String (https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/)
     */
    @Test
    void p09() {
//        String haystack = "sadbutsad";
//        String needle = "sad";
//        int expect = 0;

//        String haystack = "eleetoode";
//        String needle = "leeto";
//        int expect = 1;

        String haystack = "mississippi";
        String needle = "issip";
        int expect = 4;

        int actual = p09Solution(haystack, needle);
        assertThat(actual).isEqualTo(expect);
    }

    int p09Solution(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();

        for (int i = 0; i <= h - n; i++) {
            int j = 0;
            while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == n) return i;
        }

        return -1;
    }

    /**
     * Search Insert Position (https://leetcode.com/problems/search-insert-position/description/)
     */
    @Test
    void p10() {
//        int[] nums = {1, 3, 5, 6};
//        int target = 5;
//        int expect = 2;

//        int[] nums = {1, 3, 5, 6};
//        int target = 2;
//        int expect = 1;

//        int[] nums = {1, 3, 5, 6};
//        int target = 7;
//        int expect = 4;

        int[] nums = {1,3};
        int target = 2;
        int expect = 1;

        int actual = p10Solution(nums, target);
        assertThat(actual).isEqualTo(expect);
    }

    int p10Solution(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1 ;

        while (start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    /**
     * Length of Last Word (https://leetcode.com/problems/length-of-last-word/description/)
     */
    @Test
    void p11() {
        String s = "   fly me   to   the moon  ";
        int expect = 4;

        int actual = p11Solution(s);
        assertThat(actual).isEqualTo(expect);
    }

    int p11Solution(String s) {
        int i = s.length() - 1;
        // 뒤쪽 공백 스킵
        while (i >= 0 && s.charAt(i) == ' ') i--;

        // 단어 길이 세기
        int length = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        return length;
    }

    /**
     * Plus One (https://leetcode.com/problems/plus-one/description/)
     */
    @Test
    void p12() {
        int[] digits = {1,2,3};
        int[] expect = {1,2,4};
        int[] actual = p12Solution(digits);
        assertThat(actual).isEqualTo(expect);

        digits = new int[] {9};
        expect = new int[] {1,0};
        actual = p12Solution(digits);
        assertThat(actual).isEqualTo(expect);

        digits = new int[]{4, 3, 2, 9};
        expect = new int[]{4, 3, 3, 0};
        actual = p12Solution(digits);
        assertThat(actual).isEqualTo(expect);

        digits = new int[]{4, 9, 9, 9};
        expect = new int[]{5, 0, 0, 0};
        actual = p12Solution(digits);
        assertThat(actual).isEqualTo(expect);

        digits = new int[]{9, 9, 9, 9};
        expect = new int[]{1, 0, 0, 0, 0};
        actual = p12Solution(digits);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p12Solution(int[] digits) {
        int[] answer = null;
        int idx = digits.length - 1;
        while (idx >= 0) {
           int lastElement = digits[idx];
            if (lastElement != 9) {
                digits[idx] = digits[idx] + 1;
                break;
            }

            digits[idx] = 0;
            idx--;
        }

        if (idx < 0) {
            answer = new int[digits.length + 1];
            System.arraycopy(digits, 0, answer, 0, digits.length);
            answer[0] = 1;
            return answer;
        }

        return digits;
    }

    /**
     * Add Binary (https://chatgpt.com/c/682962db-6ba8-800b-aceb-396e09c3c892)
     */
    @Test
    void p13() {
        String a, b, expect;

        a = "11";
        b = "1";
        expect = "100";

        a = "1010";
        b = "1011";
        expect = "10101";

        a = "0000";
        b = "0001";
        expect = "0001";

        String actual = p13Solution(a, b);
        assertThat(actual).isEqualTo(expect);
    }

    String p13Solution(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aIdx = a.length() - 1;
        int bIdx = b.length() - 1;
        int aInt = a.charAt(a.length() - 1) - '0';
        int bInt = b.charAt(b.length() - 1) - '0';
        int appendix = 0;
        while (aIdx >=0 && bIdx >= 0) {
            int sum = aInt + bInt + appendix;

            if (sum > 1) {
                appendix = sum % 2;
                sb.append(appendix);
            } else {
                appendix = 0;
                sb.append(appendix);
            }

            aIdx--;
            bIdx--;

        }

        return null;
    }

}
