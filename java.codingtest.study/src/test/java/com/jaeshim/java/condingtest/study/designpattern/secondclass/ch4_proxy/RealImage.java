package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch4_proxy;

public class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    // 실제 이미지 로딩 작업 (비용이 큰 작업이라고 가정)
    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

