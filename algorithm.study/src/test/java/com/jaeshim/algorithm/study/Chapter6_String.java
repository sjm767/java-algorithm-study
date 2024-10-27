package com.jaeshim.algorithm.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class Chapter6_String {

    boolean s01Solution(String s) {
        StringBuilder org = new StringBuilder(s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase());
        StringBuilder reverse = org.reverse();
        return org.compareTo(reverse) == 0;
    }

    @Test
    @DisplayName("01_주어진 문자열이 팰린드롬인지 확인")
    void s01() {
        String str = "Do geese see God?";
        org.junit.jupiter.api.Assertions.assertTrue(s01Solution(str));
    }

    char[] s02Solution(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        }

        return s;
    }

    @Test
    @DisplayName("02_문자열 뒤집기")
    void s02() {
        char[] s = {'h','e','l','l','o'};
        char[] s2 = {'h','e','l','l'};
        System.out.println(s02Solution(s));
        System.out.println(s02Solution(s2));
    }

    String[] s03Solution(String [] logs) {
        List<String> list = new ArrayList<>();
        List<String> numList = new ArrayList<>();
        for (String log : logs) {
            int i = log.indexOf(" ") + 1;
            if (Character.isAlphabetic(log.charAt(i))) {
                list.add(log);
            } else {
                numList.add(log);
            }
        }

        Collections.sort(list, (o1, o2) -> {
            String[] s1 = o1.split(" ", 2);
            String[] s2 = o2.split(" ", 2);

            int compared = s1[1].compareTo(s2[1]);

            // 문자열이 동일한 경우 식별자 순
            if (compared == 0) {
                return s1[0].compareTo(s2[0]);
            }
            return compared;
        });

        list.addAll(numList);

        return list.toArray(new String[0]);
    }

    @Test
    @DisplayName("03_로그 파일 재정렬")
    void s03() {
        String[] input = {
                "id1 8 1 5 1",
                "id7 art can",
                "id2 art can",
                "id3 3 6",
                "id4 own kit dig",
                "id5 art zero"
        };

        String[] str = s03Solution(input);
        for (String s : str) {
            System.out.println(s);
        }
    }

    String s04Solution(String[] paragraph, String[] banned) {
        // 전처리 : 문자만 남겨놓고 제거
        String collect = Arrays.stream(paragraph).map(p -> {
            p = p.replaceAll("[^a-zA-Z0-9!\\s]", "");
            return p.toLowerCase();
        }).collect(Collectors.joining(" "));

        // 전처리: contains 연산을 위해 Set에 넣음.
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        // 단어별 숫자 세기
        Map<String, Integer> countMap = new HashMap<>();
        String[] splits = collect.split(" ");
        for (String split : splits) {
            if (!bannedSet.contains(split)) {
                countMap.put(split, countMap.getOrDefault(split, 0) + 1);
            }
        }

        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Test
    @DisplayName("04_가장 흔한 단어")
    void s04() {
        String[] paragraph = {
          "Ross hit a ball",
          "the hit BALL flew far away after it was hit."
        };
        String[] banned = {"hit"};

        System.out.println(s04Solution(paragraph, banned));
    }

    List<List<String>> s05Solution(String[] str) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : str) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);
            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(s);

            map.put(key, value);
        }

        return new ArrayList<>(map.values());
    }
    
    @Test
    @DisplayName("05_그룹 애너그램")
    void s05() {
        String[] s = {
                "eat",
                "tea",
                "tan",
                "ate",
                "ant",
                "cat"
        };

        List<List<String>> lists = s05Solution(s);
        lists.forEach(l -> {
           l.forEach(e -> {
               System.out.print(e + ",");
           });
            System.out.println();
        });

    }
    int lLeft, maxLen;
    void extendPalindrome(String s, int left, int right) {
        // 투 포인터가 유효한 범위 내에 있고 양쪽 끝 문자가 일치하는 팰린드롬인 경우 범위 확장
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        //기존 최대 길이보다 큰 경우 값 교체
        if (maxLen < right - left - 1) {
            lLeft = left + 1;
            maxLen = right - left - 1;
        }
    }

    String s06Solution(String str) {
        int len = str.length();
        // 예외 처리
        if (len < 2) {
            return str;
        }

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(str, i, i + 1);
            extendPalindrome(str, i, i + 2);
        }

        return str.substring(lLeft, lLeft + maxLen);
    }

    @Test
    @DisplayName("06_가장 긴 팰린드롬 부분 문자열")
    void s06() {
        String input = "dcbabcdd";
        System.out.println(s06Solution(input));
    }

}
