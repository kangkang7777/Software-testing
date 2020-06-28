package com.example.demo.service;

public class OnlinePay {

    private double balance;
    private final double maxBalance = 99999999999.0;

    double getBalance() {
        return balance;
    }

    boolean withdrawBalance(double cash){
        if(cash > balance)return false;
        balance -= cash;
        return true;
    }

    boolean addBalance(double cash){
        if(balance + cash > maxBalance)return false;
        balance += cash;
        return true;
    }
}
