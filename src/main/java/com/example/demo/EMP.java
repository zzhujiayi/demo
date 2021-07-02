package com.example.demo;

public class EMP {
    public EMP() {

    }

    public EMP(int id, String name, int age, boolean sex, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.major = major;
    }

    private int id;

    private String name;

    private int age;

    private boolean sex;

    private String major;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void PrintEMP() {
        System.out.println("EMP{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", major='" + major + '\'' +
                '}');
    }
}
