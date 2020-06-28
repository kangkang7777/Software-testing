package com.example.demo.controller;

import com.example.demo.service.FileReader;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Resource
    private FileReader fileReader;


    @GetMapping(value = "/date/type1")
    public String testNextDate1() throws IOException {
        return fileReader.readNextDateFile1();
    }

    @GetMapping(value = "/date/type2")
    public String testNextDate2() throws IOException {
        return fileReader.readNextDateFile2();
    }

    @GetMapping(value = "/triangle/type1")
    public String testTriangle1() throws IOException {
        return fileReader.readTriangleFile1();
    }

    @GetMapping(value = "/triangle/type2")
    public String testTriangle2() throws IOException {
        return fileReader.readTriangleFile2();
    }
}
