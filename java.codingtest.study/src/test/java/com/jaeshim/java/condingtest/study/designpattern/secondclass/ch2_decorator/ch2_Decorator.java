package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch2_decorator;

public class ch2_Decorator {
    public static void main(String[] args) {
        // 기본 커피 생성
        Coffee coffee = new Espresso();
        System.out.println(coffee.getDescription() + " | Cost: " + coffee.cost() + "원");

        // Milk 옵션 추가
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " | Cost: " + coffee.cost() + "원");

        // Syrup 옵션 추가
        coffee = new SyrupDecorator(coffee);
        System.out.println(coffee.getDescription() + " | Cost: " + coffee.cost() + "원");
    }
}
