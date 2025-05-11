package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch2_factorymethod;

public class PersonalLoan implements Loan {
    @Override
    public void getInterestRate() {
        System.out.println("Personal Loan Interest Rate: 4.5%");
    }
}
