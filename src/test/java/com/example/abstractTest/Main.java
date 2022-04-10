package com.example.abstractTest;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: luozijian
 * @date: 10/12/21 14:07:22
 * @description:
 */
public class Main {

    public static void main(String[] args) {

        Parent yellow = Yellow.builder().country("广州").build();
        yellow.setName("yellow");

        System.out.println(retrieveValueFromField(yellow, "country"));

        System.out.println(retrieveValueFromField(yellow, "name"));
    }


    public static Object retrieveValueFromField(Object source, String fieldName){

        List<Field> allFields = getAllField(source);
        for(Field field : allFields){
            field.setAccessible(true);
            if(!Objects.equals(fieldName, field.getName())){
                continue;
            }
            try{
                return field.get(source);
            }catch (Exception e) {

            }
        }
        return null;
    }

    private static List<Field> getAllField(Object model){
        Class clazz = model.getClass();
        List<Field> fields = Lists.newArrayList();
        while (clazz != null){
            fields.addAll(Lists.newArrayList(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}

