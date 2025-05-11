package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch3_adapter;

public class ch3_Adapter {
    public static void main(String[] args) {
        Electronic220V hairDryer = new HairDryerAdapter(new HairDryer());
        hairDryer.connect();  // 220V HairDryer is powered on

        Electronic220V cleaner = new CleanerAdapter(new Cleaner());
        cleaner.connect();    // 220V Cleaner is now running
    }
}
