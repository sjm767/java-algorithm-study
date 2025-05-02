package com.jaeshim.java.codingtest.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 생각나는 것 빠르게 짜보기 위해 사용
 */
public class ch0_quickCode {

    public int answer = 0;
    @Test
    void p01() {
        int k = 80;
        int[][] dungeons = {
                {50, 40},
                {80, 20},
                {30, 10}
        };

        p01Solution(k, dungeons);
    }

    void p01Solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        p01backtracking(k, dungeons, 0, visited);

    }

    void p01backtracking(int k, int[][] dungeons, int complete, boolean[] visited) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                p01backtracking(k- dungeons[i][1], dungeons, complete + 1, visited);
                answer = Math.max(answer, complete);
                visited[i] = false;
            }
        }
    }
}
