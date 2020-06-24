package com.example.demo.controller;

import com.example.demo.service.Calendar;
import com.example.demo.service.CheckTriangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private CheckTriangle checkTriangle;

    @Autowired
    private Calendar calendar;

    @PostMapping(value = "/triangle")
    public String checkTriangle(@RequestParam int a, @RequestParam int b, @RequestParam int c){
        return checkTriangle.checkTriangle(a, b, c);
    }

    @PostMapping(value = "/date")
    public String getNextDate(@RequestParam int year, @RequestParam int month, @RequestParam int day){
        return calendar.getNextDate(year, month, day);
    }

}
