package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

class ch6_stack {

    /**
     * 올바른 괄호 판별
     */
    @Test
    void p01() {
//        String s = "(())()";
//        boolean expect = true;

        String s = "(()(";
        boolean expect = false;

        boolean actual = p01Solution(s);
        assertThat(actual).isEqualTo(expect);
    }

    boolean p01Solution(String s) {
        Stack<Character> stack = new Stack<>(); // 스택 선언

        char[] charArray = s.toCharArray();
        for (char c : charArray) {

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.pop() == c) { // 스택이 비어있으면 valid 하지 않음.
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 10진수를 2진수로 변환하기
     */
    @Test
    void p02() {
//        int decimal = 27;
//        String expect = "11011";

        int decimal = 12345;
        String expect = "11000000111001";

        String actual = p02Solution(decimal);
        assertThat(actual).isEqualTo(expect);
    }

    String p02Solution(int decimal) {
        Stack<Integer> stack = new Stack<>();

        while (decimal > 0) {
            stack.push(decimal % 2);
            decimal /= 2;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    /**
     * 괄호 회전하기
     */
    @Test
    void p03() {
        String s = "[](){}";
        int expect = 2;

//        String s = "}]()[{";
//        int expect = 2;

        int actual = p03Solution(s);
    }

    int p03Solution(String s) {
        Map<Character, Character> pair = new HashMap<>();
        pair.put(')', '(');
        pair.put('}', '{');
        pair.put(']', '[');

        int validCount = 0; // 결과
        int n = s.length(); //회전 횟수
        StringBuilder sb = new StringBuilder(s); // 회전할 문자열
        Stack<Character> stack = new Stack<>();

        // 문자열 크기 만큼 반복
        for (int i = 0; i < n; i++) {

            char[] charArray = sb.toString().toCharArray();
            boolean valid = true;
            for (char c : charArray) {
                if(!pair.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || stack.pop() != pair.get(c)) {
                        valid = false;
                        break;
                    }
                }
            }

            if(valid && stack.isEmpty()) {
                validCount++;
            }
            // 스택 초기화
            stack.clear();
            // 왼쪽으로 회전
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }

        return validCount;
    }

    /**
     * 짝지어 제거하기
     */
    @Test
    void p04() {
        String s = "baabaa";
        int expect = 1;

//        String s = "cdcd";
//        int expect = 0;

        int actual = p04Solution(s);
        assertThat(actual).isEqualTo(expect);
    }

    int p04Solution(String s) {
        Stack<Character> stack = new Stack<>();

        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            return 1;
        }

        return 0;
    }

    /**
     * 주식 가격
     */
    @Test
    void p05() {

    }

    int[] p05Solution(int[] n) {
        return null;
    }
}
