package com.jaeshim.java.codingtest.study;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /**
     * 네트워크 (**)
     */
    @Test
    void p06() {
//        int n = 3;
//        int[][] computers = {
//                {1, 1, 0},
//                {1, 1, 0},
//                {0, 0, 1}
//        };
//        int expect = 2;

        int n = 3;
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        int expect = 1;

        int actual = p06Solution(n, computers);
        assertThat(actual).isEqualTo(expect);
    }

    int p06Solution(int n, int[][] computers) {
        // 인접 리스트 만들기
        ArrayList<Integer>[] adjList = new ArrayList[n];

        for (int s = 0; s < n; s++) {
            // 인접 리스트 초기화
            if (adjList[s] == null) {
                adjList[s] = new ArrayList<>();
            }
            for (int t = 0; t < n; t++) {
                int connected = computers[s][t];

                // 연결되어 있으면
                if (s != t && connected == 1) {
                    adjList[s].add(t);
                }
            }
        }

        // DFS 처리
        Set<Integer> visit = new HashSet<>(); // 방문기록
        Queue<Integer> queue = IntStream.range(0, n)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int answer = 0;

        while (!queue.isEmpty()) {
            Integer v = queue.poll();

            if (!visit.contains(v)) { // 방문하지 않은 경우만 push
                stack.push(v);
                answer++;
            }
            while (!stack.isEmpty()) {
                Integer currentNode = stack.pop();
                visit.add(currentNode);

                // 인접 리스트보면서 순회
                ArrayList<Integer> adjs = adjList[currentNode];
                adjs.forEach(a -> {
                    if (!visit.contains(a)) {
                        visit.add(a); // 방문한것으로 기록
                        stack.push(a);                        
                    }
                });
            }
        }
        
        return answer;
    }

    /**
     * 미로 탈출 (**)
     */
    @Test
    void p07() {
//        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
//        int expect = 16;

        String[] maps = {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"};
        int expect = -1;

        int actual = p07Solution(maps);
        assertThat(actual).isEqualTo(expect);
    }

    int p07Solution(String[] maps) {
        // 이동할 방향
        final int[] dx = {0, 0, -1, 1};
        final int[] dy = {-1, 1, 0, 0};

        int n = maps.length;
        int m = maps[0].length();
        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = maps[i].toCharArray();
        }

        p07Node start = null, end = null, lever = null;

        // 시작/끝/레버를 찾음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 'S') start = new p07Node(i, j);
                else if(map[i][j] == 'E') end = new p07Node(i, j);
                else if(map[i][j] == 'L') lever = new p07Node(i, j);
            }
        }

        assert start != null;

        int startToLever = p07bfs(start, lever, n, m, map, dx, dy);
        int leverToEnd = p07bfs(lever, end, n, m, map, dx, dy);

        if (startToLever == -1 || leverToEnd == -1) {
            return -1;
        }


        return startToLever + leverToEnd;
    }

    // 너비우선 탐색
    static int p07bfs(p07Node start, p07Node end, int n, int m, char[][] map, int[] dx,int[] dy) {
        int[][] dist =  new int[n][m];
        ArrayDeque<p07Node> queue = new ArrayDeque<>();
        dist[start.nx][start.ny] = 0;
        queue.add(start);
        int answer = -1;

        while (!queue.isEmpty()) {
            p07Node current = queue.poll();

            // 목적지라면
            if (current.nx == end.nx && current.ny == end.ny) {
                answer = dist[current.nx][current.ny];
                break;
            }

            map[current.nx][current.ny] = 'X'; // 방문한 곳 처리

            for (int i = 0; i < 4; i++) {
                int nx = current.nx + dx[i];
                int ny = current.ny + dy[i];

                // 이동 가능하고 이미 방문하지 않았다면
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if(map[nx][ny] != 'X') {
                        queue.add(new p07Node(nx, ny));
                        dist[nx][ny] = dist[current.nx][current.ny] + 1;
                    }
                }
            }

        }

        return answer;
    }

    static class p07Node {
        public int nx;
        public int ny;

        public p07Node(int nx, int ny) {
            this.nx = nx;
            this.ny = ny;
        }
    }

    /**
     * 배달 (***)
     */
    @Test
    void p08() {
//        int N = 5;
//        int[][] road = {
//                {1, 2, 1},
//                {2, 3, 3},
//                {5, 2, 2},
//                {1, 4, 2},
//                {5, 3, 1},
//                {5, 4, 2}
//        };
//        int K = 3;
//        int expect = 4;

        int N = 6;
        int[][] road = {
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 2},
                {3, 4, 3},
                {3, 5, 2},
                {3, 5, 3},
                {5, 6, 1}
        };
        int K = 4;
        int expect = 4;

        int actual = p08Solution(N, road, K);
        assertThat(actual).isEqualTo(expect);
    }

    int p08Solution(int N, int[][] road, int K) {
        // 인접 리스트 생성
        ArrayList<p08Node>[] adjList = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 인접리스트 초기화
        for (int[] r : road) {
            int src = r[0];
            int dst = r[1];
            int weight = r[2];

            adjList[src].add(new p08Node(dst, weight));
            adjList[dst].add(new p08Node(src, weight));
        }

        // 다익스트라 시작
        int[] weight = new int[N + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[1] = 0;

        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            Integer currentIdx = queue.poll();
            ArrayList<p08Node> nodes = adjList[currentIdx];

            for (p08Node node : nodes) {
                int nWeight = weight[currentIdx] + node.weight;

                if (nWeight < weight[node.dst] ) {
                    weight[node.dst] = nWeight;
                    queue.offer(node.dst);
                }
            }
        }

        int answer = 0;
        for (int w : weight) {
            if (w <= K) {
                answer++;
            }
        }

        return answer;
    }

    static class p08Node{
        public int dst;
        public int weight;

        public p08Node(int dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }

    }

    /**
     * 경주로 건설 (*****)
     */
    @Test
    void p09() {

    }

    /**
     * 전력망을 둘로 나누기 (**)
     */
    @Test
    void p10() {
//        int n = 9;
//        int[][] wires = {
//                {1, 3},
//                {2, 3},
//                {3, 4},
//                {4, 5},
//                {4, 6},
//                {4, 7},
//                {7, 8},
//                {7, 9}
//        };
//        int expect = 3;

        int n = 4;
        int[][] wires = {
                {1, 2},
                {2, 3},
                {3, 4}
        };
        int expect = 0;

//        int n = 7;
//        int[][] wires = {
//                {1, 2},
//                {2, 7},
//                {3, 7},
//                {3, 4},
//                {4, 5},
//                {6, 7}
//        };
//        int expect = 1;

        int actual = p10Solution(n, wires);
        assertThat(actual).isEqualTo(expect);
    }

    int p10Solution(int n, int[][] wires) {
        // 인접 리스트 (셋으로) 만들기
        HashSet<Integer>[] adjList = new HashSet[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjList[i] = new HashSet<>();
        }

        for (int[] wire : wires) {
            int src = wire[0];
            int dst = wire[1];

            adjList[src].add(dst);
            adjList[dst].add(src);
        }

        // 전력망 하나씩 끊으면서 둘 간의 차이가 최소일 때 찾기
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int src = wire[0];
            int dst = wire[1];

            adjList[src].remove(dst);
            adjList[dst].remove(src);

            int bfs1 = bfs(adjList, src);
            int bfs2 = bfs(adjList, dst);

            if (Math.abs(bfs1 - bfs2) < answer) {
                answer = Math.abs(bfs1 - bfs2);
            }

            // 원복
            adjList[src].add(dst);
            adjList[dst].add(src);
        }

        return answer;
    }

    /**
     * bfs를 통해 방문한 노드 수를 리턴한다.
     * @return
     */
    int bfs(HashSet<Integer>[] adjList, int start) {
        int n = adjList.length;
        boolean[] visited = new boolean[n]; // 방문 노드 기록
        int count = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            HashSet<Integer> adjs = adjList[current];
            visited[current] = true;
            count++;

            for (Integer a : adjs) {
                if (!visited[a]) {
                    queue.offer(a);
                }
            }
        }

        return count;
    }
}
