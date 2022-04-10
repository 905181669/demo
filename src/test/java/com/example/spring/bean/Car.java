package com.example.spring.bean;

/**
 * @author: luozijian
 * @date: 4/10/21 13:24:58
 * @description:
 */
public class Car {
    // 只包含基本数据类型的属性
    private int speed;
    private double price;
    private User user;

    public Car() {
    }
    public Car(int speed, double price, User user) {
        this.speed = speed;
        this.price = price;
        this.user = user;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @Override
//    public String toString() {
//        return "Car{" +
//                "speed=" + speed +
//                ", price=" + price +
//                '}';
//    }

    /**
     * 自定义的初始化方法
     */
    public void start(){
        System.out.println("Car 中自定义的初始化方法");
    }


    public void destroy(){
        System.out.println("Car 中自定义的destory方法");
    }
}
