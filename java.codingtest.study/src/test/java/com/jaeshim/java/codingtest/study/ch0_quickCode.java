package com.jaeshim.java.codingtest.study;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 생각나는 것 빠르게 짜보기 위해 사용
 */
public class ch0_quickCode {

    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {3, 6},
                {5, 6}
        };
        int start = 1;
        int n = 6;

        boolean[] visited = new boolean[n + 1];
        ArrayList<Integer>[] adjLists = new ArrayList[n + 1];
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < adjLists.length; i++) {
            adjLists[i] = new ArrayList<>();
        }

        // 인접리스트 생성
        for (int[] g : graph) {
            int s = g[0];
            int e = g[1];

            adjLists[s].add(e);
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            visited[node] = true;
            answer.add(node);

            for (Integer adj : adjLists[node]) {

                if(!visited[adj]) {
                    stack.push(adj);
                }
            }
        }

        for (Integer a : answer) {
            System.out.println(a+" ");
        }


    }


}
