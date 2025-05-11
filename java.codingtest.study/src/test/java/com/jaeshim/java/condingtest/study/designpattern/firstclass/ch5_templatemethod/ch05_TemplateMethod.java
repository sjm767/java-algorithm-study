package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch5_templatemethod;

public class ch05_TemplateMethod {
    public static void main(String[] args) {
        // CSV 처리
        DataProcessor csvProcessor = new CSVDataProcessor();
        csvProcessor.process();

        System.out.println("----");

        // JSON 처리
        DataProcessor jsonProcessor = new JSONDataProcessor();
        jsonProcessor.process();
    }
}
