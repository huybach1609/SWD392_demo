package com.practice.swd392_demo.utitlities;

import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.util.Date;

public class Utility {
    public static String GenerateActivationKey(int length){
        return RandomString.make(length) + new Date().toString();
    }
}
