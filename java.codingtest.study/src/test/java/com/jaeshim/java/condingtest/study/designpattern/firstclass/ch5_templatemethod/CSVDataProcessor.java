package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch5_templatemethod;

public class CSVDataProcessor extends DataProcessor {

    @Override
    protected void processData() {
        System.out.println("Processing data in CSV format...");
    }
}

