package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class ch5_array {

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

    @Test
    void p6() {
//        int N = 5;
//        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
//        int[] expect = {3,4,2,1,5};

        int N = 4;
        int[] stages = {4,4,4,4,4};
        int[] expect = {4,1,2,3};

        int[] result = p6Solution(N, stages);
        assertThat(result).isEqualTo(expect);
    }

    int[] p6Solution(int N, int[] arr) {
        List<FailInfo> list = new ArrayList<>();
        // 1. 배열을 정렬 1,2,2,2,3,3,4,6
        Arrays.sort(arr);

        int idx = 0;
        int total = arr.length; //
        int stage = 1;
        int failCount = 0;
        while(idx < arr.length && stage <= N) {
            while (idx < arr.length && arr[idx] == stage) {
                failCount++;
                idx++;
            }
            list.add(new FailInfo(stage, (double) failCount / total));

            // 스테이지 정보 갱신
            stage++;
            total -= failCount;
            failCount = 0;
        }
        list.sort(new FailComparator());

        return list.stream().mapToInt(i->i.stage).toArray();

    }

    static class FailInfo {
        public int stage;
        public double failRate;

        public FailInfo(int stage, double failRate) {
            this.stage = stage;
            this.failRate = failRate;
        }
    }

    static class FailComparator implements Comparator<FailInfo> {
        @Override
        public int compare(FailInfo o1, FailInfo o2) {
            int failRateCompare = Double.compare(o2.failRate, o1.failRate);
            if (failRateCompare != 0) {
                return failRateCompare;
            }
            return o1.stage - o2.stage;
        }
    }

    @Test
    void p7() {
        char[] dirs = {'U', 'L', 'U', 'R', 'R', 'D', 'L', 'L', 'U'};
        int expect = 7;

        int actual = p7Solution(dirs);
        assertThat(actual).isEqualTo(expect);
    }

    int p7Solution(char[] arr) {
        // 방향 설정
        Map<Character, Integer[]> map = new HashMap<>();
        map.put('U', new Integer[]{0, 1});
        map.put('D', new Integer[]{0, -1});
        map.put('L', new Integer[]{-1, 0});
        map.put('R', new Integer[]{1, 0});

        // 초기 설정
        int x = 5;
        int y = 5;

        // 이동 경로 기록
        Set<String> set = new HashSet<>();

        for (char dir : arr) {
            int nx = x + map.get(dir)[0];
            int ny = y + map.get(dir)[1];

            if (!isValidMove(nx, ny)) {
                continue;
            }

            // 이동 경로 기록
            set.add(x + " " + y + " " + nx + " " + ny);
            set.add(nx + " " + ny + " " + x + " " + y);

            x = nx;
            y = ny;
        }

        return set.size() / 2;
    }

    boolean isValidMove(int nx, int ny) {
        return nx >= 0 && nx <= 10 && ny >= 0 && ny <= 10;
    }
}
