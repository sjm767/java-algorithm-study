package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        while (!stack.isEmpty()) {
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
                if (!pair.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || stack.pop() != pair.get(c)) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid && stack.isEmpty()) {
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
     * 주식 가격 (**)
     */
    @Test
    void p05() {
//        int[] prices = {1, 2, 3, 2, 3};
//        int[] expect = {4, 3, 1, 1, 0};

        int[] prices = {3, 2, 2, 1, 3};
        int[] expect = {1, 2, 1, 1, 0};

        int[] actual = p05Solution(prices);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p05Solution(int[] prices) {
        Stack<Stock> stack = new Stack<>();
        int[] result = new int[prices.length];

        int time = 0;
        for (int p : prices) {
            if (stack.isEmpty()) {
                stack.push(new Stock(time, p));
            }
            while (!stack.isEmpty() && p < stack.peek().price) {
                Stock pop = stack.pop();
                result[pop.time] = time - pop.time; // 결과에 집어넣음.
            }
            stack.push(new Stock(time, p));

            time++;
        }

        time--;

        // 마지막까지 남은 것 모두 제거하면서 초 계산
        while (!stack.isEmpty()) {
            Stock pop = stack.pop();
            result[pop.time] = time - pop.time;
        }

        return result;
    }

    static class Stock {

        public int time;
        public int price;

        public Stock(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }

    /**
     * 크레인 인형 뽑기 (**)
     */
    @Test
    void p06() {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 3},
            {0, 2, 5, 0, 1},
            {4, 2, 4, 4, 2},
            {3, 5, 1, 3, 1}
        };
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int expect = 4;

        int actual = p06Solution(board, moves);
    }

    int p06Solution(int[][] board, int[] moves) {
        int result = 0;
        final int n = board.length;

        // 보드판에 대한 스택 생성
        List<Stack<Integer>> boardStack = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boardStack.add(new Stack<>());
        }

        // 보드판 스택 완성
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = boardStack.get(i);
            for (int j = n-1; j >= 0; j--) {
                if (board[j][i] > 0) {
                    stack.push(board[j][i]);
                }
            }
        }

        // 바구니 스택 생성
        Stack<Integer> basket = new Stack<>();

        for (int move : moves) {
            int idx = move - 1;
            Stack<Integer> bs = boardStack.get(idx);

            // 해당 라인이 비어있지 않으면 빼서 넣는다.
            if (!bs.isEmpty()) {
                Integer doll = bs.pop();
                // 바구니에 넣으려는데 같은 인형이 있으면 터뜨리고 점수를 쌓는다.
                if (!basket.isEmpty() && Objects.equals(basket.peek(), doll)) {
                    basket.pop();
                    result += 2;
                } else {
                    basket.push(bs.pop());
                }
            }
        }
        return result;
    }

    /**
     * 표 편집 (*****)
     */
    @Test
    void p07() {

    }



}
