package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch2_factorymethod;

public class LoanFactory {

    // Factory Method
    public Loan getLoan(String loanType) {
        if (loanType == null) {
            return null;
        }

        if (loanType.equalsIgnoreCase("Housing")) {
            return new HouseLoan();
        } else if (loanType.equalsIgnoreCase("Car")) {
            return new CarLoan();
        } else if (loanType.equalsIgnoreCase("Personal")) {
            return new PersonalLoan();
        }

        return null;
    }
}
