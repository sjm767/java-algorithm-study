package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ch7_queue {


    /**
     * 요세푸스 문제 (**)
     */
    @Test
    void p01() {
        int N = 5;
        int K = 2;

        int expect = 3;
        int actual = p01Solution(N, K);

        assertThat(actual).isEqualTo(expect);
    }

    int p01Solution(int N, int K) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 큐에 넣기
        for (int n = 1; n <= N; n++) {
            queue.offer(n);
        }

        while(queue.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                queue.addLast(Objects.requireNonNull(queue.pollFirst()));
            }
            queue.pollFirst();
        }

        if (queue.isEmpty()) {
            return 0;
        }

        // 큐에 남은 마지막 멤버가 승리자임
        return queue.pollFirst();
    }


}
