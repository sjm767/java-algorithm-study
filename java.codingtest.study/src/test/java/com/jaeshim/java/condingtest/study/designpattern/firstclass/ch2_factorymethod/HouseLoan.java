package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch2_factorymethod;

public class HouseLoan implements Loan {
    @Override
    public void getInterestRate() {
        System.out.println("Housing Loan Interest Rate: 2.5%");
    }
}
