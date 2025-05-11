package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch3_chainofresponsibility;

public class AuthorizationHandler extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("AUTHENTICATED")) {
            System.out.println("Authorization granted");
            if (next != null) {
                next.handleRequest(request);
            }
        } else {
            System.out.println("Authorization denied");
        }
    }
}

