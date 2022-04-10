package com.example.spring.bean;

/**
 * @author: luozijian
 * @date: 4/10/21 13:25:16
 * @description:
 */
public class User {

    private String name;
    private int age;
    // 除了上面两个基本数据类型的属性，User还依赖Car
    private Car car;

    public User() {
    }
    public User(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
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
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
