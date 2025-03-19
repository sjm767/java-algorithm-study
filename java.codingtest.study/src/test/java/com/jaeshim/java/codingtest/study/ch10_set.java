package com.jaeshim.java.codingtest.study;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class ch10_set {

    /**
     * 간단한 유니온-파인드 알고리즘 구현하기 (**)
     */
    @Test
    void p01() {
        int k = 3;
//        int[][] operations = {
//                {0,0,1},
//                {0,1,2},
//                {1,1,2}
//        };
//        boolean[] expect = {true};
        int[][] operations = {
                {0, 0, 1},
                {1, 1, 2},
                {0, 1, 2},
                {1, 0, 2}
        };
        boolean[] expect = {false, true};

        boolean[] actual = p01Solution(k, operations);
        assertThat(actual).isEqualTo(expect);

    }

    static boolean[] p01Solution(int k, int[][] operations) {
        // 집합 생성
        int[] set = new int[k];
        for (int i = 0; i < k; i++) {
            set[i] = i;
        }

        List<Boolean> answer = new ArrayList<>();

        for (int[] oper : operations) {
            int x = oper[1];
            int y = oper[2];
            // union 연산
            if(oper[0] == 0) {
                union(x, y, set);
            }
            // find 연산
            else {
                answer.add(isSameSet(x, y, set));
            }
        }

        boolean[] result = new boolean[answer.size()];
        IntStream.range(0, answer.size())
                .forEach(i -> result[i] = answer.get(i));

        return result;
    }

    static void union(int x, int y, int[] set) {
        int rx = find(x, set); //x의 root 찾기
        int ry = find(y, set); //y의 root 찾기

        // 둘의 루트가 다르면 x의 하위에 y를 둠
        if (rx != ry) {
            set[ry] = rx;
        }
    }

    static int find(int x, int[] set) {
        if (set[x] == x) {
            return x;
        }
        return find(set[x], set);
    }

    static boolean isSameSet(int x, int y, int[] set) {
        return find(x, set) == find(y, set);
    }

    /**
     * 폰켓몬 (**)
     */
    @Test
    void p02() {
//        int[] nums = {3, 1, 2, 3};
//        int expect = 2;

//        int[] nums = {3, 3, 3, 2, 2, 4};
//        int expect = 3;

        int[] nums = {3, 3, 3, 2, 2, 2};
        int expect = 2;

        int actual = p02Solution(nums);
        assertThat(actual).isEqualTo(expect);
    }
    int p02Solution(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int half = nums.length / 2;


        return Math.min(half, set.size());
    }

    /**
     * 영어 끝말 잇기 (*)
     */
    @Test
    void p03() {
//        int n = 3; // 사람 수 (1부터 시작)
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//        int[] expect = {3, 3};

//        int n = 5; // 사람 수 (1부터 시작)
//        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
//        int[] expect = {0, 0};

        int n = 2; // 사람 수 (1부터 시작)
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] expect = {1, 3};

        int[] actual = p03Solution(n, words);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p03Solution(int n, String[] words) {
        int[] count = new int[n + 1]; // 횟수를 카운팅
        Set<String> set = new HashSet<>(); // 이미 나온 영단어
        int[] answer = new int[2];

        int order = 1;
        char lastChar = words[0].charAt(0);
        for (String word : words) {
            count[order]++;

            if (lastChar != word.charAt(0) || set.contains(word)) {
                answer[0] = order;
                answer[1] = count[order];
                break;
            }

            lastChar = word.charAt(word.length() - 1);
            set.add(word);
            order = (order % n) + 1;
        }
        return answer;
    }

    /**
     * 섬 연결하기 (***)
     */
    @Test
    void p04() {
        int n = 4;
        int[][] costs = {
            {0,1,1},
            {0,2,2},
            {1,2,5},
            {1,3,1},
            {2,3,8}
        };
        int expect = 4;
        int actual = p04Solution(n, costs);
        assertThat(actual).isEqualTo(expect);
    }

    int p04Solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        IntStream.range(0, n).forEach(idx -> parent[idx] = idx);

        // 길이가 짧은 순서대로 정렬
        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        int edges = 0;
        for (int[] cost : costs) {
            int start = cost[0];
            int end = cost[1];
            int val = cost[2];

            if (find(parent, start) != find(parent, end)) {
                union(start, end, parent);
                answer += val;
                edges++;
            }

            if (edges == n - 1) {
                break;
            }

        }

        return answer;

//        int answer = 0;
//        // 트리 구조 초기화
//        int[] nodes = new int[n];
//        IntStream.range(0, n).forEach(idx -> nodes[idx] = idx);
//
//        // 길이가 짧은 순서대로 정렬
//        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
//
//        Set<Integer> set = new HashSet<>();
//        for (int[] cost : costs) {
//            int start = cost[0];
//            int end = cost[1];
//            int val = cost[2];
//
//            if (!set.contains(end)) {
//                set.add(start);
//                set.add(end);
//                answer += val;
//            }
//
//            if (set.size() == n) {
//                break;
//            }
//        }
//
//        return answer;
    }

    int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent, parent[x]); // 경로 압축
    }

    void union(int[] parent, int x, int y) {
        int root1 = find(parent, x);
        int root2 = find(parent, y);

        parent[root2] = root1;
    }

}
