package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch3_adapter;

public class CleanerAdapter implements Electronic220V {
    private final Cleaner cleaner;

    public CleanerAdapter(Cleaner cleaner) {
        this.cleaner = cleaner;
    }

    @Override
    public void connect() {
        cleaner.start();
    }
}
