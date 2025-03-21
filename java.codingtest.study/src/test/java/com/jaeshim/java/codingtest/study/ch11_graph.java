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
//        // 출발지노드, 목적지노드, 가중치
//        int[][] graph = {
//                {0, 1, 9},
//                {0, 2, 3},
//                {1, 0, 5},
//                {2, 1, 1}
//        };
//        int start = 0;
//        int n = 3;
//        int[] expect = {0, 4, 3};

        // 출발지노드, 목적지노드, 가중치
        int[][] graph = {
                {0, 1, 1},
                {1, 2, 5},
                {2, 3, 1}
        };
        int start = 0;
        int n = 4;
        int[] expect = {0, 1, 6, 7};

        int[] actual = p03Solution(graph, start, n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p03Solution(int[][] graph, int start, int n) {
        // 인접 행렬로 표현하기
        int[][] adjMatrix = new int[n][n];
        for (int[] g : graph) {
            adjMatrix[g[0]][g[1]] = g[2];
        }

        // 다익스트라 순회를 위한 리스트 생성
        List<DijkstraNode> dijkstraNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 시작점인 경우 초기화
            if (i == start) { 
                dijkstraNodes.add(new DijkstraNode(i, 0, start));
            } else {
                dijkstraNodes.add(new DijkstraNode(i, Integer.MAX_VALUE, -1));
            }
        }
        PriorityQueue<DijkstraNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.minWeight));
        offer(dijkstraNodes.get(start), queue);

        while (!queue.isEmpty()) {
            DijkstraNode current = queue.poll();

            if (!current.visited) {
                current.visited = true;

                for (int i = 0; i < adjMatrix.length; i++) {
                    // 인접행렬에 가중치가 존재하고, 현재 노드의 가중치보다 작다면
                    if (adjMatrix[current.idx][i] != 0) {
                        DijkstraNode dstNode = dijkstraNodes.get(i);
                        int weight = current.minWeight + adjMatrix[current.idx][i];

                        if (weight < dstNode.minWeight) {
                            dstNode.minWeight = weight;
                            dstNode.lastVisited = current.idx;
                        }
                        offer(dstNode, queue);
                    }
                }

            }
        }

        return dijkstraNodes.stream()
                .map(node -> node.minWeight)
                .mapToInt(Integer::intValue)
                .toArray();
    }


    void offer(DijkstraNode item, PriorityQueue<DijkstraNode> queue) {
        if (queue.isEmpty()) {
            queue.offer(item);
        } else {
            DijkstraNode current = queue.peek();
            if (item.minWeight < current.minWeight) {
                queue.poll();
                queue.offer(item);
            }
        }
    }

    static class DijkstraNode {
        public int idx;
        public int minWeight;
        public int lastVisited;
        public boolean visited;

        public DijkstraNode(int idx, int minWeight, int lastVisited) {
            this.idx = idx;
            this.minWeight = minWeight;
            this.lastVisited = lastVisited;
            this.visited = false;
        }

        public int getMinWeight() {
            return minWeight;
        }
    }

    /**
     * 게임 맵 최단 거리 (**)
     */
    @Test
    void p04() {
//        int[][] maps = {
//                {1, 0, 1, 1, 1},
//                {1, 0, 1, 0, 1},
//                {1, 0, 1, 1, 1},
//                {1, 1, 1, 0, 1},
//                {0, 0, 0, 0, 1}
//        };
//        int expect = 11;

        int[][] maps = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        };
        int expect = -1;
        int actual = p04Solution(maps);

        assertThat(actual).isEqualTo(expect);
    }

    int p04Solution(int[][] maps) {
        // 방향
        int[] rx = {0, -1, 0, 1};
        int[] ry = {-1, 0, 1, 0};

        int n = maps.length;
        int m = maps[0].length;

        int[][] board = new int[n + 2][m + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i+1][j+1] = maps[i][j];
            }
        }

        int[][] dist = new int[n + 2][m + 2]; // 최단거리를 저장할 배열
        dist[1][1] = 1; // 시작점은 1로 세팅

        Queue<p05Node> queue = new ArrayDeque<>();
        queue.offer(new p05Node(1, 1));


        while (!queue.isEmpty()) {
            p05Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + rx[i];
                int ny = current.y + ry[i];
                if (board[nx][ny] == 1) {
                    queue.offer(new p05Node(nx, ny));
                    dist[nx][ny] = dist[current.x][current.y] + 1;
                    board[current.x][current.y] = 0; // 이미 왔던 곳으로 체크
                }
            }
        }
        // 경로를 찾을 수 없다면 -1 리턴
        return dist[n][m] != 0 ? dist[n][m] : -1;
    }

    static class p05Node {
        public int x;
        public int y;

        public p05Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
