package com.jaeshim.java.codingtest.study.book;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * 생각나는 것 빠르게 짜보기 위해 사용
 */
public class ch0_quickCode {

    public int diffMax = 0;
    int[] answer;
    @Test
    void p01() {
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] expect = {0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0};

        p01Solution(n, info);
        assertThat(answer).isEqualTo(expect);
    }

    void p01Solution(int n, int[] info) {
        answer = new int[info.length];
        boolean[] visited = new boolean[info.length];

        int[] current = new int[info.length];
        p01backtracking(0, n, info, visited, current);

    }

    void p01backtracking(int start, int n, int[] info, boolean[] visited, int[] current) {
        if (n == 0) {
            int apeachSum = 0;
            int ryanSum = 0;
            for (int i = 0; i < info.length; i++) {
                if (!visited[i] && info[i] > 0) {
                    apeachSum += 10 - i;
                } else if (visited[i]){
                    ryanSum += 10 - i;
                }
            }

            if(ryanSum - apeachSum > diffMax) {
                answer = Arrays.copyOf(current, current.length);
                diffMax = ryanSum - apeachSum;
            }
            return;
        }

        for (int i = start; i < info.length; i++) {
            if (!visited[i] && n > info[i]) {
                visited[i] = true;
                current[i] += info[i] + 1;
                p01backtracking(i + 1, n - info[i] - 1, info, visited, current);
                current[i] -= info[i] + 1;
                visited[i] = false;
            }
        }
    }
}
