package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch3_strategy;

public class PaymentContext {
    // 전략 객체를 주입받음
    private PaymentStrategy paymentStrategy;

    // 생성자에서 어떤 전략을 사용할지 결정
    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // 실행 메서드
    public void executePayment(int amount) {
        System.out.println("지불 시작");
        paymentStrategy.pay(amount);
        System.out.println("지불 완료");
    }
}

