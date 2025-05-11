package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch2_decorator;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public int cost() {
        return coffee.cost() + 500;
    }
}

