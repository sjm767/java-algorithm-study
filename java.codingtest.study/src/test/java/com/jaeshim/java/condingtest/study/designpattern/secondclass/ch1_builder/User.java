package com.jaeshim.java.condingtest.study.designpattern.secondclass.ch1_builder;

public class User {
    private final String name;
    private final int age;
    private final String email;
    private final String address;

    // Private 생성자: 외부에서 직접 생성하지 못하도록 막음
    private User(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", email='" + email + "', address='" + address + "'}";
    }

    // Builder Class (Static Inner Class)
    public static class UserBuilder {
        // 필드와 동일한 멤버 변수
        private String name;
        private int age;
        private String email;
        private String address;

        // Builder 메서드들
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        // 최종 빌드 메서드
        public User build() {
            return new User(this);
        }
    }
}

