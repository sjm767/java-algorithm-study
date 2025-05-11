package com.jaeshim.java.condingtest.study.designpattern.thirdclass.ch2_composite;

public class ch2_Composite {
    public static void main(String[] args) {
        // 개별 파일 생성
        File file1 = new File("Document.txt");
        File file2 = new File("Photo.jpg");
        File file3 = new File("Video.mp4");

        // 폴더 생성
        Directory directory1 = new Directory("My Documents");
        Directory directory2 = new Directory("My Media");

        // 폴더에 파일 추가
        directory1.add(file1);
        directory2.add(file2);
        directory2.add(file3);

        // 상위 폴더 생성
        Directory rootDirectory = new Directory("Root");
        rootDirectory.add(directory1);
        rootDirectory.add(directory2);

        // 전체 구조 출력
        rootDirectory.showDetails();
    }
}
