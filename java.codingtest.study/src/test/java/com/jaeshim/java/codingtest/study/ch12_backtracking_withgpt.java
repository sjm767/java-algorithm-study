package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

import java.util.*;

class ch12_backtracking_withgpt {


    /**
     * GPT 추천 백트래킹 문제
     * - 부분집합 구하기 (*)
     * 1부터 n까지의 숫자로 만들 수 있는 모든 부분집합을 구하세요.
     * (숫자의 순서는 유지되지만, 선택 여부는 자유)
     */
    @Test
    void p01() {
        int n = 3;
        int[][] expect = {
                {},
                {1},
                {2},
                {1, 2},
                {3},
                {1, 3},
                {2, 3},
                {1, 2, 3}
        };
        int[][] actual = p01Solution(n);
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    int[][] p01Solution(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p01backtracking(1, n, answer, current);

        return answer.stream()
                .map(innerList -> innerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    void p01backtracking(int start, int n, List<List<Integer>> answer, List<Integer> current) {

        answer.add(new ArrayList<>(current));

        for (int i = start; i <= n; i++) {
            current.add(i);
            p01backtracking(i + 1, n, answer, current);

            current.removeLast();
        }

    }

    /**
     * GPT 추천 백트래킹 문제
     * - 조합 구하기 (*)
     * 1부터 n까지 숫자 중에서 중복 없이 k개를 뽑는 모든 조합을 구하세요.
     */
    @Test
    void p02() {
        int n = 4;
        int k = 2;
        int[][] expect = {
                {1, 2},
                {1, 3},
                {1, 4},
                {2, 3},
                {2, 4},
                {3, 4}
        };

        int[][] actual = p02Solution(n, k);
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);

    }

    int[][] p02Solution(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p02backtracking(1, k, n, answer, current);

        return answer.stream()
                .map(innerList -> innerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    void p02backtracking(int start, int k, int n, List<List<Integer>> answer, List<Integer> current) {
        if (current.size() == k) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            p02backtracking(i + 1, k, n, answer, current);

            current.removeLast();
        }

    }

    /**
     * GPT 추천 백트래킹 문제
     * - 순열 구하기 (*)
     * 1부터 n까지 숫자 중에서 중복 없이 k개를 뽑아 모든 순서를 고려한 경우를 구하세요.
     */
    @Test
    void p03() {
        int n = 3;
        int k = 2;
        int[][] expect = {
                {1, 2},
                {2, 1},
                {2, 3},
                {3, 1},
                {3, 2}
        };

        int[][] actual = p03Solution(n, k);
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    int[][] p03Solution(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] visited = new boolean[n + 1]; // 1부터 n까지 사용

        p03backtracking(n, k, answer, current, visited);

        return answer.stream()
                .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    void p03backtracking(int n, int k, List<List<Integer>> answer, List<Integer> current, boolean[] visited) {
        if (current.size() == k) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            current.add(i);

            p03backtracking(n, k, answer, current, visited);

            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * GPT 추천 백트래킹 문제
     * - 중복 조합 구하기 (중복 허용 조합) (*)
     * 1부터 n까지의 숫자 중에서 중복을 허용하며 k개를 뽑는 모든 조합을 구하세요.
     * 단, 오름차순으로만 뽑습니다!!
     */
    @Test
    void p04() {
        int n = 3;
        int k = 2;
        int[][] expect = {
                {1, 1},
                {1, 2},
                {1, 3},
                {2, 2},
                {3, 3}
        };

        int[][] actual = p04Solution(n, k);
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    int[][] p04Solution(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p04backtracking(1, n, k, answer, current);

        return answer.stream()
                .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }


    void p04backtracking(int start, int n, int k, List<List<Integer>> answer, List<Integer> current) {
        if (current.size() == k) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);

            p04backtracking(i, n, k, answer, current);
            current.removeLast();
        }

    }

    /**
     * GPT 추천 백트래킹 문제
     * - 괄호 생성 (Leetcode 22: Generate Parentheses) (Leetcode Medium)
     * 올바른 괄호 쌍 n쌍이 있을 때, 만들 수 있는 모든 올바른 괄호 문자열을 구하세요.
     */
    @Test
    void p05() {
        int n = 2;
        String[] expect = {
                "(())",
                "()()"
        };
        String[] actual = p05Solution(n);
        Arrays.stream(actual).forEach(System.out::println);

    }

    String[] p05Solution(int n) {
        List<String> answer = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        p05backtracking("", 0, 0, answer, n);

        return answer.toArray(String[]::new);
    }

    void p05backtracking(String current, int open, int close, List<String> answer,int n) {
        if (current.length() == (n * 2)) {
            answer.add(current);
            return;
        }

        if (open < n) {
            p05backtracking(current + "(", open + 1, close, answer, n);
        }

        if (close < open) {
            p05backtracking(current + ")", open, close + 1, answer, n);
        }
    }


//    /**
//     * 백트래킹 감잡기 : (1,2,3) 숫자로 만들 수 있는 순열 조합 만들어서 리턴하기
//     * 순열이란 (1,2,3), (1,3,2) 와 같이 순서만 바뀌는 것을 의미함.
//     */
//    @Test
//    void p0_01() {
//        int[] nums = {1, 2, 3};
//
//        int[][] actual = p0_01Solution(nums);
//        // Junit 결과 확인용 출력
//        Arrays.stream(actual)
//                .map(Arrays::toString)
//                .forEach(System.out::println);
//    }
//
//    int[][] p0_01Solution(int[] nums) {
//        List<List<Integer>> answer = new ArrayList<>();
//        List<Integer> current = new ArrayList<>();
//        boolean[] visited = new boolean[nums.length];
//
//        p01_01_backtracking(nums, answer, current, visited);
//
//        return answer.stream()
//                .map(innerList -> innerList.stream()
//                        .mapToInt(Integer::intValue)
//                        .toArray())
//                .toArray(int[][]::new);
//    }
//
//    void p01_01_backtracking(int[] nums, List<List<Integer>> answer, List<Integer> current, boolean[] visited) {
//        if (current.size() == nums.length) {
//            answer.add(new ArrayList<>(current));
//            return;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            if (visited[i]) {
//                continue;
//            }
//
//            visited[i] = true;
//            current.add(nums[i]);
//
//            p01_01_backtracking(nums, answer, current, visited);
//
//            visited[i] = false;
//            current.remove(current.size() - 1);
//        }
//
//    }
//
//    /**
//     * 백트래킹 감잡기 : (1,2,3)에서 나올 수 있는 모든 부분집합구하기
//     */
//    @Test
//    void p0_02() {
//        int n = 3; // 1 ~ N 까지의 숫자로 모든 부분집합 구하기
//        int[][] actual = p0_02Solution(n);
//
//        // Junit 결과 확인용 출력
//        Arrays.stream(actual)
//                .map(Arrays::toString)
//                .forEach(System.out::println);
//    }
//
//    int[][] p0_02Solution(int n) {
//        List<List<Integer>> answer = new ArrayList<>();
//        List<Integer> current = new ArrayList<>();
//
//        p0_02backtracking(1, n, answer, current);
//
//        return answer.stream()
//                .map(innerList -> innerList.stream()
//                        .mapToInt(Integer::intValue)
//                        .toArray())
//                .toArray(int[][]::new);
//    }
//
//    void p0_02backtracking(int n, int max, List<List<Integer>> answer, List<Integer> current) {
//        answer.add(new ArrayList<>(current));
//        for (int i = n; i <= max; i++) {
//            current.add(i);
//            p0_02backtracking(i + 1, max, answer, current);
//
//            current.removeLast();
//        }
//    }
//
//    /**
//     * 백트래킹 감잡기 : 조합 (1~4까지의 숫자 중 2개 뽑는 조합)
//     */
//    @Test
//    void p0_03() {
//        int n = 4; // 1 ~ N 까지의 숫자
//        int m = 2; // 조합 할 개수
//        int[][] actual = p02_03Solution(n, m);
//
//        // Junit 결과 확인용 출력
//        Arrays.stream(actual)
//                .map(Arrays::toString)
//                .forEach(System.out::println);
//    }
//
//    int[][] p02_03Solution(int n,int m) {
//
//        List<List<Integer>> answer = new ArrayList<>();
//        List<Integer> current = new ArrayList<>();
//
//        p0_03backtracking(1, n, m, answer, current);
//
//        return answer.stream()
//                .map(innerList -> innerList.stream()
//                        .mapToInt(Integer::intValue)
//                        .toArray())
//                .toArray(int[][]::new);
//    }
//
//    void p0_03backtracking(int start, int n, int m, List<List<Integer>> answer, List<Integer> current) {
//        if (current.size() == m) {
//            answer.add(new ArrayList<>(current));
//            return;
//        }
//
//        for (int i = start; i <= n; i++) {
//            current.add(i);
//
//            p0_03backtracking(i + 1, n, m, answer, current);
//            current.removeLast();
//        }
//    }
//
//
//    /**
//     * 백트래킹 감잡기 : 부분집합 합(책에 있음), N퀸 문제
//     */



}
