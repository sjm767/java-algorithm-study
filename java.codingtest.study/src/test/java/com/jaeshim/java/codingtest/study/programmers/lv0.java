package com.jaeshim.java.codingtest.study.programmers;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class lv0 {

    /**
     * 모스부호 (https://school.programmers.co.kr/learn/courses/30/lessons/120838?language=java)
     */
    @Test
    void p01() {
        String letter = ".... . .-.. .-.. ---";
        String expect = "hello";

        String actual = p01Solution(letter);
        assertThat(expect).isEqualTo(actual);
    }

    public String p01Solution(String letter) {
        StringBuilder sb = new StringBuilder();

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Map<String, Character> morseMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            morseMap.put(morse[i], (char) ('a' + i));
        }

        String[] letters = letter.split(" ");
        for (String l : letters) {
            sb.append(morseMap.get(l));
        }

        return sb.toString();
    }

    /**
     * 나머지 구하기 (https://school.programmers.co.kr/learn/courses/30/lessons/120810)
     */
    @Test
    void p02() {
        int num1 = 3;
        int num2 = 2;
        int expect = 1;

        int actual = p02Solution(num1, num2);
        assertThat(expect).isEqualTo(actual);
    }

    public int p02Solution(int num1, int num2) {
        int answer = num1 % num2;

        return answer;
    }

    /**
     * 분수의 덧셈 (https://school.programmers.co.kr/learn/courses/30/lessons/120808)
     */
    @Test
    void p03() {
        int numer1 = 9;
        int denom1 = 2;
        int numer2 = 1;
        int denom2 = 3;
        int[] expect = {29, 6};
        int[] actual = p03Solution(numer1, denom1, numer2, denom2);

        assertThat(expect).isEqualTo(actual);
    }
    public int[] p03Solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];

        // 최소공배수 구하기
        int mother = (denom1 * denom2) / gcd(denom1, denom2);
        int child = (numer1 * (mother / denom1)) + (numer2 * (mother / denom2));

        int gcd = gcd(child, mother);
        answer[0] = child / gcd;
        answer[1] = mother / gcd;

        return answer;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    /**
     * 양꼬치 (https://school.programmers.co.kr/learn/courses/30/lessons/120830)
     */
    @Test
    void p04() {
        int n = 64;
        int k = 6;
        int expect = 768000;
        int actual = p04Solution(n, k);

        assertThat(expect).isEqualTo(actual);
    }
    public int p04Solution(int n, int k) {
        int free = n / 10;
        int sheep = n * 12000;
        int beverage = (k - free) * 2000;


        return sheep + beverage;
    }

    /**
     * 짝수의 합 (https://school.programmers.co.kr/learn/courses/30/lessons/120831)
     */
    @Test
    void p05() {
        int n = 10;
        int expect = 30;
        int actual = p05Solution(n);
        assertThat(actual).isEqualTo(expect);
    }
    public int p05Solution(int n) {
        int sum = 0;
        for (int i = 2; i <= n; i+=2) {
            sum += i;
        }
        return sum;
    }

    /**
     * 배열 원소의 길이 (https://school.programmers.co.kr/learn/courses/30/lessons/120854)
     */
    @Test
    void p06() {
        String[] strlist = {"We", "are", "the", "world!"};
        int[] expect = {2, 3, 3, 6};

        int[] actual = p06Solution(strlist);
        assertThat(actual).isEqualTo(expect);
    }
    public int[] p06Solution(String[] strlist) {
        return Arrays.stream(strlist).mapToInt(String::length).toArray();
    }

    /**
     * 배열의 유사도 (https://school.programmers.co.kr/learn/courses/30/lessons/120903)
     */
    @Test
    void p07() {
        String[] s1 = {"a", "b", "c"};
        String[] s2 = {"com", "b", "d", "p", "c"};

        int expect = 2;
        int actual = p07Solution(s1, s2);
        assertThat(actual).isEqualTo(expect);
    }
    public int p07Solution(String[] s1, String[] s2) {
        int answer = 0;

        Set<String> set = new HashSet<>(Arrays.asList(s1));
        for (String s : s2) {
            if(set.remove(s)) {
                answer++;
            }
        }
        return answer;
    }

    /**
     * 문자열 안에 문자열 (https://school.programmers.co.kr/learn/courses/30/lessons/120908)
     */
    @Test
    void p08() {
        String str1 = "ab6CDE443fgh22iJKlmn1o";
        String str2 = "6CD";

        int expect = 1;
        int actual = p08Solution(str1, str2);
        assertThat(actual).isEqualTo(expect);
    }
    public int p08Solution(String str1, String str2) {
        return str1.contains(str2) ? 1 : 2;
    }

    /**
     * 배열의 평균값 (https://school.programmers.co.kr/learn/courses/30/lessons/120817)
     */
    @Test
    void p09() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double expect = 5.5f;

        double actual = p09Solution(numbers);
        assertThat(actual).isEqualTo(expect);
    }
    public double p09Solution(int[] numbers) {
        return Arrays.stream(numbers).average().orElse(0.0);
    }

    /**
     * 배열 뒤집기 (https://school.programmers.co.kr/learn/courses/30/lessons/120821)
     */
    @Test
    void p10() {
        int[] num_list = {1, 2, 3, 4, 5};
        int[] expect = {5, 4, 3, 2, 1};

        int[] actual = p10Solution(num_list);
        assertThat(actual).isEqualTo(expect);
    }

    public int[] p10Solution(int[] num_list) {
        int[] answer = new int[num_list.length];
        int k = 0;

        for (int i = num_list.length - 1; i >= 0; i--) {
            answer[k++] = num_list[i];
        }
        return answer;
    }

    /**
     * 제곱수 판별하기 (https://school.programmers.co.kr/learn/courses/30/lessons/120909)
     */
    @Test
    void p11() {
        int n = 976;
        int expect = 1;

        int actual = p11Solution(n);
        assertThat(actual).isEqualTo(expect);
    }
    public int p11Solution(int n) {
        double sqrt = Math.sqrt(n);
        return sqrt % 1 == 0 ? 1 : 2;
    }

    /**
     * 짝수 홀수 개수 (https://school.programmers.co.kr/learn/courses/30/lessons/120824)
     */
    @Test
    void p12() {
        int[] num_list = {1, 2, 3, 4, 5};
        int[] expect = {2, 3};

        int[] actual = p12Solution(num_list);
        assertThat(actual).isEqualTo(expect);
    }
    public int[] p12Solution(int[] num_list) {
        int[] answer = new int[2];

        for (int num : num_list) {
            answer[num % 2]++;
        }
        return answer;
    }

    /**
     * 특정 문자 제거하기 (https://school.programmers.co.kr/learn/courses/30/lessons/120826)
     */
    @Test
    void p13() {
        String my_string = "abcdef";
        String letter = "f";
        String expect = "abcde";

        String actual = p13Solution(my_string, letter);
        assertThat(actual).isEqualTo(expect);
    }
    public String p13Solution(String my_string, String letter) {
        return my_string.replaceAll(letter, "");
    }

}
