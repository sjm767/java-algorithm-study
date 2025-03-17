package com.jaeshim.java.codingtest.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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

    /**
     * 양과 늑대 (*****)
     */
    @Test
    void p04() {
        int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges = {
            {0, 1},
            {0, 2},
            {1, 3},
            {1, 4},
            {2, 5},
            {2, 6},
            {3, 7},
            {4, 8},
            {6, 9},
            {9, 10}
        };
        int expect = 5;
        int actual = p04Solution(info, edges);
    }

    int p04Solution(int[] info, int[][] edges) {
        List<Integer>[] tree; // 트리 정보를 저장
        tree = new ArrayList[info.length];

        buildTree(info, edges, tree);
        int answer = 0; // 정답을 저장할 변수

        Queue<Info> queue = new ArrayDeque<>(); // BFS를 위한 큐 생성 및 초기 상태 설정
        queue.add(new Info(0, 1, 0, new HashSet<>()));

        while (!queue.isEmpty()) {
            Info now = queue.poll();

            answer = Math.max(answer, now.sheep);
            now.visited.addAll(tree[now.node]);

            for (int next : now.visited) {
                HashSet<Integer> set = new HashSet<>(now.visited);
                set.remove(next);

                if (info[next] == 1) { // 늑대일 경우
                    if (now.sheep != now.wolf + 1) {
                        queue.add(new Info(next, now.sheep, now.wolf + 1, set));
                    }
                } else {
                    queue.add(new Info(next, now.sheep + 1, now.wolf, set));
                }
            }
        }



        return 0;
    }

    void buildTree(int[] info, int[][] edges, List<Integer>[] tree) {
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
    }

    static class Info {
        int node, sheep, wolf;
        HashSet<Integer> visited;

        public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }


    /**
     * 길 찾기 게임 (****)
     */
    @Test
    void p05() {
       int[][] nodeinfo = {
               {5,3}, //1
               {11,5}, //2
               {13,3}, //3
               {3,5}, //4
               {6,1}, //5
               {1,3}, //6
               {8,6}, //7
               {7,2}, //8
               {2,2} //9
       };
       int[][] expect = {
               {7,4,6,9,1,8,5,2,3},
               {9,6,5,8,1,4,3,2,7}
       };

        int[][] actual = p05Solution(nodeinfo);
        assertThat(actual).isEqualTo(expect);

    }

    int[][] p05Solution(int[][] nodeinfo) {
        int[][] newNodeInfo = new int[nodeinfo.length][3];
        for (int i = 0; i < nodeinfo.length; i++) {
            newNodeInfo[i][0] = nodeinfo[i][0];
            newNodeInfo[i][1] = nodeinfo[i][1];
            newNodeInfo[i][2] = i + 1;
        }


        // y 기준으로 정렬해서 root 노드를 정함
        Arrays.sort(newNodeInfo, (a, b) -> {
            // b[1] - a[1] 을 하면 내림차순 정렬이 됩니다.
            return b[1] - a[1];
        });

        Node root = new Node(newNodeInfo[0][0], newNodeInfo[0][1], newNodeInfo[0][2]); // root 노드 설정

        // 노드 순회하면서 트리 만들기
        for (int i = 1; i < newNodeInfo.length; i++) {
            int x = newNodeInfo[i][0];
            int y = newNodeInfo[i][1];
            int val = newNodeInfo[i][2];

            Node parent = root; // root 노드부터 순회시작함
            Node current = parent;
            while (true) {
                int cx = current.x;
                int cy = current.y;

                if (x < cx) {
                    current = parent.left;
                } else {
                    current = parent.right;
                }

                if (current == null) {
                    Node newNode = new Node(x, y, val);
                    if(x < cx) {
                        parent.left = newNode;
                    } else {
                        parent.right = newNode;
                    }
                    break;
                } else {
                    parent = current;
                }
            }
        }

        // 전위 순회
        List<Integer> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList);

        // 후위 순회
        List<Integer> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList);

        int[][] answer = new int[2][preOrderList.size()];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();

      return answer;
    }

    void preOrder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    void postOrder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }

        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }


    static class Node {
        public int x;
        public int y;
        public int val;

        public Node left;
        public Node right;

        public Node(int x, int y,int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }





}
