package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch3_strategy;

public class ch03_Strategy {
    public static void main(String[] args) {
        // 카드 결제로 실행
        PaymentContext cardPayment = new PaymentContext(new CardPayment());
        cardPayment.executePayment(10000);

        // 페이팔 결제로 실행
        PaymentContext paypalPayment = new PaymentContext(new PayPalPayment());
        paypalPayment.executePayment(20000);

        // 계좌이체 결제로 실행
        PaymentContext bankTransferPayment = new PaymentContext(new BankTransferPayment());
        bankTransferPayment.executePayment(30000);
    }
}
