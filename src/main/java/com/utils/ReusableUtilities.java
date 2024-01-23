package com.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReusableUtilities {

    public static int generateRandomNumber(){
        return (int)(Math.random()*10);
    }


    public static String generateTimeStamp(){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        return date+time.toString();
    }
}
