package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ch12_backtracking {




    /**
     * 백트래킹 감잡기 : (1,2,3) 숫자로 만들 수 있는 순열 조합 만들어서 리턴하기
     * 순열이란 (1,2,3), (1,3,2) 와 같이 순서만 바뀌는 것을 의미함.
     */
    @Test
    void p0_01() {
        int[] nums = {1, 2, 3};

        int[][] actual = p0_01Solution(nums);
        // Junit 결과 확인용 출력
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    int[][] p0_01Solution(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        p01_01_backtracking(nums, answer, current, visited);

        return answer.stream()
                .map(innerList -> innerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    void p01_01_backtracking(int[] nums, List<List<Integer>> answer, List<Integer> current, boolean[] visited) {
        if (current.size() == nums.length) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current.add(nums[i]);

            p01_01_backtracking(nums, answer, current, visited);

            visited[i] = false;
            current.remove(current.size() - 1);
        }

    }

    /**
     * 백트래킹 감잡기 : (1,2,3)에서 나올 수 있는 모든 부분집합구하기
     */
    @Test
    void p0_02() {
        int n = 3; // 1 ~ N 까지의 숫자로 모든 부분집합 구하기
        int[][] actual = p0_02Solution(n);

        // Junit 결과 확인용 출력
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    int[][] p0_02Solution(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p0_02backtracking(1, n, answer, current);

        return answer.stream()
                .map(innerList -> innerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    void p0_02backtracking(int n, int max, List<List<Integer>> answer, List<Integer> current) {
        answer.add(new ArrayList<>(current));
        for (int i = n; i <= max; i++) {
            current.add(i);
            p0_02backtracking(i + 1, max, answer, current);

            current.removeLast();
        }
    }

    /**
     * 백트래킹 감잡기 : 조합 (1~4까지의 숫자 중 2개 뽑는 조합)
     */
    @Test
    void p0_03() {
        int n = 4; // 1 ~ N 까지의 숫자
        int m = 2; // 조합 할 개수
        int[][] actual = p02_03Solution(n, m);

        // Junit 결과 확인용 출력
        Arrays.stream(actual)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    int[][] p02_03Solution(int n,int m) {

        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p0_03backtracking(1, n, m, answer, current);

        return answer.stream()
                .map(innerList -> innerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    void p0_03backtracking(int start, int n, int m, List<List<Integer>> answer, List<Integer> current) {
        if (current.size() == m) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);

            p0_03backtracking(i + 1, n, m, answer, current);
            current.removeLast();
        }
    }


    /**
     * 백트래킹 감잡기 : 부분집합 합(책에 있음), N퀸 문제
     */


    /**
     * 1부터 N까지 숫자 중 합이 10이 되는 조합구하기 (*)
     */
    @Test
    void p01() {
        int N = 5;

    }

    void p01Solution(int N) {

    }

}
