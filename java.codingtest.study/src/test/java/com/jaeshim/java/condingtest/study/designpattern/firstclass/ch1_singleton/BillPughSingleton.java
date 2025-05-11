package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch1_singleton;

public class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("Instance Created!");
    }

    // 내부 static 클래스
    private static class SingletonHelper {
        // Singleton 인스턴스 생성
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        // getInstance를 호출할 때 SingletonHelper가 로딩됨
        return SingletonHelper.INSTANCE;
    }
}
