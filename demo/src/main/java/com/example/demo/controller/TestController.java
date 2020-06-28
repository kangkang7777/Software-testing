package com.example.demo.controller;

import com.example.demo.service.ComputerSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private ComputerSales computerSales;

    @GetMapping(value = "/computer")
    public String checkTriangle() throws IOException {
        return computerSales.Run();
    }
}
