package com.example.lab4_3;

public class Student {
    private int id;
    private String studentid;
    private String name;
    private int age;
    private float score;
    private String address;

    Student(int id, String studentid, String name, int age, float score, String address){
        this.id = id;
        this.studentid = studentid;
        this.name = name;
        this.age = age;
        this.score = score;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getScore() {
        return score;
    }

    public String getAddress() {
        return address;
    }
}
