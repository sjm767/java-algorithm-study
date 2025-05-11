package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch2_factorymethod;

public class ch02_FactoryMethod {

    public static void main(String[] args) {
        LoanFactory loanFactory = new LoanFactory();

        // 팩토리 메서드를 통해 객체 생성
        Loan housingLoan = loanFactory.getLoan("Housing");
        housingLoan.getInterestRate();   // Housing Loan Interest Rate: 2.5%

        Loan carLoan = loanFactory.getLoan("Car");
        carLoan.getInterestRate();       // Car Loan Interest Rate: 3.0%

        Loan personalLoan = loanFactory.getLoan("Personal");
        personalLoan.getInterestRate();  // Personal Loan Interest Rate: 4.5%
    }
}
