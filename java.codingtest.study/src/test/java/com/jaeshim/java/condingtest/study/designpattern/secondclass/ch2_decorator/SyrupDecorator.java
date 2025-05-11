package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch2_decorator;

public class SyrupDecorator extends CoffeeDecorator {

    public SyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Syrup";
    }

    @Override
    public int cost() {
        return coffee.cost() + 300;
    }
}
