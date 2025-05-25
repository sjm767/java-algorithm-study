package com.jaeshim.java.codingtest.study.book;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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

    /**
     * GPT 추천 백트래킹 문제
     * - 모든 순열 출력
     * 주어진 정수 배열의 모든 순열을 구하라
     */
    @Test
    void p06() {
        int n = 3;
        int[][] expect = {
            {1, 2, 3},
            {1, 3, 2},
            {2, 1, 3},
            {2, 3, 1},
            {3, 1, 2},
            {3, 2, 1}
        };
        int[][] actual = p06Solution(n);
        Arrays.stream(actual)
            .map(Arrays::toString)
            .forEach(System.out::println);
    }

    int[][] p06Solution(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];

        p06backtracking(n, answer, current, visited);

        return answer.stream()
            .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
    }

    void p06backtracking(int n, List<List<Integer>> answer, List<Integer> current, boolean[] visited) {
        if (current.size() == n) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }

            current.add(i);
            visited[i] = true;
            p06backtracking(n, answer, current, visited);
            visited[i] = false;
            current.remove(current.size() - 1);
        }
    }

    /**
     * GPT 추천 백트래킹 문제
     * - 모든 조합
     * n과 r이 주어졌을 때, 1부터 n까지 수 중에서 r개를 뽑는 모든 조합을 출력하라.
     */
    @Test
    void p07() {
        int n = 4;
        int r = 2;
        int[][] expect = {
            {1, 2},
            {1, 3},
            {1, 4},
            {2, 3},
            {2, 4},
            {3, 4}
        };
        int[][] actual = p07Solution(n, r);
        Arrays.stream(actual)
            .map(Arrays::toString)
            .forEach(System.out::println);
    }

    int[][] p07Solution(int n, int r) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p07backtracking(1, n, r, answer, current);

        return answer.stream()
            .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
    }
    void p07backtracking(int start, int n, int r, List<List<Integer>> answer, List<Integer> current) {
        if (current.size() == r) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            p07backtracking(i + 1, n, r, answer, current);
            current.removeLast();
        }
    }

    /**
     * GPT 추천 백트래킹 문제
     * - 이진 수열 생성
     * 1부터 n까지 자리에 0 또는 1을 넣어 만들 수 있는 모든 이진 수열을 출력하라.
     */
    @Test
    void p08() {
        int n = 3;
        int[][] expect = {
            {0,0,0},
            {0,0,1},
            {0,1,0},
            {0,1,1},
            {1,0,0},
            {1,0,1},
            {1,1,0},
            {1,1,1}
        };

        int[][] actual = p08Solution(n);
        Arrays.stream(actual)
            .map(Arrays::toString)
            .forEach(System.out::println);
    }

    int[][] p08Solution(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p08backtracking(n, answer, current);

        return answer.stream()
            .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
    }

    void p08backtracking(int n, List<List<Integer>> answer, List<Integer> current) {
        if (current.size() == n) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i <= 1; i++) {
            current.add(i);
            p08backtracking(n, answer, current);
            current.removeLast();
        }
    }

    /**
     * GPT 추천 백트래킹 문제
     * - [Target Number (프로그래머스 유형)]
     * 주어진 숫자에 + 또는 -를 붙여 target을 만드는 경우의 수
     * 설명: 아래와 같은 5가지 방법이 있음.
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     */
    @Test
    void p09() {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int expect = 5;

        int actual = p09Solution(numbers, target);
        assertThat(actual).isEqualTo(expect);
    }

    int p09Solution(int[] numbers, int target) {
        AtomicInteger answer = new AtomicInteger();
        p09backtracking(0, 0, numbers, target, answer);

        return answer.get();
    }

    void p09backtracking(int index, int sum, int[] numbers, int target, AtomicInteger answer) {
        if (index == numbers.length) {
            if (sum == target) {
                answer.incrementAndGet();
            }
            return;
        }

        p09backtracking(index + 1, sum + numbers[index], numbers, target, answer);
        p09backtracking(index + 1, sum - numbers[index], numbers, target, answer);
    }

    /**
     * GPT 추천 백트래킹 문제
     * - 부분 집합 구하기 (Power Set)
     * 1부터 n까지의 수가 주어졌을 때,
     * 그 수들로 만들 수 있는 모든 부분 집합을 구하라.
     */

    @Test
    void p10() {
        int n = 3;
        int[][] expect = {
            {},
            {1},
            {2},
            {3},
            {1,2},
            {1,3},
            {2,3},
            {1,2,3}
        };
        int[][] actual = p10Solution(n);
        Arrays.stream(actual)
            .map(Arrays::toString)
            .forEach(System.out::println);
    }

    int[][] p10Solution(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p10backtracking(1, n, answer, current);

        return answer.stream()
            .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
    }

    void p10backtracking(int index, int n, List<List<Integer>> answer, List<Integer> current) {
        if (index > n) {
            answer.add(new ArrayList<>(current));
            return;
        }

        // 1. 현재 index를 포함하는 경우
        current.add(index);
        p10backtracking(index + 1, n, answer, current);
        current.removeLast();

        // 2. 현재 index를 포함하지 않는 경우
        p10backtracking(index + 1, n, answer, current);
    }

    /**
     * GPT 추천 백트래킹 문제
     * - N-Queen
     * n x n 체스판 위에 **n개의 퀸(Queen)**을 서로 공격하지 않도록 놓는 방법의 개수를 구하라.
     * 퀸은 가로, 세로, 대각선 모두 공격 가능하므로,
     *     같은 행 ❌
     *     같은 열 ❌
     *     같은 대각선 ❌
     *     에 퀸이 동시에 있을 수 없음.
     */

    @Test
    void p11() {
        int n = 4;
        int expect = 2;
        int actual = p11Solution(n);

        assertThat(actual).isEqualTo(expect);
    }

    int p11Solution(int n) {
        AtomicInteger answer = new AtomicInteger();
        boolean[] colDiag = new boolean[n];
        boolean[] leftDiag = new boolean[2 * n];
        boolean[] rightDiag = new boolean[2 * n];

        p11dfs(0, n, colDiag, leftDiag, rightDiag, answer);
        return answer.get();
    }

    void p11dfs(int row, int n, boolean[] colDiag, boolean[] leftDiag, boolean[] rightDiag, AtomicInteger answer) {
        if (row == n) {
            answer.incrementAndGet();
            return;
        }

        for (int col = 0; col < n; col++) {
            // 열/대각선에 이미 퀸이 있는 경우
            if (colDiag[col] || leftDiag[row + col] || rightDiag[row - col + n]) {
                continue;
            }
            colDiag[col] = true;
            leftDiag[row + col] = true;
            rightDiag[row - col + n] = true;

            p11dfs(row + 1, n, colDiag, leftDiag, rightDiag, answer);

            colDiag[col] = false;
            leftDiag[row + col] = false;
            rightDiag[row - col + n] = false;
        }
    }

    /**
     * GPT 추천 백트래킹 문제
     * - Word Search
     * 2차원 문자 배열 board와 단어 word가 주어졌을 때,
     * 보드에서 상하좌우로 인접한 칸을 따라 단어를 만들 수 있는지 true/false로 리턴하라.
     */

    @Test
    void p12() {
//        char[][] board = {
//            {'A','B','C','E'},
//            {'S','F','C','S'},
//            {'A','D','E','E'}
//        };
//        String word = "ABCCED";
//        boolean expect = true;

//        char[][] board = {
//            {'A', 'B', 'C', 'E'},
//            {'S', 'F', 'C', 'S'},
//            {'A', 'D', 'E', 'E'}
//        };
//        String word = "ABCB";
//        boolean expect = false;

//        char[][] board = {
//            {'A', 'B'},
//            {'C', 'D'}
//        };
//        String word = "A";
//        boolean expect = true;

        char[][] board = {
            {'A', 'B'},
            {'C', 'D'}
        };
        String word = "ABCD";
        boolean expect = false;

        boolean actual = p12Solution(board, word);
        assertThat(actual).isEqualTo(expect);
    }

    boolean p12Solution(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        // 북동남서
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] visited = new boolean[row][col];

        StringBuilder current = new StringBuilder();
        AtomicBoolean answer = new AtomicBoolean();

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (board[x][y] == word.charAt(0)) {
                    p12backtracking(x, y, row, col, dx, dy, board, word, 0, current, answer, visited);
                }
            }
        }


        return answer.get();
    }

    void p12backtracking(int x,int y, int row, int col, int[] dx, int[] dy, char[][] board, String word, int next, StringBuilder current, AtomicBoolean answer, boolean[][] visited) {
        if (answer.get()) {
            return;
        }

        if(current.length() == word.length()) {
            answer.set(true);
            return;
        }

        if(board[x][y] == word.charAt(next)) {
            current.append(word.charAt(next));
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny]) {
                    p12backtracking(nx, ny, row, col, dx, dy, board, word, next + 1, current, answer, visited);
                }
            }
            current.deleteCharAt(current.length() - 1);
            visited[x][y] = false;
        }
    }

    /**
     *  GPT 추천 백트래킹 문제
     *  - 숫자 조합으로 합 만들기
     *  N개의 자연수가 주어졌을 때, 이 숫자들을 조합해서 만들 수 있는 모든 부분 집합의 합을 구하고,
     * 이 중 서로 다른 합의 개수를 구하세요.
     * 만들 수 있는 부분합:
     *
     *     2
     *     4
     *     6
     *     2+4 = 6
     *     2+6 = 8
     *     4+6 = 10
     *     2+4+6 = 12
     * → 중복 제거 후: {2, 4, 6, 8, 10, 12} → 총 6가지
     */
    @Test
    void p13() {
        int n = 3;
        int[] nums = {2, 4, 6};
        int expect = 6;

        int actual = p13Solution(n, nums);
        assertThat(actual).isEqualTo(expect);
    }

    int p13Solution(int n, int[] nums) {
        List<Integer> current = new ArrayList<>();
        Set<Integer> ans = new HashSet<>();

        p13backtracking(0, n, nums, ans, current);

        return ans.size();
    }

    void p13backtracking(int start, int n, int[] nums, Set<Integer> ans, List<Integer> current) {
        if (start >= nums.length) {
            return;
        }

        for (int i = start; i < n; i++) {
            int num = nums[i];
            current.add(num);
            ans.add(current.stream().mapToInt(val -> val).sum());

            p13backtracking(i + 1, n, nums, ans, current);

            current.removeLast();
        }
    }

    /**
     *  GPT 추천 백트래킹 문제
     *  - 암호만들기
     *  서로 다른 L개의 알파벳 소문자 조합으로 암호를 만든다.
     *  암호는 다음 조건을 만족해야 함:
     *     알파벳 오름차순 정렬이어야 함
     *     모음(a, e, i, o, u) 최소 1개
     *     자음은 최소 2개
     *   첫 줄: L C (L은 암호 길이, C는 사용 가능한 문자 수)
     *   둘째 줄: C개의 문자 (공백으로 구분, 모두 서로 다름)
     *   출력: 조건을 만족하는 모든 암호를 사전 순 정렬된 순서로 출력
     */
    @Test
    void p14() {
        int L = 4; // 암호 길이
        int C = 6; // 사용가능한 알파벳 수
        char[] chars = {'a','t','c','i','s','w'}; // 사용가능한 알파벳

        // 14개
        String[] expect = {
                "acis",
                "acit",
                "aciw",
                "acst",
                "acsw",
                "actw",
                "aist",
                "aisw",
                "aitw",
                "astw",
                "cist",
                "cisw",
                "citw",
                "istw"
        };
        String[] actual = p14Solution(L, C, chars);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p14Solution(int L, int C, char[] chars) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        List<String> answer = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        Arrays.sort(chars);

        p14backtracking(0, L, C, chars, vowels, answer, current);
        return answer.toArray(new String[0]);
    }

    void p14backtracking(int idx, int L, int C, char[] chars, Set<Character> vowels, List<String> answer, StringBuilder current) {
        if (current.length() == L) {
            int vowelCount = 0;
            int consonantCount = 0;

            for (int i = 0; i < current.length(); i++) {
                char c = current.charAt(i);
                if (vowels.contains(c)) vowelCount++;
                else consonantCount++;
            }

            if (vowelCount >= 1 && consonantCount >= 2) {
                answer.add(current.toString());
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            current.append(chars[i]);

            p14backtracking(i + 1, L, C, chars, vowels, answer, current);

            current.deleteCharAt(current.length() - 1);
        }
    }


    /**
     * 정수 n, k가 주어졌을 때, 1~n까지 숫자 중 k개를 뽑는 모든 조합을 구하세요.
     *
     *
     *     출력: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
     */
    @Test
    void p15() {
        int n = 4;
        int k = 2;

        p15Solution(n, k);
    }
    void p15Solution(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        p15backtracking(1, n,k, answer, current);

        for (List<Integer> comb : answer) {
            System.out.println(comb);
        }
    }
    void p15backtracking(int start, int n, int k, List<List<Integer>> answer, List<Integer> current) {
        if (current.size() == k) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            p15backtracking(i + 1, n, k, answer, current);
            current.removeLast();
        }
    }

    /**
     * ✅ 문제 2. 순열 구하기 (Permutation)     *
     *     설명: 정수 배열 nums가 주어졌을 때, 모든 가능한 순열을 구하세요.     *
     *     입력: nums = [5,9,2]     *
     *     출력: [[5,9,2], [5,2,9], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
     */
    @Test
    void p16(){
        int[] nums = {5,9,2};
        p16Solution(nums);
    }
    void p16Solution(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        p16backtracking(nums, nums.length, answer, current, visited);
        for (List<Integer> a : answer) {
            System.out.println(a);
        }
    }

    private void p16backtracking(int[] nums, int length, List<List<Integer>> answer, List<Integer> current, boolean[] visited) {
        if (current.size() == length) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(nums[i]);
                p16backtracking(nums, length, answer, current, visited);
                visited[i] = false;
                current.removeLast();
            }
        }

    }

    /**
     * ✅ 문제 3. 부분집합 구하기 (Subsets)
     *     설명: 정수 배열 nums가 주어졌을 때, 가능한 모든 부분집합을 구하세요.
     *     입력: nums = [1, 2]
     *     출력: [[], [1], [2], [1, 2]]
     */
    @Test
    void p17() {
        int[] nums = {1, 2,3};
        p17Solution(nums);
    }

    void p17Solution(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p17backtracking(0, nums, answer, current);

        for (List<Integer> a : answer) {
            System.out.println(a);
        }
    }
    void p17backtracking(int start, int[] nums, List<List<Integer>> answer, List<Integer> current) {
        answer.add(new ArrayList<>(current)); // 언제나 지금 상태 저장

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            p17backtracking(i+1, nums, answer, current);
            current.removeLast();
        }
    }

    /**
     * ✅ 문제 4. 대소문자 조합 만들기 (Letter Case Permutation)
     *     설명: 영문자와 숫자가 섞인 문자열 s가 주어질 때, 영문자의 대소문자를 바꿔 만들 수 있는 모든 문자열 조합을 구하세요.
     *     입력: "a1b2"
     *     출력: ["a1b2", "a1B2", "A1b2", "A1B2"]
     */
    @Test
    void p18() {
        String input = "a1b2";

        p18Solution(input);
    }

    void p18Solution(String input) {
        List<String> answer = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        p18backtracking(0, input, answer, current);

        for (String a : answer) {
            System.out.println(a);
        }
    }

    void p18backtracking(int start, String input, List<String> answer, StringBuilder current) {
        if (current.length() == input.length()) {
            answer.add(current.toString());
            return;
        }

        char c = input.charAt(start);
        if(Character.isAlphabetic(c)) {
            current.append(Character.toLowerCase(c));
            p18backtracking(start + 1, input, answer, current);
            current.deleteCharAt(current.length() - 1);

            current.append(Character.toUpperCase(c));
            p18backtracking(start + 1, input, answer, current);
            current.deleteCharAt(current.length() - 1);
        } else {
            current.append(c);
            p18backtracking(start + 1, input, answer, current);
            current.deleteCharAt(current.length() - 1);
        }
    }

    /**
     * ✅ 문제 5. Combination Sum
     *     설명: 정수 배열 candidates와 목표값 target이 주어졌을 때, 숫자를 중복 사용해서 합이 target이 되는 모든 조합을 구하세요.
     *     입력: candidates = [2,3,6,7], target = 7
     *     출력: [[2,2,3], [7]]
     */
    @Test
    void p19() {
        int[] nums = {2,3,6,7};
        int target = 7;

        p19Solution(nums, target);
    }

    void p19Solution(int[] nums, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        p19backtracking(0, nums, target, answer, current, 0);

        for (List<Integer> a : answer) {
            System.out.println(a);
        }
    }

    void p19backtracking(int start, int[] nums, int target, List<List<Integer>> answer, List<Integer> current, int sum) {
        if (sum == target) {
            answer.add(new ArrayList<>(current));
            return;
        }

        if (sum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            p19backtracking(i, nums, target, answer, current, sum + nums[i]);
            current.removeLast();
        }
    }

    /**
     * ✅ 문제 6. 전화번호 문자 조합 (Phone Letter Combination)     *
     *     설명: 숫자 문자열이 주어졌을 때, 각 숫자에 해당하는 문자 조합의 모든 가능한 문자열을 구하세요. (ex. 숫자 2 → a, b, c)     *
     *     입력: "23"
     *     출력: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
     */
    @Test
    void p20() {
        String s = "23";
        p20Solution(s);
    }
    void p20Solution(String s) {
        List<String> answer = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        Map<Character, String> map = Map.of(
                '2', "abc",
                '3', "def",
                '4', "ghi",
                '5', "jkl",
                '6', "mno",
                '7', "pqrs",
                '8', "tuv",
                '9', "wxyz"
        );

        p20backtracking(s, 0, map, answer, current);

        for (String a : answer) {
            System.out.println(a);
        }
    }

    void p20backtracking(String digits, int idx, Map<Character, String> map, List<String> answer, StringBuilder current) {
        if (current.length() == digits.length()) {
            answer.add(current.toString());
            return;
        }

        String alphabets = map.get(digits.charAt(idx));
        for (int i = 0; i < alphabets.length(); i++) {
            current.append(alphabets.charAt(i));
            p20backtracking(digits, idx + 1, map, answer, current);
            current.deleteCharAt(current.length() - 1);
        }

    }

    /**
     * ✅ 문제 7. 괄호 만들기 (Generate Parentheses)
     *     설명: 정수 n이 주어졌을 때, n쌍의 올바른 괄호 조합을 모두 출력하세요.
     *     입력: n = 2
     *     출력:
     *     [
     *          "(())",
     *          "()()"
     *     ]
     *     입력: n = 3
     *     출력:
     *     [
     *          "((()))",
     *          "(()())",
     *          "(())()",
     *          "()(())",
     *          "()()()"
     *      ]
     */
    @Test
    void p21() {
        int n = 3;

        p21Solution(n);
    }

    void p21Solution(int n) {
        List<String> answer = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        p21backtracking(0, 0, n, answer, current);

        for (String a : answer) {
            System.out.println(a);
        }
    }

    void p21backtracking(int left, int right, int n, List<String> answer, StringBuilder current) {
        if (current.length() == (n * 2)) {
            answer.add(current.toString());
            return;
        }

        if (left < n) {
            current.append("(");
            p21backtracking(left + 1, right, n, answer, current);
            current.deleteCharAt(current.length() - 1);
        }
        if (right < left) {
            current.append(")");
            p21backtracking(left, right + 1, n, answer, current);
            current.deleteCharAt(current.length() - 1);
        }
    }



}
