package com.jaeshim.java.codingtest.study.book;

import static org.assertj.core.api.Assertions.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
        assertThat(result).isEqualTo(expect);
    }


    int[] p06Solution(String[] genres, int[] plays) {
        Map<String, Integer> playMap = new HashMap<>();
        Map<Integer, String> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        Map<String, Map<Integer, Integer>> listByGenres = new HashMap<>(); // <장르, 재생횟수, 고유번호>

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            playMap.put(genres[i], playMap.getOrDefault(genres[i], 0) + plays[i]); // 장르별 재생횟수 합계 기록하기

            // 고유번호별 재생횟수 기록하기
            Map<Integer, Integer> genreMap = listByGenres.getOrDefault(genres[i], new TreeMap<>(Comparator.reverseOrder()));
            genreMap.put(plays[i], i);

            listByGenres.put(genres[i], genreMap);
        }

        // 장르별 전체 기록을 기준으로 정렬
        for (String p : playMap.keySet()){
            sortedMap.put(playMap.get(p), p);
        }

        // 최종 결과 산출
        for (Integer k : sortedMap.keySet()) {
            String genreKey = sortedMap.get(k);
            Map<Integer, Integer> m = listByGenres.get(genreKey);

            // 2개까지만 얻기
            Iterator<Entry<Integer, Integer>> iterator = m.entrySet().iterator();
            int count = 0;
            while (iterator.hasNext() && count < 2) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                answer.add(entry.getValue());
                count++;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 신고 결과 받기 (**)
     */
    @Test
    void p07() {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        int[] expected = {2, 1, 1, 0};

//        String[] id_list = {"con", "ryan"};
//        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
//        int k = 3;
//        int[] expected = {0, 0};

        int[] result = p07Solution(id_list, report, k);

        assertThat(result).isEqualTo(expected);
    }

    int[] p07Solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reporterMap = new LinkedHashMap<>(); // 신고자 리스트
        Map<String, Integer> reportedMap = new HashMap<>(); // 신고 당한 횟수
        List<Integer> result = new ArrayList<>();

        for (String i : id_list) {
            reporterMap.put(i, new HashSet<>()); // 신고자 리스트 초기화
        }

        for (String r : report) {
            String reporter = r.split(" ")[0];
            String reported = r.split(" ")[1];

            boolean added = reporterMap.get(reporter).add(reported);
            if (added) {
                reportedMap.put(reported, reportedMap.getOrDefault(reported, 0) + 1);
            }
        }

        // 신고 횟수가 k회 이상인 경우만 남기기
        reportedMap = reportedMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 신고자를 순회하면서 신고 대상에 있는지 확인
        for (String r : reporterMap.keySet()) {
            Set<String> reportSet = reporterMap.get(r);

            result.add((int) reportSet.stream()
                    .filter(reportedMap.keySet()::contains)
                    .count());

        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 메뉴 리뉴얼 (***)
     */
    @Test
    void p08() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        String[] expected = {"AC", "ACDE", "BCFG", "CDE"};

//        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//        int[] course = {2, 3, 5};
//        String[] expected = {"ACD", "AD", "ADE", "CD", "XYZ"};

//        String[] orders = {"XYZ", "XWY", "WXA"};
//        int[] course = {2, 3, 4};
//        String[] expected = {"WX", "XY"};

        String[] actual = p08Solution(orders, course);

        assertThat(actual).isEqualTo(expected);
    }

    String[] p08Solution(String[] orders, int[] course) {
//        // 1. 최소 2명의 손님에게서 주문된 단품 필터링하기
//        Map<Character, Long> menuCountMap = Arrays.stream(orders)
//                .flatMap(order -> order.chars().mapToObj(c -> (char)c))
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        String filterRegex = "[" + menuCountMap.entrySet().stream()
//                .filter(e -> e.getValue() < 2)
//                .map(e -> String.valueOf(e.getKey()))
//                .collect(Collectors.joining()) + "]";
//
//        if (!filterRegex.equals("[]")) {
//            orders = Arrays.stream(orders)
//                    .map(order -> order.replaceAll(filterRegex, ""))
//                    .toArray(String[]::new);
//        }
        // 2. 각 손님별로 course에 따라 나올 수 있는 조합 만들어서 Map으로 저장하기
        Map<Integer, Map<String, Integer>> courseMap = new HashMap<>();

        for (int c : course) {
            Map<String, Integer> map = new HashMap<>();
            for (String order : orders) {
                List<String> result = new ArrayList<>();
                combine(order, c, 0, new StringBuilder(), result);

                for (String re : result) {
                    char[] charArray = re.toCharArray();
                    Arrays.sort(charArray);
                    String str = new String(charArray);
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }
            }
            courseMap.put(c, map);
        }

        List<String> result = new ArrayList<>();
        for (Map<String, Integer> crsMap : courseMap.values()){
            crsMap.values()
                    .stream()
                    .max(Comparator.comparingInt(o -> o))
                    .ifPresent(cnt -> crsMap.entrySet()
                            .stream()
                            .filter(entry -> cnt.equals(entry.getValue()) && cnt > 1)
                            .forEach(entry -> result.add(entry.getKey())));
        }

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    // 주어진 문자열에서 r개 조합을 생성하는 메서드
    private static List<String> getCombinations(String str, int r) {
        List<String> result = new ArrayList<>();
        combine(str, r, 0, new StringBuilder(), result);
        return result;
    }

    // 재귀적으로 조합을 생성하는 메서드
    private static void combine(String str, int r, int start, StringBuilder sb, List<String> result) {
        if (sb.length() == r) {
            result.add(sb.toString());
            return;
        }
        for (int i = start; i < str.length(); i++) {
            sb.append(str.charAt(i));
            combine(str, r, i + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1); // 마지막 문자 제거하여 다음 조합 준비
        }
    }
}
