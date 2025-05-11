package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch1_singleton;

public class EagerSingleton {
    // 인스턴스 생성 (JVM 로딩 시점에 생성됨)
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // 생성자를 private으로 막음
    private EagerSingleton() {}

    // 인스턴스 반환 메서드
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
