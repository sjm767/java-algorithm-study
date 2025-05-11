package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch3_chainofresponsibility;

public class DataHandler extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("AUTHENTICATED")) {
            System.out.println("Data Access Granted: Fetching user data...");
        } else {
            System.out.println("Data Access Denied");
        }
    }
}

