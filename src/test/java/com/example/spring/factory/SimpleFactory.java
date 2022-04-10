package com.example.spring.factory;

import com.example.spring.bean.Car;

/**
 * @author: luozijian
 * @date: 4/10/21 13:42:19
 * @description:
 */
public class SimpleFactory {
    /**
     * 静态工厂，返回一个Car的实例对象
     */
    public static Car getCar() {
        return new Car(12345, 5.4321, null);
    }
}
