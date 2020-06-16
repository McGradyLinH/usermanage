package com.jc.usermanage.controller;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/12 8:57
 */
public class Test {
    private static final int _1m = 1_024 * 1_024;

    public static void main(String[] args) {
        //-XX:+PrintGCDetails -verbose:gc -XX:SurvivorRatio=8 -Xmx20m -Xms20m -Xmn10m
//        byte[] byte1 = new byte[5 * _1m];
//        byte1 = null;
//        byte[] byte2 = new byte[7 * _1m];
//        byte[] byte3 = new byte[2 * _1m];
//        byte[] byte4 = new byte[4 * _1m];
        System.out.println(SubClass.value);

//
//        new SubClass();
//        System.out.println(ClassLayout.parseInstance(new TestObject()).toPrintable());
//
//        System.out.println(GraphLayout.parseInstance(new TestObject()).toPrintable());


    }
}

class TestObject {
    private Object object = new Object();
    private Object object1;
}

class SuperClass {
    public static final String value = "123";

    static {
        System.out.println("superclass init");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass.static initializer");
    }
}