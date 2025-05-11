package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch1_facade;

public class ComputerFacade {
    private final Power power;
    private final Memory memory;
    private final HardDrive hardDrive;

    public ComputerFacade() {
        this.power = new Power();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    // 단순하게 전원을 켤 때 필요한 모든 과정을 한번에 처리
    public void start() {
        power.turnOn();
        memory.load();
        hardDrive.boot();
        System.out.println("Computer is ready to use!");
    }

    // 단순하게 전원을 끌 때 필요한 모든 과정을 한번에 처리
    public void shutdown() {
        hardDrive.shutdown();
        memory.release();
        power.turnOff();
        System.out.println("Computer is shut down.");
    }
}

