package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch4_observer;

public class ch04_Observer {

    public static void main(String[] args) {
        // 발행자 생성
        NewsPublisher publisher = new NewsPublisher();

        // 구독자 생성 및 등록
        Observer sub1 = new NewsSubscriber("Subscriber 1");
        Observer sub2 = new NewsSubscriber("Subscriber 2");
        Observer sub3 = new NewsSubscriber("Subscriber 3");

        publisher.registerObserver(sub1);
        publisher.registerObserver(sub2);
        publisher.registerObserver(sub3);

        // 뉴스 발행
        publisher.publishNews("Breaking News: Java 20 Released!");
    }
}
