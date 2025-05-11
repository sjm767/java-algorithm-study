package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch3_chainofresponsibility;

public abstract class Handler {
    protected Handler next;

    // 다음 체인에 연결하기 위한 메서드
    public void setNext(Handler next) {
        this.next = next;
    }

    // 처리 메서드
    public abstract void handleRequest(String request);
}

