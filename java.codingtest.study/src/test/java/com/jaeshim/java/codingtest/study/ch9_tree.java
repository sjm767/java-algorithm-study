package com.jaeshim.java.codingtest.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ch9_tree {

    private static final Logger log = LoggerFactory.getLogger(ch9_tree.class);

    /**
     * 트리 순회 (*)
     */
    @Test
    void p01() {
        int[] nodes = {1, 2, 3, 4, 5, 6, 7};
        String[] expect = {
                "1 2 4 5 3 6 7",
                "4 2 5 1 6 3 7",
                "4 5 2 6 7 3 1"
        };

        String[] actual = p01Solution(nodes);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p01Solution(int[] nodes) {
        int[] copiedNodes = new int[nodes.length + 1]; // 모든 요소가 기본값 0
        System.arraycopy(nodes, 0, copiedNodes, 1, nodes.length);

        String[] answer = new String[3];

        // 전위
        answer[0] = preOrder(copiedNodes, 1).trim();
        // 중위
        answer[1] = inOrder(copiedNodes, 1).trim();
        // 후위
        answer[2] = postOrder(copiedNodes, 1).trim();

        return answer;
    }
    private static String preOrder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return nodes[idx] + " "
                + preOrder(nodes, idx * 2)
                + preOrder(nodes, idx * 2 + 1);
    }

    private static String inOrder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return inOrder(nodes, idx * 2)
                + nodes[idx] + " "
                + inOrder(nodes, idx * 2 + 1);
    }

    private static String postOrder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return postOrder(nodes, idx * 2)
                + postOrder(nodes, idx * 2 + 1)
                + nodes[idx] + " ";
    }

    /**
     * 예상 대진표 (*)
     */
    @Test
    void p02() {
        int N = 8;
        int A = 4;
        int B = 7;
        int expect = 3;

        int actual = p02Solution(N, A, B);
        assertThat(actual).isEqualTo(expect);
    }

    int p02Solution(int N, int A, int B) {
        return p02Round(1, A, B);
    }

    int p02Round(int r, int a, int b) {
        if (a == b) {
            return r;
        }

        return p02Round(r + 1, a / 2, b / 2);
    }

    /**
     * 다단계 칫솔 판매 (**)
     */
    @Test
    void p03() {
//        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}; // 각 판매원의 이름
//        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}; //각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름
//        String[] seller = {"young", "john", "tod", "emily", "mary"}; //판매량
//        int[] amount = {12, 4, 2, 5, 10};
//        int[] expect = {360, 958, 108, 0, 450, 18, 180, 1080};

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}; // 각 판매원의 이름
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie","edward"}; //각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름
        String[] seller = {"sam","emily","jaimie","edward"}; //판매량
        int[] amount = {2, 3, 5, 4};
        int[] expect = {0, 110, 378, 180, 270, 450, 0, 0};

        int[] actual = p03Solution(enroll, referral, seller, amount);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p03Solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 각 사람의 부모(추천인) 관계와 수익을 초기화
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> profit = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            profit.put(enroll[i], 0);
            if (!referral[i].equals("-")) {
                parent.put(enroll[i], referral[i]);
            }
        }
        // 각 판매에 대해 수익 분배
        for (int i = 0; i < seller.length; i++) {
            String current = seller[i];
            int total = amount[i] * 100;

            // 현재 판매 금액을 분배하면서 반복 (10%씩 위로 전달)
            while (true) {
                int commission = (int) Math.round(total * 0.1);
                int earned = total - commission;
                profit.put(current, profit.get(current) + earned);

                // 더 이상 분배할 금액이 없거나, 부모(추천인)가 없으면 종료
                if (commission < 1 || !parent.containsKey(current)) {
                    break;
                }

                current = parent.get(current);
                total = commission;
            }
        }

        // enroll 순서대로 결과 배열 생성
        int[] result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            result[i] = profit.get(enroll[i]);
        }

        return result;
    }


}
