package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch3_strategy;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + "원 결제를 카드로 진행합니다.");
    }
}
