package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class CheckTriangle {
    //假设a,b,c均小于100
    public String checkTriangle(double a, double b, double c){
        if (a == 0){
            return "a不能为0";
        }else if(b == 0){
            return "b不能为0";
        }else if(c == 0){
            return "c不能为0";
        }else if (a < 0 || a > 100){
            return "a不在取值范围内";
        }else if (b < 0 || b > 100){
            return "b不在取值范围内";
        }else if (c < 0 || c > 100){
            return "c不在取值范围内";
        }else if (a + b <= c || a + c <= b || b + c <= a){
            return "非三角形";
        }else if (a == b && b == c){
            return "等边三角形";
        }else if (a == b || b == c || a == c){
            return "等腰三角形";
        }
        else{
            return "非等腰三角形";
        }
    }
}
