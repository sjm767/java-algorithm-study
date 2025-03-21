package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ch11_graph {

    /**
     * 깊이 우선 탐색 순회 (*)
     */
    @Test
    void p01() {
        char[][] graph = {
                {'1', '2'},
                {'2', '3'},
                {'3', '4'},
                {'4', '5'}
        };
        char start = '1';
        int n = 5;
        char[] expect = {'1', '2', '3', '4', '5'};

        char[] actual = p01Solution(graph, start, n);
        assertThat(actual).isEqualTo(expect);
    }

    char[] p01Solution(char[][] graph, char start, int n) {
        Set<Character> answer = new HashSet<>();
        // 인접 리스트 이용하여 그래프 만들기
        ArrayList<Integer>[] lists = new ArrayList[n + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<Integer>();
        }

        for (char[] gg : graph) {
            int sNode = Character.getNumericValue(gg[0]);
            int dest = Character.getNumericValue(gg[1]);

            lists[sNode].add(dest);
        }

        int iStart = Character.getNumericValue(start);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(iStart); // 스택에 넣고 시작

        while (!stack.isEmpty()) {
            Integer nodeIdx = stack.pop();
            answer.add((char) (nodeIdx + '0')); // 방문지로 기록

            ArrayList<Integer> nodes = lists[nodeIdx];
            for (Integer nn : nodes) {
                stack.push(nn);
            }
        }

        return answer.stream()
                .map(i -> (char) (i + '0'))
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString()
                .toCharArray();
    }

    /**
     * 너비 우선 탐색 순회 (*)
     */
    @Test
    void p02() {
//        int[][] graph = {
//                {1, 2},
//                {1, 3},
//                {2, 4},
//                {2, 5},
//                {3, 6},
//                {3, 7},
//                {4, 8},
//                {5, 8},
//                {6, 9},
//                {7, 9}
//        };
//        int start = 1;
//        int n = 9;
//        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[][] graph = {
                {1, 3},
                {3, 4},
                {3, 5},
                {5, 2}
        };
        int start = 1;
        int n = 5;
        int[] expect = {1, 3, 4, 5, 2};

        int[] actual = p02Solution(graph, start, n);
    }

    int[] p02Solution(int[][] graph, int start, int n) {
        // 인접 행렬 만들기
        ArrayList<Integer>[] adjList = new ArrayList[n + 1];
        for (int[] g : graph) {
            int src = g[0];
            int dest = g[1];

            // 리스트가 초기화되어 있지 않으면 초기화 한다.
            if (adjList[src] == null) {
                adjList[src] = new ArrayList<>();
            }

            adjList[src].add(dest);
        }

        // 너비 우선 탐색 시작
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> answer = new LinkedHashSet<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(start);
        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            // 방문지로 기록
            visited[current] = true;
            answer.add(current);

            // 아직 방문하지 않았으면 추가
            if (adjList[current] != null) {
                for (Integer adj : adjList[current] ) {
                    if (!visited[adj]) {
                        queue.add(adj);
                    }
                }
            }
        }


        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    /**
     * 다익스트라 알고리즘 (***)
     */
    @Test
    void p03() {
        // 출발지노드, 목적지노드, 가중치
        int[][] graph = {
                {0, 1, 9},
                {0, 2, 3},
                {1, 0, 5},
                {2, 1, 1}
        };
        int start = 0;
        int n = 3;
        int[] expect = {0, 4, 3};

        int[] actual = p03Solution(graph, start, n);
    }

    int[] p03Solution(int[][] graph, int start, int n) {
        // 인접리스트로 그래프 만들기
        ArrayList<DijkstraNode>[] adjList = new ArrayList[n];
        for (int[] g : graph) {
            int src = g[0];
            int dest = g[1];
            int weight = g[2];

            if (adjList[src] == null) {
                adjList[src] = new ArrayList<>();
            }

            adjList[src].add(new DijkstraNode(dest, weight));
        }
        // 초기화
        List<DijkstraResult> dList = new DijkstraResult;
        for (int i = 0; i < n; i++) {
            dList.add(new DijkstraResult(i, Integer.MAX_VALUE, -1));
        }
        DijkstraResult startNode = dList.get(start);
        startNode.lastVisited = 0;
        startNode.minWeight = 0;

        // 방문지 기록
        boolean[] visited = new boolean[n];
        ArrayDeque<DijkstraResult> stack = new ArrayDeque<>();
        stack.push(dList.get(start)); // 시작 노드 넣고 시작

        while (!stack.isEmpty()) {
            DijkstraResult current = stack.pop();
            ArrayList<DijkstraNode> adjNodes = adjList[current.idx];

            for (DijkstraNode adj : adjNodes) {
                DijkstraResult dijkstraResult = dList.get(adj.dest);

                // 거리가 더 짧으면
                if (adj.weight < dijkstraResult.minWeight) {
                    
                }

            }
        }


        return null;
    }
    static class DijkstraNode {
        public int dest;
        public int weight;

        public DijkstraNode(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class DijkstraResult {
        public int idx;
        public int minWeight;
        public int lastVisited;

        public DijkstraResult(int idx, int minWeight, int lastVisited) {
            this.idx = idx;
            this.minWeight = minWeight;
            this.lastVisited = lastVisited;
        }

        public int getMinWeight() {
            return minWeight;
        }
    }
}
