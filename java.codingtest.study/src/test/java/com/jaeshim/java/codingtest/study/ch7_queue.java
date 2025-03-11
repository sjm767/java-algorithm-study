package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
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

        while (queue.size() > 1) {
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

    /**
     * 기능 개발 (**)
     */
    @Test
    void p02() {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] expect = {2, 1};

//        int[] progresses = {95, 90, 99, 99, 80, 99};
//        int[] speeds = {1, 1, 1, 1, 1, 1};
//        int[] expect = {1, 3, 2};

        int[] actual = p02Solution(progresses, speeds);
        assertThat(actual).isEqualTo(expect);
    }

//    int[] p02Solution(int[] progresses, int[] speeds) {
//        ArrayDeque<FunctionDevInfo> queue = new ArrayDeque<>();
//        List<Integer> result = new ArrayList<>();
//
//        // 큐에 기능개발 정보 넣기
//        for (int i = 0; i < progresses.length; i++) {
//            queue.offer(new FunctionDevInfo(progresses[i], speeds[i]));
//        }
//
//        int complete = 0;
//        while (!queue.isEmpty()) {
//
//            // 그 날의 작업 진행
//            for (int i = 0; i < queue.size(); i++) {
//                FunctionDevInfo f = queue.pollFirst();
//                if (f.progress < 100) {
//                    f.progress += f.speed;
//                }
//                queue.addLast(f);
//            }
//
//            while (true) {
//                // 작업이 완료되었으면 큐에서 제거하고 완료횟수 추가
//                if (!queue.isEmpty() && queue.peekFirst().progress >= 100) {
//                    queue.pollFirst();
//                    complete++;
//                }
//                else {
//                    break;
//                }
//            }
//            if(complete > 0) {
//                result.add(complete);
//            }
//
//            complete = 0;
//        }
//        return result.stream().mapToInt(i -> i).toArray();
//    }
//
//    static class FunctionDevInfo {
//        public int progress;
//        public int speed;
//
//        public FunctionDevInfo(int progress, int speed) {
//            this.progress = progress;
//            this.speed = speed;
//        }
//    }

    int[] p02Solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        // 큐에 완료일 넣기
        for (int i = 0; i < progresses.length; i++) {
            int completeDay = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            queue.offer(completeDay);
        }

        while (!queue.isEmpty()) {
            int completeCount = 1;
            Integer poll = queue.poll();

            while (!queue.isEmpty() && queue.peek() <= poll) {
                completeCount++;
                queue.poll();
            }

            result.add(completeCount);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 카드 뭉치 (**)
     */
    @Test
    void p03() {
        String[] cards1 = {"i", "drink", "water"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};

        String expect = "Yes";

//        String[] cards1 = {"i", "water", "drink"};
//        String[] cards2 = {"want", "to"};
//        String[] goal = {"i", "want", "to", "drink", "water"};
//
//        String expect = "No";

        String actual = p03Solution(cards1, cards2, goal);
        assertThat(actual).isEqualTo(expect);
    }

    String p03Solution(String[] cards1, String[] card2, String[] goal) {
        String result = "Yes";

        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        Queue<String> goalQueue = new ArrayDeque<>();

        // 카드 뭉치1 추가
        for (String card : cards1) {
            queue1.offer(card);
        }

        // 카드 뭉치2 추가
        for (String card : card2) {
            queue2.offer(card);
        }

        for (String card : goal) {
            goalQueue.offer(card);
        }

        while (!goalQueue.isEmpty()) {
            if (!queue1.isEmpty() && queue1.peek().equals(goalQueue.peek())) {
                queue1.poll();
                goalQueue.poll();
            } else if (!queue2.isEmpty() && queue2.peek().equals(goalQueue.peek())) {
                queue2.poll();
                goalQueue.poll();
            } else {
                return "No";
            }
        }

        return "Yes";
    }


}
