package com.example.demo.spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
//@Scope("prototype")
public class Test {

    public Test() {

    }

    @Autowired
    private TestB testB;

    public Test(TestB testB) {

        this.testB = testB;
    }


    public TestB getTestB() {
        return testB;
    }


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
