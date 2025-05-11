package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch2_factorymethod;

public class CarLoan implements Loan {
    @Override
    public void getInterestRate() {
        System.out.println("Car Loan Interest Rate: 3.0%");
    }
}

