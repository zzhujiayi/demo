package com.example.demo.springlean;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class Test {

    public Test() {

    }

    private TestB testB;

    public Test(TestB testB) {
        
        this.testB = testB;
    }


    public TestB getTestB() {
        return testB;
    }

    @Autowired
    public void setTestB(TestB testB) {
        this.testB = testB;
    }

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
