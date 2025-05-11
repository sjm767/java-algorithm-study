package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch1_singleton;

public class LazySingleton {
    // 인스턴스 선언만 해놓음
    private static LazySingleton instance;

    // 생성자를 private으로 막음
    private LazySingleton() {}

    // 인스턴스 반환 메서드
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

