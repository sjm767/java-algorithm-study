package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch2_decorator;

public class Espresso implements Coffee {

    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public int cost() {
        return 3000;
    }
}
