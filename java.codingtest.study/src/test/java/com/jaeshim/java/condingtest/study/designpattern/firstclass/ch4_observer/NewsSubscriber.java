package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch4_observer;

public class NewsSubscriber implements Observer {
    private final String name;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}
