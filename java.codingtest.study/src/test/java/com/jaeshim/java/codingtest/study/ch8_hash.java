package com.jaeshim.java.codingtest.study;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
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
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        String expect = "leo";

//        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//        String[] completion = {"josipa", "filipa", "marina", "nikola"};
//        String expect = "vinko";

//        String[] participant = {"mislav", "stanko", "mislav", "ana"};
//        String[] completion = {"stanko", "ana", "mislav"};
//        String expect = "mislav";

        String actual = p02Solution(participant, completion);
        assertThat(actual).isEqualTo(expect);
    }

    String p02Solution(String[] participant, String[] completion) {
        Map<String, Integer> participantMap = new HashMap<>();

        // 참가자를 해시맵에 넣기.
        for (String p : participant) {
            participantMap.put(p, participantMap.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            Integer count = participantMap.get(c);
            count--;
            if (count == 0) {
                participantMap.remove(c);
            } else {
                participantMap.put(c, participantMap.get(c) - 1);
            }
        }

        return participantMap.keySet().iterator().next();
    }

    /**
     * 할인 행사 (**)
     */
    @Test
    void p03() {
        String[] want = {"banana", "apple", "rice", "pork", "pot"}; // 원하는 제품
        int[] number = {3, 2, 2, 2, 1};
        // 할인행사
        String[] discount = {
            "chicken", "apple", "apple", "banana",
            "rice", "apple", "pork", "banana",
            "pork", "rice", "pot", "banana",
            "apple", "banana"
        };
        int expect = 3;

//        String[] want = {"apple"}; // 원하는 제품
//        int[] number = {10};
//        // 할인행사
//        String[] discount = {
//            "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana",
//            "banana", "banana"
//        };
//        int expect = 0;

        int actual = p03Solution(want, number, discount);
        assertThat(actual).isEqualTo(expect);
    }
    int p03Solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantMap = new HashMap<>();
        Map<String, Integer> discountMap = new HashMap<>();
        int result = 0;

        // 구매 원하는 품목 추가
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 할인 정보 Map 초기화
        for (int i = 0; i < 10; i++) {
            discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
        }

        int maxDay = discount.length - 10;
        int startDay = 0;
        int endDay = 9;

        while (startDay <= maxDay) {
            if (wantMap.equals(discountMap)) {
               result++;
            }

            if (startDay == maxDay) {
                break;
            }

            // 오늘 날짜 제거
            discountMap.put(discount[startDay], discountMap.get(discount[startDay]) - 1);
            if (discountMap.get(discount[startDay]) <= 0) {
                discountMap.remove(discount[startDay]);
            }
            startDay++;

            // 신규 날짜 추가
            endDay++;
            discountMap.put(discount[endDay], discountMap.getOrDefault(discount[endDay], 0) + 1);
        }
        return result;
    }

    /**
     * 오픈채팅방 (**)
     */
    @Test
    void p04() {
        String[] record = {
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        };
        String[] expect = {
            "Prodo님이 들어왔습니다.",
            "Ryan님이 들어왔습니다.",
            "Prodo님이 나갔습니다",
            "Prodo님이 들어왔습니다"
        };

        String[] actual = p05Solution(record);
    }

    String[] p05Solution(String[] record) {
        List<String> result = new ArrayList<>(); // 최종 결과
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("Enter", "님이 들어왔습니다.");
        msgMap.put("Leave", "님이 나갔습니다.");

        List<String> logs = new ArrayList<>();
        Map<String, String> userMap = new HashMap<>(); // <uid, nickname>


        for (String r : record) {
            String[] sp = r.split(" ");
            String action = sp[0];
            String uid = sp[1];

            // Enter 또는 Change 인 경우
            if (sp.length == 3) {
                String nickname = sp[2];
                userMap.put(uid, nickname);
            }
        }

        for (String r : record) {
            String[] sp = r.split(" ");
            String action = sp[0];
            String uid = sp[1];

            if (msgMap.containsKey(action)) {
                result.add(userMap.get(uid) + msgMap.get(action)); // 메시지 완성
            }
        }

        return result.toArray(String[]::new);
    }

    /**
     * 베스트 앨범 (**)
     */
    @Test
    void p06() {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] expect = {4, 1, 3, 0};

        int[] result = p06Solution(genres, plays);
    }


    int[] p06Solution(String[] genres, int[] plays) {
        Map<String, Integer> playMap = new HashMap<>();
        Map<Integer, String> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        Map<String, Map<Integer, Integer>> listByGenres = new HashMap<>(); // <장르, 재생횟수, 고유번호>

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            playMap.put(genres[i], playMap.getOrDefault(genres[i], 0) + plays[i]); // 장르별 재생횟수 기록하기
            // 고유번호별 재생횟수 기록하기

            Map<Integer, Integer> genreMap = listByGenres.getOrDefault(genres[i], new TreeMap<>(Comparator.reverseOrder()));
            genreMap.put(plays[i], i);

            listByGenres.put(genres[i], genreMap);
        }

        // 장르별 전체 기록
        for (String p : playMap.keySet()){
            sortedMap.put(playMap.get(p), p);
        }

        // 최종 결과 산출
        for (Integer k : sortedMap.keySet()) {
            Map<Integer, Integer> m = listByGenres.get(k);

            // 2개까지만 얻기
            Iterator<Entry<Integer, Integer>> iterator = m.entrySet().iterator();
            int count = 0;
            while (iterator.hasNext() && count < 2) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                answer.add(entry.getValue());
                count++;
            }
        }




        return null;
    }


}
