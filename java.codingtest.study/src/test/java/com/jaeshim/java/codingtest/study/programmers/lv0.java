package com.jaeshim.java.codingtest.study.programmers;

import org.junit.jupiter.api.Test;

import java.math.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

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

    /**
     * 배열 만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/181921)
     */
    @Test
    void p14() {
        int l = 5;
        int r = 555;
        int[] expect = {5,50,55,500,505,550,555};

//        int l = 10;
//        int r = 20;
//        int[] expect = {-1};

        int[] actual = p14Solution(l, r);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p14Solution(int l, int r) {
        List<Integer> answer = new ArrayList<>();

        // 가장 가까운 5의 배수 찾기
        int start = l % 5 == 0 ? l : 5 * (l / 5 + 1);

        while(start <= r) {
            if(p05isOnlyFiveOrZero(start)) {
                answer.add(start);
            }
            start+=5;
        }

        if(answer.isEmpty()) {
            return new int[]{-1};
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    boolean p05isOnlyFiveOrZero(int number) {
        while(number > 0) {
            int digit = number % 10;
            if (digit != 0 && digit != 5) {
                return false;
            }
            number /= 10;
        }
        return true;
    }

    /**
     * 카운트 업 (https://school.programmers.co.kr/learn/courses/30/lessons/181920)
     */
    @Test
    void p16() {
        int start_num = 3;
        int end_num = 10;
        int[] expect = {3, 4, 5, 6, 7, 8, 9, 10};

        int[] actual = p16Solution(start_num, end_num, expect);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p16Solution(int start_num, int end_num, int[] expect) {
        int[] answer = new int[end_num - start_num + 1];
        int k =0;
        while(start_num <= end_num) {
            answer[k++] = start_num;
            start_num++;
        }

        return answer;
    }

    /**
     * 콜라츠 수열 만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/181919)
     */
    @Test
    void p17() {
        int n = 10;
        int[] expect = {10, 5, 16, 8, 4, 2, 1};

        int[] actual = p17Solution(n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p17Solution(int n) {
        List<Integer> answer = new ArrayList<>();

        p17Colaz(n, answer);

        return answer.stream().mapToInt(i -> i).toArray();
    }

    void p17Colaz(int num, List<Integer> answer) {
        if (num == 1) {
            answer.add(num);
            return;
        }
        answer.add(num);

        if (num % 2 == 0) {
            num /= 2;
        } else {
            num = 3 * num + 1;
        }
        p17Colaz(num, answer);

    }

    /**
     * 배열 만들기 4 (https://school.programmers.co.kr/learn/courses/30/lessons/181918)
     */
    @Test
    void p18() {
        int[] arr = {1, 4, 2, 5, 3};
        int[] expect = {1, 2, 3};

        int[] actual = p18Solution(arr);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p18Solution(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (answer.isEmpty()) {
                answer.add(arr[i]);
            } else if(answer.get(answer.size() - 1) < arr[i]) {
                answer.add(arr[i]);
            } else {
                answer.remove(answer.size() - 1);
                i--;
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 간단한 논리 연산 (https://school.programmers.co.kr/learn/courses/30/lessons/181917)
     */
    @Test
    void p19() {
        boolean x1 = false;
        boolean x2 = true;
        boolean x3 = true;
        boolean x4 = true;
        boolean expect = true;

        boolean actual = p19Solution(x1, x2, x3, x4);
        assertThat(expect).isEqualTo(actual);
    }

    boolean p19Solution(boolean x1, boolean x2, boolean x3, boolean x4) {
        return (x1 | x2) & (x3 | x4);
    }

    /**
     * 주사위 게임 3 (https://school.programmers.co.kr/learn/courses/30/lessons/181916)
     */
    @Test
    void p20() {
        int a = 6;
        int b = 4;
        int c = 2;
        int d = 5;
        int expect = 2;

        int actual = p20Solution(a, b, c, d);
        assertThat(actual).isEqualTo(expect);
    }

    int p20Solution(int a, int b, int c, int d) {
        int[] nums = new int[7];
        nums[a]++;
        nums[b]++;
        nums[c]++;
        nums[d]++;

        int[][] array = new int[7][2];
        for (int i = 1; i < nums.length; i++) {
            array[i][0] = nums[i];
            array[i][1] = i;
        }

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o2[0] == o1[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o2[0], o1[0]);
            }
        });

        List<int[]> filtered = new ArrayList<>();
        for (int[] arr : array) {
            if(arr[0] == 0) {
                break;
            }
            if (arr[0] > 0) {
                filtered.add(arr);
            }
        }

        if(filtered.get(0)[0] == 4) {
            return 1111 * filtered.get(0)[1];
        } else if(filtered.get(0)[0] == 3) {
            return (int) Math.pow((10 * filtered.get(0)[1] + filtered.get(1)[1]), 2);
        } else if (filtered.get(0)[0] == 2) {
            if(filtered.size() == 3) {
                return filtered.get(1)[1] * filtered.get(2)[1];
            } else {
                int p = filtered.get(0)[1];
                int q = filtered.get(1)[1];

                return (p + q) * Math.abs(p - q);
            }
        } else {
            return filtered.get(0)[1];
        }

    }

    /**
     * 글자 이어 붙여 문자열 만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/181915)
     */
    @Test
    void p21() {
        String my_string = "cvsgiorszzzmrpaqpe";
        int[] index_list = {16, 6, 5, 3, 12, 14, 11, 11, 17, 12, 7};

        String expect = "programmers";
        String actual = p21Solution(my_string, index_list);
        assertThat(actual).isEqualTo(expect);
    }

    String p21Solution(String my_string, int[] index_list) {
        StringBuilder sb = new StringBuilder();

        for (int idx : index_list) {
            sb.append(my_string.charAt(idx));
        }

        return sb.toString();
    }

    /**
     * 9로 나눈 나머지 (https://school.programmers.co.kr/learn/courses/30/lessons/181914)
     */
    @Test
    void p22() {
        String number = "123";
        int expect = 6;

        int actual = p22Solution(number);
        assertThat(actual).isEqualTo(expect);
    }

    int p22Solution(String number) {
        int sum = 0;
        for (char ch : number.toCharArray()) {
            sum += ch - '0';
        }

        return sum % 9;
    }

    /**
     * 문자열 여러 번 뒤집기 (https://school.programmers.co.kr/learn/courses/30/lessons/181913)
     */
    @Test
    void p23() {
        String my_string = "rermgorpsam";
        int[][] queries = {
                {2, 3},
                {0, 7},
                {5, 9},
                {6, 10}
        };
        String expect = "programmers";
        String actual = p23Solution(my_string, queries);
        assertThat(actual).isEqualTo(expect);
    }

    String p23Solution(String my_string, int[][] queries) {
        StringBuilder sb = new StringBuilder();
        StringBuilder current = new StringBuilder(my_string);

        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];

            sb = new StringBuilder(current.substring(s, e + 1)).reverse();
            current.replace(s, e+1, sb.toString());


        }
        return current.toString();
    }

    /**
     * 배열 만들기 5 (https://school.programmers.co.kr/learn/courses/30/lessons/181912)
     */
    @Test
    void p24() {
        String[] intStrs = {"0123456789","9876543210","9999999999999"};
        int k = 50000;
        int s = 5;
        int l = 5;

        int[] expect = {56789, 99999};
        int[] actual = p24Solution(intStrs, k, s, l);

        assertThat(actual).isEqualTo(expect);
    }

    int[] p24Solution(String[] intStrs, int k, int s, int l) {
        List<Integer> answer = new ArrayList<>();

        for (String str : intStrs) {
            String substr = str.substring(s, s + l);
            int num = Integer.parseInt(substr);
            if (num > k) {
                answer.add(num);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 부분 문자열 이어 붙여 문자열 만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/181911)
     */
    @Test
    void p25() {
        String[] my_strings = {"progressive", "hamburger", "hammer", "ahocorasick"};
        int[][] parts = {
                {0, 4},
                {1, 2},
                {3, 5},
                {7, 7}
        };
        String expect = "programmers";
        String actual = p25Solution(my_strings, parts);
        assertThat(actual).isEqualTo(expect);
    }

    String p25Solution(String[] my_strings, int[][] parts) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < my_strings.length; i++) {

            String substr = my_strings[i].substring(parts[i][0], parts[i][1] + 1);
            sb.append(substr);
        }

        return sb.toString();
    }

    @Test
    void p26() {
        String my_string = "ProgrammerS123";
        int n = 11;

        String expect = "grammerS123";
        String actual = p26Solution(my_string, n);

        assertThat(actual).isEqualTo(expect);
    }

    String p26Solution(String my_string, int n) {
        return my_string.substring(my_string.length() - n );
    }

    /**
     * 접미사 배열 (https://school.programmers.co.kr/learn/courses/30/lessons/181909)
     */
    @Test
    void p27() {
        String my_string = "banana";
        String[] expect = {"a", "ana", "anana", "banana", "na", "nana"};

        String[] actual = p27Solution(my_string);
        assertThat(actual).isEqualTo(expect);

    }

    String[] p27Solution(String my_string) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < my_string.length(); i++) {
            answer.add(my_string.substring(i));
        }

        Collections.sort(answer);
        return answer.toArray(String[]::new);
    }

    /**
     * 접미사인지 확인하기
     */
    @Test
    void p28() {
        String my_string = "banana";
        String is_suffix = "ana";

        int expect = 1;
        int actual = p28Solution(my_string, is_suffix);
        assertThat(actual).isEqualTo(expect);
    }

    int p28Solution(String my_string, String is_suffix) {
        return my_string.endsWith(is_suffix) ? 1 : 0;

    }

    /**
     * 문자열 뒤집기
     */
    @Test
    void p29() {
        String my_string = "Progra21Sremm3";
        int s = 6;
        int e = 12;
        String expect = "ProgrammerS123";

        String actual = p29Solution(my_string, s, e);
        assertThat(actual).isEqualTo(expect);
    }

    String p29Solution(String my_string, int s, int e) {
        StringBuilder reverse = new StringBuilder(my_string.substring(s, e + 1)).reverse();

        StringBuilder answer = new StringBuilder(my_string);
        answer.replace(s, e + 1, reverse.toString());

        return answer.toString();
    }

    /**
     * 세로 읽기 (https://school.programmers.co.kr/learn/courses/30/lessons/181904)
     */
    @Test
    void p30() {
        String my_string = "programmers";
        int m = 1;
        int c = 1;

        String expect = "programmers";
        String actual = p30Solution(my_string, m, c);
        assertThat(actual).isEqualTo(expect);
    }

    String p30Solution(String my_string, int m, int c) {
        String[] str = new String[my_string.length() / m];
        StringBuilder sb = new StringBuilder();

        int s = 0;
        int e = m;
        int k = 0;
        while (s < my_string.length()) {
            str[k] = my_string.substring(s, e);
            sb.append(str[k].charAt(c-1));
            s+=m;
            e+=m;
            k++;
        }
        return sb.toString();

    }

    /**
     * qr code (https://school.programmers.co.kr/learn/courses/30/lessons/181903)
     */
    @Test
    void p31() {
        int q = 3;
        int r = 1;
        String code = "qjnwezgrpirldywt";
        String expect = "jerry";

        String actual = p31Solution(q, r, code);
        assertThat(actual).isEqualTo(expect);
    }

    String p31Solution(int q, int r, String code) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (i % q == r) {
                sb.append(code.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * 문자 개수 세기 (https://school.programmers.co.kr/learn/courses/30/lessons/181902)
     */
    @Test
    void p32() {
        String my_string = "Programmers";
        int[] expect = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 3, 1, 0, 0, 0, 0, 0, 0, 0};

        int[] actual = p32Solution(my_string);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p32Solution(String my_string) {
        int[] answer = new int[52];

        for (char ch : my_string.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                answer[ch - 'A']++;
            } else {
                answer[ch-'a' + 26]++;
            }

        }

        return answer;
    }

    /**
     * 배열 만들기1 (https://school.programmers.co.kr/learn/courses/30/lessons/181901)
     */
    @Test
    void p33() {
        int n = 15;
        int k = 5;
        int[] expect = {5, 10, 15};

        int[] actual = p33Solution(n, k);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p33Solution(int n, int k) {
        List<Integer> answer = new ArrayList<>();
        // 가장 가까운 수 찾기
        int current = k;
        while (current <= n) {
            answer.add(current);
            current+=k;
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    @Test
    void p34() {
        String my_string = "apporoograpemmemprs";
        int[] indices = {1, 16, 6, 15, 0, 10, 11, 3};

        String expect = "programmers";
        String actual = p34Solution(my_string, indices);
        assertThat(actual).isEqualTo(expect);
    }

    private String p34Solution(String my_string, int[] indices) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(indices);

        int k = 0;
        for (int i = 0; i < my_string.length(); i++) {
            if (k< indices.length && i == indices[k]){
                k++;

            } else {
                sb.append(my_string.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 가까운 1찾기 (https://school.programmers.co.kr/learn/courses/30/lessons/181898)
     */
    @Test
    void p35() {
        int[] arr = {1,1,1,1,0};
        int idx = 3;
        int expect = 3;

        int actual = p35Solution(arr, idx);
        assertThat(actual).isEqualTo(expect);
    }

    int p35Solution(int[] arr, int idx) {
        int answer = idx;
        for (int start = idx; start < arr.length; start++) {
            if(arr[start] == 1) {
                return answer;
            }
            answer++;
        }

        return -1;
    }

    /**
     * 리스트 자르기 (https://school.programmers.co.kr/learn/courses/30/lessons/181897)
     */
    @Test
    void p36() {
//        int n = 3;
//        int[] slicer = {1, 5, 2};
//        int[] num_list = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] expect = {2, 3, 4, 5, 6};

        int n = 4;
        int[] slicer = {1, 5, 2};
        int[] num_list = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expect = {2, 4, 6};

        int[] actual = p36Solution(n, slicer, num_list);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p36Solution(int n, int[] slicer, int[] num_list) {
        int[] answer = null;
        int a = slicer[0];
        int b = slicer[1];
        int c = slicer[2];

        if (n == 1) {
            answer = Arrays.copyOfRange(num_list, 0, b + 1);
        } else if (n == 2) {
            answer = Arrays.copyOfRange(num_list, a, num_list.length);
        } else if (n == 3) {
            answer = Arrays.copyOfRange(num_list, a, b +1);
        } else {
            List<Integer> list = new ArrayList<>();
            for (int i = a; i <= b; i += c) {
                list.add(num_list[i]);
            }
            answer = list.stream().mapToInt(i -> i).toArray();
        }

        return answer;
    }

    /**
     * 배열 만들기 3 (https://school.programmers.co.kr/learn/courses/30/lessons/181895)
     */
    @Test
    void p37(){
        int[] arr = {1, 2, 3, 4, 5};
        int[][] intervals = {
                {1, 3},
                {0, 4}
        };
        int[] expect = {2, 3, 4, 1, 2, 3, 4, 5};
        int[] actual = p37Solution(arr, intervals);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p37Solution(int[] arr, int[][] intervals) {
        List<Integer> answer = new ArrayList<>();

        for (int[] interval : intervals) {
            int s = interval[0];
            int e = interval[1];

            answer.addAll(
                    Arrays.stream(Arrays.copyOfRange(arr, s, e + 1))
                            .boxed()
                            .collect(Collectors.toList())  // ✅ Java 8 호환
            );
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 2의 영역 (https://school.programmers.co.kr/learn/courses/30/lessons/181894)
     */
    @Test
    void p38() {
        int[] arr = {1, 2, 1, 4, 5, 2, 9};
        int[] expect = {2,1,4,5,2};

        int[] actual = p38Solution(arr);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p38Solution(int[] arr) {
        int s = -1;
        int e = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                if (s == -1) {
                    s = i;
                } else {
                    e = i;
                }
            }
        }

        if (e == -1) {
            e = s;
        }

        if (s == -1) {
            return new int[]{-1};
        }

        return Arrays.copyOfRange(arr, s, e + 1);
    }

    /**
     * n 번째 원소부터 (https://school.programmers.co.kr/learn/courses/30/lessons/181892)
     */
    @Test
    void p39(){
        int[] num_list = {2, 1, 6};
        int n = 3;

        int[] expect = {6};
        int[] actual = p39Solution(num_list, n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p39Solution(int[] num_list, int n) {
        return Arrays.copyOfRange(num_list, n - 1, num_list.length);

    }

    /**
     * 배열 조각하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181893)
     */
    @Test
    void p40() {
        int[] arr = {0, 1, 2, 3, 4, 5};
        int[] query = {4, 1, 2};
        int[] expect = {1, 2, 3};

        int[] actual = p40Solution(arr, query);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p40Solution(int[] arr, int[] query) {
        int[] answer = arr;
        for (int i = 0; i < query.length; i++) {
            int q = query[i];

            if (i % 2 == 0) {
                answer = Arrays.copyOfRange(answer, 0, q + 1);
            } else {
                answer = Arrays.copyOfRange(answer, q, answer.length);
            }
        }
        return answer;
    }

    @Test
    void p41() {
        int[] num_list = {5, 2, 1, 7, 5};
        int n = 3;

        int[] expect = {7, 5, 5, 2, 1};
        int[] actual = p41Solution(num_list, n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p41Solution(int[] num_list, int n) {
        List<Integer> answer = new ArrayList<>();

        int[] front = Arrays.copyOfRange(num_list, 0, n);
        int[] tail = Arrays.copyOfRange(num_list, n, num_list.length);

        answer.addAll(Arrays.stream(tail).boxed().collect(Collectors.toList()));
        answer.addAll(Arrays.stream(front).boxed().collect(Collectors.toList()));

        return answer.stream().mapToInt(i -> i).toArray();
    }

    @Test
    void p42() {
        String[] str_list = {"u", "u", "l", "r"};
        String[] expect = {"u", "u"};

        String[] actual = p42Solution(str_list);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p42Solution(String[] str_list) {
        String find = null;

        int idx = 0;
        for (int i = 0; i < str_list.length; i++) {

            if (str_list[i].equals("l") || str_list[i].equals("r")) {
                find = str_list[i];
                idx = i;
                break;
            }
        }

        if (find != null && find.equals("l")) {
            return Arrays.copyOfRange(str_list, 0, idx);
        } else {
            return Arrays.copyOfRange(str_list, idx + 1, str_list.length);
        }

    }

    /**
     * n 번쨰 원소까지 (https://school.programmers.co.kr/learn/courses/30/lessons/181889)
     */
    @Test
    void p43() {
        int[] num_list = {2, 1, 6};
        int n = 1;

        int[] expect = {2};
        int[] actual = p43Solution(num_list, n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p43Solution(int[] num_list, int n) {

        return Arrays.copyOfRange(num_list, 0, n );
    }

    /**
     * n개 간격의 원소들 (https://school.programmers.co.kr/learn/courses/30/lessons/181888)
     */
    @Test
    void p44() {
        int[] num_list = {4, 2, 6, 1, 7, 6};
        int n = 2;
        int[] expect = {4, 6, 7};

        int[] actual = p44Solution(num_list, n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p44Solution(int[] num_list, int n) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < num_list.length; i += n) {
            answer.add(num_list[i]);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 홀수 vs 짝수 (https://school.programmers.co.kr/learn/courses/30/lessons/181887)
     */
    @Test
    void p45() {
        int[] num_list = {4, 2, 6, 1, 7, 6};
        int expect = 17;

        int actual = p45Solution(num_list);
        assertThat(actual).isEqualTo(expect);
    }

    int p45Solution(int[] num_list) {

        int jjakSum = 0;
        int holSum = 0;

        for (int i = 0; i < num_list.length; i++) {
            if(i % 2 == 0) {
                jjakSum += num_list[i];
            } else {
                holSum += num_list[i];
            }

        }

        return Math.max(jjakSum, holSum);
    }

    /**
     * 5명씩 (https://school.programmers.co.kr/learn/courses/30/lessons/181886)
     */
    @Test
    void p46() {
        String[] names = {"nami", "ahri", "jayce", "garen", "ivern", "vex", "jinx"};
        String[] expect = {"nami", "vex"};

        String[] actual = p46Solution(names);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p46Solution(String[] names) {
        List<String> answer = new ArrayList<>();
        final int divide = 5;

        for (int i = 0; i < names.length; i++) {
            if (i % divide == 0) {
                answer.add(names[i]);
            }
        }

        return answer.toArray(new String[0]);

    }

    /**
     * 할 일 목록 (https://school.programmers.co.kr/learn/courses/30/lessons/181885)
     */
    @Test
    void p47() {
        String[] todo_list = {"problemsolving", "practiceguitar", "swim", "studygraph"};
        boolean[] finished = {true, false, true, false};
        String[] expect = {"practiceguitar", "studygraph"};

        String[] actual = p47Solution(todo_list, finished);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p47Solution(String[] todo_list, boolean[] finished) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < todo_list.length; i++) {
            if (!finished[i]) answer.add(todo_list[i]);
        }

        return answer.toArray(new String[0]);
    }

    /**
     * 수열과 구간 쿼리1 (https://school.programmers.co.kr/learn/courses/30/lessons/181883)
     */
    @Test
    void p48() {
        int[] arr = {0, 1, 2, 3, 4};
        int[][] queries = {
                {0, 1},
                {1, 2},
                {2, 3}
        };
        int[] expect = {1, 3, 4, 4, 4};
        int[] actual = p48Solution(arr, queries);

        assertThat(actual).isEqualTo(expect);
    }

    int[] p48Solution(int[] arr, int[][] queries) {

        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];

            for (int i = s; i <= e; i++) {
                arr[i]++;
            }
        }

        return arr;
    }

    /**
     * 조건에 맞게 수열 변환하기 2 (https://school.programmers.co.kr/learn/courses/30/lessons/181881)
     */
    @Test
    void p49() {
        int[] arr = {1, 2, 3, 100, 99, 98};
        int expect = 5;

        int actual = p49Solution(arr);
        assertThat(actual).isEqualTo(expect);

    }

    int p49Solution(int[] arr) {
        int answer = 0;
        boolean isChanged = false;

        while (true) {

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 50 && arr[i] % 2 == 0) {
                    arr[i] /= 2;
                    isChanged = true;
                } else if (arr[i] < 50 && arr[i] % 2 != 0) {
                    arr[i] = (arr[i] * 2) + 1;
                    isChanged = true;
                }
            }

            if(!isChanged) {
                break;
            }
            answer++;
            isChanged = false;
        }
        return answer;
    }

    /**
     * 1로 만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/181880)
     */
    @Test
    void p50() {
        int[] num_list = {12, 4, 15, 1, 14};
        int expect = 11;

        int actual = p50Solution(num_list);
        assertThat(actual).isEqualTo(expect);
    }

    int p50Solution(int[] num_list) {
        int answer = 0;

        for (int j : num_list) {
            int num = j;

            while (num != 1) {
                if (num % 2 == 0) {
                    num /= 2;
                } else {
                    num = (num - 1) / 2;
                }
                answer++;
            }
        }

        return answer;
    }

    @Test
    void p51(){
        int[] num_list = {3, 4, 5, 2, 5, 4, 6, 7, 3, 7, 2, 2, 1};
        int expect = 51;

        int actual = p51Solution(num_list);
        assertThat(actual).isEqualTo(expect);
    }

    int p51Solution(int[] num_list) {

        if (num_list.length >= 11) {
            return Arrays.stream(num_list).sum();
        }
        return Arrays.stream(num_list).reduce(1, (a, b) -> a * b);

    }

    /**
     * 배열에서 문자열 대소문자 변환하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181875)
     */
    @Test
    void p52() {
        String[] strArr = {"AAA", "BBB", "CCC", "DDD"};
        String[] expect = {"aaa","BBB","ccc","DDD"};

        String[] actual = p52Solution(strArr);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p52Solution(String[] strArr) {
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < strArr.length; i++) {
            if (i % 2 == 0) {
                answer.add(strArr[i].toLowerCase());
            } else {
                answer.add(strArr[i].toUpperCase());
            }
        }
        return answer.toArray(new String[0]);
    }

    /**
     * A 강조하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181874)
     */
    @Test
    void p53() {
        String myString = "abstract algebra";
        String expect = "AbstrAct AlgebrA";

        String actual = p53Solution(myString);
        assertThat(actual).isEqualTo(expect);

    }

    String p53Solution(String myString) {
        StringBuilder sb = new StringBuilder();

        for (char ch : myString.toCharArray()) {

            if(ch == 'a' || ch =='A') {
                sb.append(Character.toUpperCase(ch));
            } else {
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString();
    }

    /**
     * 특정 문자열로 끝나는 가장 긴 부분 문자열 찾기 (https://school.programmers.co.kr/learn/courses/30/lessons/181872)
     */
    @Test
    void p54() {
        String myString = "AbCdEFG";
        String pat = "dE";
        String expect = "AbCdE";

        String actual = p54Solution(myString, pat);
        assertThat(actual).isEqualTo(expect);
    }

    String p54Solution(String myString, String pat) {
        return myString.substring(0, myString.lastIndexOf(pat)) + pat;
    }

    /**
     *
     */
    @Test
    void p55() {
        String myString = "banana";
        String pat = "ana";
        int expect = 2;

        int actual = p55Solution(myString, pat);
        assertThat(actual).isEqualTo(expect);
    }

    int p55Solution(String myString, String pat) {
        int answer = 0;
        int start = 0;
        int end = pat.length();

        while (end <= myString.length()) {
            if ((myString.substring(start, end).equals(pat))) {
                answer++;
            }

            start++;
            end++;
        }

        return answer;
    }

    /**
     * 공백으로 구분하기 2 (https://school.programmers.co.kr/learn/courses/30/lessons/181868)
     */
    @Test
    void p56() {
        String my_string = "i       love you";
        String[] expect = {"i", "love", "you"};

        String[] actual = p56Solution(my_string);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p56Solution(String my_string) {
        String[] s = my_string.split(" ");
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].trim();
        }

        return Arrays.stream(s).filter(ss -> !ss.isEmpty()).toArray(String[]::new);

    }

    /**
     * x 사이의 개수 (https://school.programmers.co.kr/learn/courses/30/lessons/181867)
     */
    @Test
    void p57() {
        String myString = "xabcxdefxghi";
        int[] expect = {0,3,3,3};

        int[] actual = p57Solution(myString);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p57Solution(String myString) {
        List<Integer> answer = new ArrayList<>();

        int count = 0;
        for (char ch : myString.toCharArray()) {
            if (ch == 'x') {
                answer.add(count);
                count = 0;
            } else {
                count++;
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(i->i).toArray();
    }

    /**
     * 문자열 잘라서 정렬하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181866)
     */
    @Test
    void p58(){
        String myString = "dxccxbbbxaaaa";
        String[] expect = {"aaaa","bbb","cc","d"};

        String[] actual = p58Solution(myString);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p58Solution(String myString) {
        String[] split = myString.split("x");

        String[] filtered = Arrays.stream(split).filter(s -> !s.isEmpty()).toArray(String[]::new);
        Arrays.sort(filtered);
        return filtered;
    }

    /**
     * 간단한 식 계산하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181865)
     */
    @Test
    void p59() {
        String binomial = "43 + 12";
        int expect = 55;

        int actual = p59Solution(binomial);
        assertThat(actual).isEqualTo(expect);
    }

    int p59Solution(String binomial) {
        String[] split = binomial.split(" ");
        int a = Integer.parseInt(split[0]);
        char bi = split[1].charAt(0);
        int b = Integer.parseInt(split[2]);

        if (bi == '+') {
            return a + b;
        } else if (bi == '-') {
            return a - b;
        } else if (bi == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }

    /**
     * 문자열 바꿔서 찾기 (https://school.programmers.co.kr/learn/courses/30/lessons/181864)
     */
    @Test
    void p60() {
        String myString = "ABBAA";
        String pat = "AABB";
        int expect = 1;

        int actual = p60Solution(myString, pat);
        assertThat(actual).isEqualTo(expect);
    }

    int p60Solution(String myString, String pat) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == 'A') {
                sb.append("B");
            } else {
                sb.append("A");
            }
        }

        return sb.toString().contains(pat) ? 1 : 0;
    }

    /**
     * 세 개의 구분자 (https://school.programmers.co.kr/learn/courses/30/lessons/181862)
     */
    @Test
    void p61() {
        String myStr = "baconlettucetomato";
        String[] expect = {"onlettu", "etom", "to"};

        String[] actual = p61Solution(myStr);
        assertThat(actual).isEqualTo(expect);
    }

    String[] p61Solution(String myStr) {
        String[] split = myStr.split("[abc]");

        String[] answer = Arrays.stream(split).filter(s -> !s.isEmpty()).toArray(String[]::new);
        return answer.length == 0 ? new String[]{"EMPTY"} : answer;
    }

    /**
     * 배열의 원소만큼 추가하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181861)
     */
    @Test
    void p62() {
        int[] arr = {5, 1, 4};
        int[] expect = {5, 5, 5, 5, 5, 1, 4, 4, 4, 4};

        int[] actual = p62Solution(arr);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p62Solution(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        for (int a : arr) {

            for (int i = 0; i < a; i++) {
                answer.add(a);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 빈 배열에 추가, 삭제하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181860)
     */
    @Test
    void p63() {
        int[] arr = {3, 2, 4, 1, 3};
        boolean[] flag = {true, false, true, false, false};
        int[] expect = {3, 3, 3, 3, 4, 4, 4, 4};

        int[] actual = p63Solution(arr, flag);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p63Solution(int[] arr, boolean[] flag) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (flag[i]) {
                for (int j = 0; j < arr[i] * 2; j++) {
                    answer.add(arr[i]);
                }
            } else {
                for (int j = 0; j < arr[i]; j++) {
                    answer.remove(answer.size() - 1);
                }
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 배열 만들기 6 (https://school.programmers.co.kr/learn/courses/30/lessons/181859)
     */
    @Test
    void p64() {
        int[] arr = {0, 1, 1, 1, 0};
        int[] expect = {0, 1, 0};

        int[] actual = p64Solution(arr);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p64Solution(int[] arr) {
        List<Integer> stk = new ArrayList<>();
        for (int j : arr) {
            if (stk.isEmpty()) {
                stk.add(j);
            } else {
                if (stk.get(stk.size() - 1) == j) {
                    stk.remove(stk.size() - 1);
                } else {
                    stk.add(j);
                }
            }
        }

        if (stk.isEmpty()) {
            return new int[]{-1};
        }

        return stk.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 무작위로 K개의 수 뽑기 (https://school.programmers.co.kr/learn/courses/30/lessons/181858)
     */
    @Test
    void p65(){
        int[] arr = {0, 1, 1, 2, 2, 3};
        int k = 3;
        int[] expect = {0, 1, 2};

        int[] actual = p65Solution(arr, k);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p65Solution(int[] arr, int k) {
        int[] answer = new int[k];
        Arrays.fill(answer, -1);

        Set<Integer> set = new HashSet<>();

        int idx = 0;
        for (int a : arr) {
            if (!set.contains(a)) {
                answer[idx++] = a;
            }
            set.add(a);

            if (idx == k) {
                break;
            }
        }

        return answer;
    }

    /**
     * 배열의 길이를 2의 거듭제곱으로 만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/181857)
     */
    @Test
    void p66() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expect = {1, 2, 3, 4, 5, 6, 0, 0};

        int[] actual = p66Solution(arr);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p66Solution(int[] arr) {
        int len = arr.length;
        int pow = 1;

        // 2의 거듭제곱 중 arr.length 이상인 최소값 찾기
        while (pow < len) {
            pow *= 2;
        }

        // 정답 배열 만들기
        int[] result = new int[pow];
        System.arraycopy(arr, 0, result, 0, len);

        return result;
    }

    /**
     * 배열 비교하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181856)
     */
    @Test
    void p67() {
        int[] arr1 = {49, 13};
        int[] arr2 = {70, 11, 2};
        int expect = -1;

        int actual = p67Solution(arr1, arr2);
        assertThat(actual).isEqualTo(expect);
    }

    int p67Solution(int[] arr1, int[] arr2) {
        if (arr1.length == arr2.length) {
            int arr1Sum = Arrays.stream(arr1).sum();
            int arr2Sum = Arrays.stream(arr2).sum();

            if (arr1Sum == arr2Sum) {
                return 0;
            }

            return arr1Sum > arr2Sum ? 1 : -1;
        }

        return arr1.length > arr2.length ? 1 : -1;
    }

    /**
     * 문자열 묶기 (https://school.programmers.co.kr/learn/courses/30/lessons/181855)
     */
    @Test
    void p68() {
        String[] strArr = {"a", "bc", "d", "efg", "hi"};
        int expect = 2;

        int actual = p68Solution(strArr);
        assertThat(actual).isEqualTo(expect);
    }

    int p68Solution(String[] strArr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (String str : strArr) {
            int length = str.length();
            map.put(length, map.getOrDefault(length, 0) + 1);
        }

        int max = Integer.MIN_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }

    /**
     * 배열의 길이에 따라 다른 연산하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181854)
     */
    @Test
    void p69() {
        int[] arr = {49, 12, 100, 276, 33};
        int n = 27;
        int[] expect = {76, 12, 127, 276, 60};

        int[] actual = p69Solution(arr, n);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p69Solution(int[] arr, int n) {
        int length = arr.length;

        if (length % 2 != 0) {
            for (int i = 0; i < length; i+=2) {
                arr[i] += n;
            }
        } else {
            for (int i = 1; i < length; i+=2) {
                arr[i] += n;
            }
        }


        return arr;
    }

    /**
     * 전국 대회 선발 고사 (https://school.programmers.co.kr/learn/courses/30/lessons/181851)
     */
    @Test
    void p70() {
        int[] rank = {3, 7, 2, 5, 4, 6, 1};
        boolean[] attendance = {false, true, true, true, true, false, false};
        int expect = 20403;

        int actual = p70Solution(rank, attendance);
        assertThat(actual).isEqualTo(expect);
    }

    int p70Solution(int[] rank, boolean[] attendance) {
        List<Integer> rankers = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < rank.length; i++) {
            if(attendance[i]) {
                rankers.add(rank[i]);
                map.put(rank[i], i);
            }
        }

        Collections.sort(rankers);
        int a = map.get(rankers.get(0));
        int b = map.get(rankers.get(1));
        int c = map.get(rankers.get(2));

        return 10000 * a + 100 * b + c;
    }

    /**
     * 정수 부분 (https://school.programmers.co.kr/learn/courses/30/lessons/181850)
     */
    @Test
    void p71() {
        double flo = 1.42;
        int expect = 1;

        int actual = p71Solution(flo);
        assertThat(actual).isEqualTo(expect);
    }

    int p71Solution(double flo) {
        return (int) flo;
    }

    /**
     * 문자열 정수의 합 (https://school.programmers.co.kr/learn/courses/30/lessons/181849)
     */
    @Test
    void p72() {
        String num_str = "123456789";
        int expect = 45;

        int actual = p72Solution(num_str);
        assertThat(actual).isEqualTo(expect);
    }

    int p72Solution(String num_str) {
        int sum = 0;
        for (char ch : num_str.toCharArray()) {
            sum += (ch - '0');
        }
        return sum;
    }

    /**
     * 두 수의 합 (https://school.programmers.co.kr/learn/courses/30/lessons/181846)
     */
    @Test
    void p73() {
        String a = "18446744073709551615";
        String b = "287346502836570928366";
        String expect = "305793246910280479981";

        String actual = p73Solution(a, b);
        assertThat(actual).isEqualTo(expect);
    }

    String p73Solution(String a, String b) {
        BigDecimal bigA = new BigDecimal(a);
        BigDecimal bigB = new BigDecimal(b);

        BigDecimal added = bigA.add(bigB);
        return String.valueOf(added);
    }

    /**
     * 배열의 원소 삭제하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181844)
     */
    @Test
    void p74() {
        int[] arr = {293, 1000, 395, 678, 94};
        int[] delete_list = {94, 777, 104, 1000, 1, 12};
        int[] expect = {293, 395, 678};

        int[] actual = p74Solution(arr, delete_list);
        assertThat(actual).isEqualTo(expect);
    }

    int[] p74Solution(int[] arr, int[] delete_list) {
        Set<Integer> set = Arrays.stream(delete_list).boxed().collect(Collectors.toSet());
        List<Integer> answer = new ArrayList<>();

        for (int a : arr) {
            if (!set.contains(a)) {
                answer.add(a);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 꼬리 문자열 (https://school.programmers.co.kr/learn/courses/30/lessons/181841)
     */
    @Test
    void p75() {
        String[] str_list = {"abc", "def", "ghi"};
        String ex = "ef";
        String expect = "abcghi";

        String actual = p75Solution(str_list, ex);
        assertThat(actual).isEqualTo(expect);
    }

    String p75Solution(String[] str_list, String ex) {
        StringBuilder sb = new StringBuilder();

        for (String str : str_list) {
            if(!str.contains(ex)) sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 주사위 게임 1 (https://school.programmers.co.kr/learn/courses/30/lessons/181839)
     */
    @Test
    void p76() {
        int a = 3;
        int b = 5;
        int expect = 34;

        int actual = p76Solution(a, b);
        assertThat(actual).isEqualTo(expect);
    }

    int p76Solution(int a, int b) {
        if (a % 2 != 0 && b % 2 != 0) {
            return (a * a) + (b * b);
        } else if (a % 2 == 0 && b % 2 == 0) {
            return Math.abs(a - b);
        } else{
            return 2 * (a + b);
        }
    }

    /**
     * 날짜 비교하기 (https://school.programmers.co.kr/learn/courses/30/lessons/181838)
     */
    @Test
    void p77() {
        int[] date1 = {2021, 12, 28};
        int[] date2 = {2021, 12, 29};
        int expect = 1;

        int actual = p77Solution(date1, date2);
        assertThat(actual).isEqualTo(expect);
    }

    int p77Solution(int[] date1, int[] date2) {
        LocalDate dt1 = LocalDate.of(date1[0], date1[1], date1[2]);
        LocalDate dt2 = LocalDate.of(date2[0], date2[1], date2[2]);

        return dt1.isBefore(dt2) ? 1 : 0;
    }

}
