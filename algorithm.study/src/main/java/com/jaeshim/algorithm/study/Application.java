package com.jaeshim.algorithm.study;

import com.jaeshim.algorithm.study.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

//public class Application {

//	public static void main(String[] args) {
////		Person person1 = new Person("person1", 1);
////		Person person2 = new Person("person2", 2);
////
////		List<Person> persons = new ArrayList<>(List.of(person2, person1));
////
////		persons.sort((o1, o2) -> {
////            if (o1.getAge() < o2.getAge()) {
////                return -1;
////            } else if (o1.getAge() > o2.getAge()) {
////                return 1;
////            }
////            return 0;
////        });
////
////		persons.forEach(p -> System.out.println(p.getAge()));
//
//        // Integer[]에 1억개 삽입
//        long start = System.currentTimeMillis();
//        Integer[] intElements = new Integer[100000000];
//        for (int i = 0; i < 100000000 - 1; i++) {
//            intElements[i] = 1;
//        }
//        intElements[100000000 - 1] = 2;
//
//        // int[] 1억 개 중 찾기
//        int idx = 0;
//        while (2 != intElements[idx]) {
//            idx++;
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println(end - start + "ms");
//
//	}
//        int count = 0;
//        public int factorial(int n) {
//                if (n >= 1) {
//                        count++;
//                        return n * factorial(n - 1);
//                }
//                else {
//                        count++;
//                        return 1;
//                }
//        }
//}





