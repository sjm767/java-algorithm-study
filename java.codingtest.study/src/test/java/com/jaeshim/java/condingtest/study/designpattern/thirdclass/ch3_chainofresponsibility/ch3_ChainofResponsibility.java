package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch3_chainofresponsibility;

public class ch3_ChainofResponsibility {
    public static void main(String[] args) {
        // 체인 생성
        Handler authHandler = new AuthenticationHandler();
        Handler authzHandler = new AuthorizationHandler();
        Handler dataHandler = new DataHandler();

        // 체인 연결
        authHandler.setNext(authzHandler);
        authzHandler.setNext(dataHandler);

        // 요청 처리
        System.out.println("=== 인증된 요청 ===");
        authHandler.handleRequest("AUTHENTICATED");

        System.out.println("\n=== 인증되지 않은 요청 ===");
        authHandler.handleRequest("UNAUTHENTICATED");
    }
}
