package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch5_templatemethod;

public abstract class DataProcessor {

    // Template Method: 알고리즘의 큰 구조를 정의
    public final void process() {
        readData();       // 1. 데이터 읽기
        processData();    // 2. 데이터 처리 (하위 클래스 구현)
        saveData();       // 3. 데이터 저장
    }

    // 기본 구현 (선택적으로 오버라이딩 가능)
    protected void readData() {
        System.out.println("Reading data from source...");
    }

    // 하위 클래스에서 반드시 구현해야 하는 메서드
    protected abstract void processData();

    // 기본 구현 (선택적으로 오버라이딩 가능)
    protected void saveData() {
        System.out.println("Saving processed data to destination...");
    }
}

