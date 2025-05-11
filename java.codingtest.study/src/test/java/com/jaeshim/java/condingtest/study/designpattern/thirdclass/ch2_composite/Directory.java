package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch2_composite;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    // 하위 항목 추가
    public void add(FileSystemComponent component) {
        components.add(component);
    }

    // 하위 항목 삭제
    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

