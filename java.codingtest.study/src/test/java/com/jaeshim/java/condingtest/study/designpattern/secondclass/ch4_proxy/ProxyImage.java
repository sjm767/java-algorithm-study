package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch4_proxy;

public class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        // 실제 이미지를 생성하는 시점은 display()를 호출할 때
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

