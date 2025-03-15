package com.jaeshim.java.codingtest.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ch10_set {

    /**
     * 간단한 유니온-파인드 알고리즘 구현하기 (**)
     */
    @Test
    void p01() {
//        int[][] operations = {
//                {0, 0, 1},
//                {0, 1, 2},
//                {1, 1, 2}
//        };
//        int k = 3;
//        boolean[] expect = {true};

        int[][] operations = {
                {0, 0, 1},
                {1, 1, 2},
                {0, 1, 2},
                {1, 0, 2}
        };
        int k = 4;
        boolean[] expect = {false, true};

        boolean[] actual = p01Solution(k, operations);
        assertThat(actual).isEqualTo(expect);
    }

    boolean[] p01Solution(int k, int[][] operations) {
        int[] set = new int[k];
        List<Boolean> answer = new ArrayList<>();

        // 노드 초기화
        for (int i = 0; i < set.length; i++) {
            set[i] = i;
        }

        for (int[] operation : operations) {
            int oper = operation[0];
            int node1 = operation[1];
            int node2 = operation[2];

            if (oper == 0) { //유니온 연산
                union(set, node1, node2);

            } else { // 파인드 연산
                answer.add(find(set, node1) == find(set, node2));
            }
        }

        boolean[] result = new boolean[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }

    // 파인드 연산
    static int find(int[] set, int x) {
        if(set[x] == x) { // 루트 노드라는 뜻
            return x;
        }
        return find(set, set[x]);
    }

    // 유니온 연산
    static void union(int[] set, int x, int y) {
        int r1 = find(set, x);
        int r2 = find(set, y);

        set[r2] = r1;
    }
}
