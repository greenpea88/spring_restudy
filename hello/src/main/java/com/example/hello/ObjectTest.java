package com.example.hello;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectTest {

    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public ObjectTest(){
        this.name = null;
        this.age = 0;
        this.phoneNumber = null;
    }

    public ObjectTest(String name, int age, String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "ObjectTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
