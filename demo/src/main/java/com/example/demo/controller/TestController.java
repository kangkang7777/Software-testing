package com.example.demo.controller;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private ComputerSales computerSales;

    @Autowired
    private Sell sell;

    @Autowired
    private PhoneBill phoneBill;


    @Resource
    private FileReader fileReader;

    @GetMapping(value = "/computer")
    public String checkTriangle() throws IOException {
        return computerSales.Run();
    }

    @GetMapping(value = "/sell")
    public String whiteBoxTest() throws IOException{
        return sell.testXlsx();
    }

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

    @GetMapping(value = "phonebill")
    public String getPhoneBill() throws IOException {
        return phoneBill.getAllBills();
    }

    @GetMapping(value = "phonebill_ec")
    public String getPhoneBillByEc() throws IOException {
        return phoneBill.getAllBillsByEc();
    }
}
