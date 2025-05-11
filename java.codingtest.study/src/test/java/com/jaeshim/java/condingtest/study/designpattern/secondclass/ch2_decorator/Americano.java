package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch2_decorator;

public class Americano implements Coffee {

    @Override
    public String getDescription() {
        return "Americano";
    }

    @Override
    public int cost() {
        return 3500;
    }
}

