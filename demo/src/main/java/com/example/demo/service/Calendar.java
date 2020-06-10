package com.example.demo.service;

public class Calendar {
    public String getDate(int year, int month, int day){

        return "";
    }

    private boolean isLeapYear(int year){
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            return true;
        }else {
            return false;
        }
    }
}
