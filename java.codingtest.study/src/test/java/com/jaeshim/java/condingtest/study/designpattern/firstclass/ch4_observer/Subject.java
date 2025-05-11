package com.jaeshim.java.condingtest.study.designpattern.firstclass.ch4_observer;

import java.util.List;
import java.util.ArrayList;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String news);
}
