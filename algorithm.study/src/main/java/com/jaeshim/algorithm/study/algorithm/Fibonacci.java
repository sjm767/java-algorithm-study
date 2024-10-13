package com.jaeshim.algorithm.study.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private static Map<Integer, Integer> memo = new HashMap<>();

    // 재귀 사용
    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // for문 사용
    static int fibonacci2(int n) {
        if (n <= 1) {
            return n; // n이 0 또는 1일 경우 n을 반환
        }

        int a = 0, b = 1; // 초기값 설정
        int fib = 0;

        // n번째 피보나치 수까지 반복
        for (int i = 2; i <= n; i++) {
            fib = a + b; // 현재 피보나치 수 계산
            a = b; // 이전 두 수 업데이트
            b = fib;
        }
        return fib; // n번째 피보나치 수 반환
    }

    //메모이제이션 사용
    static int fibonacci3(int n) {
        // 이미 계산된 값이 있으면 반환
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // 기본 조건
        if (n <= 1) {
            return n; // n이 0 또는 1일 경우 n을 반환
        }

        // 피보나치 수 계산 및 메모이제이션에 저장
        int result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result); // 결과를 메모에 저장
        return result; // n번째 피보나치 수 반환
    }

    public static void main(String[] args) {
        int fibo = 5;
        System.out.println(fibonacci(fibo));
        System.out.println(fibonacci2(fibo));
        System.out.println(fibonacci3(fibo));
    }
}
