package com.jaeshim.java.codingtest.study.book;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

class ch12_backtracking {

    /**
     * 1부터 N까지 숫자 중 합이 10이 되는 조합 구하기 (*)
     */
    @Test
    void p01() {
        int N = 5;
        int[][] expect = {
                {1, 2, 3, 4},
                {1, 4, 5},
                {2, 3, 5}
        };

        int[][] actual = p01Solution(N);
        assertThat(actual).isEqualTo(expect);
    }

    int[][] p01Solution(int N) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p01backtracking(1, N, 0, answer, current);

        return answer.stream()
                .map(innerList -> innerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    void p01backtracking(int start, int N, int sum, List<List<Integer>> answer, List<Integer> current) {
        if (sum == 10) {
            answer.add(new ArrayList<>(current));
            return;
        }

        if (sum > 10) {
            return;
        }

        for (int i = start; i <= N; i++) {
            current.add(i);
            p01backtracking(i + 1, N, sum + i, answer, current);
            current.removeLast();
        }
    }

    /**
     * 스도쿠 퍼즐 (***)
     * 스도큐 규칙
     *      9x9 격자를 채워야 함
     *      전체는 9개의 행(Row) × 9개의 열(Column)로 구성
     *      모든 행(Row) 에는 1부터 9까지 숫자가 중복 없이 한 번씩만 등장해야 함
     *      모든 열(Column) 에도 1부터 9까지 숫자가 중복 없이 한 번씩만 등장해야 함
     *      전체 9x9 격자는 3x3 박스 9개로 나뉘어 있고,
     *      각 3x3 박스 안에도 1부터 9까지 숫자가 중복 없이 한 번씩만 등장해야 함
     */
    @Test
    void p02() {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        int[][] expect = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

    }

    int[][] p02Solution(int[][] board) {

        return null;
    }

    /**
     * 피로도 (*)
     */
    @Test
    void p03() {
        int k = 80;
        int[][] dungeons = {
                {80, 20},
                {50, 40},
                {30, 10}
        };
        int expect = 3;

//        int k = 60;
//        int[][] dungeons = {
//                {80, 20},
//                {50, 30},
//                {30, 10}
//        };
//        int expect = 2;

        int actual = p03Solution(k, dungeons);
        assertThat(actual).isEqualTo(expect);
    }

    int p03Solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        AtomicInteger maxCount = new AtomicInteger(0);

        p03backtracking(k, dungeons, visited, 0, maxCount);

        return maxCount.get();
    }

    void p03backtracking(int currentFatigue, int[][] dungeons, boolean[] visited, int depth, AtomicInteger maxCount) {
        boolean canExplore = false;
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (currentFatigue >= dungeons[i][0]) {
                canExplore = true;
                visited[i] = true;
                p03backtracking(currentFatigue - dungeons[i][1], dungeons, visited, depth + 1, maxCount);
                visited[i] = false;
            }
        }

        if (!canExplore) {
            maxCount.set(Math.max(maxCount.get(), depth));
        }
    }

    /**
     * N-퀸 문제 (*)
     */
    @Test
    void p04() {
        int n = 4;
        int expect = 2;

        int actual = p04Solution(n);
        assertThat(actual).isEqualTo(expect);
    }

    int p04Solution(int n) {
        AtomicInteger answer = new AtomicInteger();
        boolean[] colDiag = new boolean[n];
        boolean[] leftDiag = new boolean[2 * n];
        boolean[] rightDiag = new boolean[2 * n];

        p04backtracking(0, n, colDiag, leftDiag, rightDiag, answer);

        return answer.get();
    }

    void p04backtracking(int row, int n, boolean[] colDiag, boolean[] leftDiag, boolean[] rightDiag, AtomicInteger answer) {
        if (row == n) {
            answer.incrementAndGet();
            return;
        }

        for (int col = 0 ; col < n; col++) {
            if (colDiag[col] || leftDiag[row + col] || rightDiag[row - col + n]) {
                continue;
            }

            colDiag[col] = true;
            leftDiag[row + col] = true;
            rightDiag[row - col + n] = true;

            p04backtracking(row + 1, n, colDiag, leftDiag, rightDiag, answer);

            colDiag[col] = false;
            leftDiag[row + col] = false;
            rightDiag[row - col + n] = false;
        }
    }

    /**
     * 양궁 대회 (**)
     */
    @Test
    void p05() {
        int n = 5; // 화살의 개수
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}; // 어피치가 맞춘 과녁 점수

        p05Solution(n, info);
    }

    void p05Solution(int n, int[] info) {
        List<List<Integer>> answer = new ArrayList<>();
        int[] counts = new int[11];

        p05backtracking(10, n, info, 0, 0, counts);
        int a = 4;
    }

    int max = Integer.MIN_VALUE;
    int[] answer = null;
    void p05backtracking(int start, int n, int[] info, int sum, int count, int[] counts) {
        if (count == n) {
            int score = getScore(counts, info);
            if (score > 0) {
                answer = counts;
            }
            return;
        }

        for (int i = start; i >= 0; i--) {
            counts[i]++;
            count++;
            p05backtracking(i - 1, n, info, sum, count, counts);
            counts[i]--;
            count--;
        }
    }

    int getScore(int[] counts, int[] info) {
        int rScore = 0;
        int aScore = 0;
        for (int i = 0; i < 11; i++) {

            if (counts[i] > info[i]) {
                rScore += (10 - i);
            } else {
                aScore += (10 - i);
            }
        }

        if (rScore > aScore) {
            return rScore;
        }

        return -1;
    }


}
