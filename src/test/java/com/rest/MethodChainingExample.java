package com.rest;

public class MethodChainingExample {

    public static void main(String[] args) {
        a1().a2().a3();
    }

    public static MethodChainingExample a1(){
        System.out.println("In a1");
        return new MethodChainingExample();
    }

    public MethodChainingExample a2(){
        System.out.println("in a2");
        return this;
    }

    public MethodChainingExample a3(){
        System.out.println("in a3");
        return this;
    }
}
