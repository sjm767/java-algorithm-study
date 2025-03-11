package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ch8_hash {

    /**
     * 두 개의 수로 특정값 만들기 (*)
     */
    @Test
    void p01() {
//        int[] arr = {1, 2, 3, 4, 8};
//        int target = 6;
//        boolean expect = true;

        int[] arr = {2, 3, 5, 9};
        int target = 10;
        boolean expect = false;

        boolean actual = p01Solution(arr, target);
        assertThat(actual).isEqualTo(expect);
    }

    boolean p01Solution(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            int find = target - num;

            if (set.contains(find)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }

    /**
     * 완주하지 못한 선수 (*)
     */
    @Test
    void p02() {
//        String[] participant = {"leo", "kiki", "eden"};
//        String[] completion = {"eden", "kiki"};
//        String expect = "leo";

//        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//        String[] completion = {"josipa", "filipa", "marina", "nikola"};
//        String expect = "vinko";

        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        String expect = "mislav";

        String actual = p02Solution(participant, completion);
        assertThat(actual).isEqualTo(expect);
    }

    String p02Solution(String[] participant, String[] completion) {
        Set<String> completionSet = new HashSet<>(Arrays.asList(completion));
        List<String> participantList = Arrays.asList(participant);
        List<String> completionList = Arrays.asList(completion);


        return "";
    }


}
