package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch4_proxy;

public class ch4_Proxy {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // 아직 로딩되지 않음
        System.out.println("Images are not loaded yet.");

        // display() 호출 시점에 실제 로딩
        image1.display();
        image2.display();

        // 두 번째 호출 시에는 로딩 없이 바로 표시됨 (캐싱된 상태)
        image1.display();
    }
}
