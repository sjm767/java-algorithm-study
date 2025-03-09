package com.jaeshim.java.codingtest.study.ch5;

import static org.assertj.core.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.util.PropertySource;
import org.junit.jupiter.api.Test;

class ch5_01_sort {

    @Test
    void p1() {
        int[] arr = {1, -5, 2, 4, 3};
        int[] expect = {-5, 1, 2, 3, 4};

        int[] result = p1Solution(arr);

        assertThat(result).isEqualTo(expect);
    }

    int[] p1Solution(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    @Test
    void p2() {
//        int[] arr = {4, 2, 2, 1, 3, 4};
//        int[] expect = {4, 3, 2, 1};

        int[] arr = {2, 1, 1, 3, 2, 5, 4};
        int[] expect = {5, 4, 3, 2, 1};

        int[] actual = p2Solution(arr);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p2Solution(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue).toArray();
    }

    @Test
    void p3() {
        int[] numbers = {2, 1, 3, 4, 1};
        int[] expect = {2, 3, 4, 5, 6, 7};

        int[] actual = p3Solution(numbers);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p3Solution(int[] arr) {
        Set<Integer> set = IntStream.range(0, arr.length)
                .boxed()
                .flatMap(num -> IntStream.range(num + 1, arr.length).mapToObj(j -> arr[num] + arr[j]))
                .collect(Collectors.toSet());

        return set.stream().sorted().toList().stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void p4() {
        int[] numbers = {1, 3, 2, 4, 2};
        int[] expect = {1, 2, 3};

//        int[] numbers = {1, 2, 3, 4, 5};
//        int[] expect = {1};

        int[] actual = p4Solution(numbers);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p4Solution(int[] arr) {
        // 문제 1, 3, 2, 4, 2

        //1번 수포자 1,2,3,4,5
        //2번 수포자 2,1,2,3,2,4,2,5
        //3번 수포자 3,3,1,1,2,2,4,4,5,5
        int[][] p = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] scores = new int[3]; // 수포자별 맞힌 개수

        // 수포자별 순회
        for (int i = 0; i < p.length; i++) {
            // 문제를 맞춘 수 계산
            int idx = 0;
            for (int j = 0; j < arr.length; j++) {
                idx = j % p[i].length;
                // 두 문제가 같으면 점수 추가
                if(p[i][idx] == arr[j]) {
                    scores[i]++;
                }
            }
        }
        
        int maxScore = Arrays.stream(scores).max().getAsInt(); // 최대로 맞힌 개수

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void p5() {
        int[][] arr1 = {
                {1,4},
                {3,2},
                {4,1}
        };
        int[][] arr2 = {
                {3,3},
                {3,3}
        };
        int[][] expect = {
                {15,15},
                {15,15},
                {15,15}
        };

//        int[][] arr1 = {
//                {2, 3, 2},
//                {4, 2, 4},
//                {3, 1, 4}
//        };
//        int[][] arr2 = {
//                {5, 4, 3},
//                {2, 4, 1},
//                {3, 1, 1}
//        };
//        int[][] expect = {
//                {22, 22, 11},
//                {36, 28, 18},
//                {29, 20, 14}
//        };

        int[][] actual = p5Solution(arr1, arr2);
        assertThat(actual).isEqualTo(expect);
    }

    int[][] p5Solution(int[][] arr1, int[][] arr2) {
        int[][] result = new int[Math.max(arr1.length, arr2.length)][arr1[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                int sum = 0;
                for (int k = 0; k < arr2[j].length; k++) {
                    int x = arr1[i][k]; //2,3,2 -> 2,3,2 -> 2,3,2
                    int y = arr2[k][j]; //5,2,3 ->
                    sum += x * y;
                }

                result[i][j] = sum;
            }
        }

        return result;
    }



}
