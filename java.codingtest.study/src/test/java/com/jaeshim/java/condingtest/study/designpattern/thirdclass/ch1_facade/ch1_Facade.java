package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch1_facade;

public class ch1_Facade {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();

        System.out.println("=== Starting Computer ===");
        computer.start();

        System.out.println("\n=== Shutting Down Computer ===");
        computer.shutdown();
    }
}
