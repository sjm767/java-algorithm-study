package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch1_builder;

public class ch1_Builder {
    public static void main(String[] args) {
        // Builder 패턴 사용 예시
        User user = new User.UserBuilder()
                .setName("Jaemin")
                .setAge(30)
                .setEmail("jaemin@example.com")
                .setAddress("Seoul")
                .build();

        System.out.println(user);
    }
}
