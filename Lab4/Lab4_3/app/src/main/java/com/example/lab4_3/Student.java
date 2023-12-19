package com.example.lab4_3;

public class Student {
    private int Id;
    private String Name;
    private String Email;
    private String PhoneNumber;
    private String Score;
    private String Address;

    public Student(String name, String email, String phoneNumber, String score, String address) {
        Name = name;
        Email = email;
        PhoneNumber = phoneNumber;
        Score = score;
        Address = address;
    }

    public Student(int id, String name, String email, String phoneNumber, String score, String address) {
        Id = id;
        Name = name;
        Email = email;
        PhoneNumber = phoneNumber;
        Score = score;
        Address = address;
    }

    public void update(String name, String email, String phoneNumber, String score, String address) {
        Name = name;
        Email = email;
        PhoneNumber = phoneNumber;
        Score= score;
        Address = address;
    }

    int getId(){
        return Id;
    }
    String getName(){
        return Name;
    }
    String getPhoneNumber(){
        return PhoneNumber;
    }
    String getEmail(){
        return Email;
    }
    String getScore(){
        return Score;
    }
    String getAddress(){
        return Address;
    }
}