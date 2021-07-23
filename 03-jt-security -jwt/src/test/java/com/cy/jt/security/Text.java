package com.cy.jt.security;

import java.util.Random;
import java.util.stream.DoubleStream;

public class Text {
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++){
            double a =new Random().nextInt(13)+995;
            System.out.println(a);
        }
    }
}
