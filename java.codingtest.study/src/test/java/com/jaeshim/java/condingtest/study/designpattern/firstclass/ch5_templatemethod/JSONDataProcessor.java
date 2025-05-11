package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch5_templatemethod;

public class JSONDataProcessor extends DataProcessor {

    @Override
    protected void processData() {
        System.out.println("Processing data in JSON format...");
    }
}

