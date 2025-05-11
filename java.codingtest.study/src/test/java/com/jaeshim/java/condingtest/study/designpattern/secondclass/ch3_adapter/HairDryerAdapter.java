package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch3_adapter;

public class HairDryerAdapter implements Electronic220V {
    private final HairDryer hairDryer;

    public HairDryerAdapter(HairDryer hairDryer) {
        this.hairDryer = hairDryer;
    }

    @Override
    public void connect() {
        hairDryer.powerOn();
    }
}
