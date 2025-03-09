package com.jaeshim.java.codingtest.study.ch5;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ch5_01_sort {

    @Test
    void sort() {
        int[] arr = {1, -5, 2, 4, 3};
        int[] expect = {-5, 1, 2, 3, 4};

        int[] result = solution(arr);

        assertThat(result).isEqualTo(expect);
    }

    int[] solution(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}
