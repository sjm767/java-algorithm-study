package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch2_decorator;

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public int cost() {
        return coffee.cost();
    }
}

