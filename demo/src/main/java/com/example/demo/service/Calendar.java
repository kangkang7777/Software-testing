package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Calendar {
    public String getNextDate(int year, int month, int day){
        if (year < 1900 || year > 2500){
            return "非法年份";
        }else if (month < 1 || month > 12){
            return "非法月份";
        }else if (day < 1 || day > 31){
            return "非法日期";
        }

        if (month == 2 && day == 28){
            if (isLeapYear(year)){
                day = 29;
            }else {
                month = 3;
                day = 1;
            }
        } else if (month == 2 && day == 29){
            if (isLeapYear(year)){
                month = 3;
                day = 1;
            }else {
                return "非法日期";
            }
        } else if (month == 2 && day > 29){
            return "非法日期";
        } else if (day == 30){
            if (month == 4 || month == 6 || month == 9 || month == 11){
                month = month + 1;
                day = 1;
            }
        } else if (day == 31){
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10){
                month = month + 1;
                day = 1;
            } else if (month == 12){
                year = year + 1;
                month = 1;
                day = 1;
            } else {
                return "非法日期";
            }
        }
        else {
            day = day + 1;
        }
        return "下一天的日期为:" + year + "年" + month + "月" + day + "日";
    }

    private boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
