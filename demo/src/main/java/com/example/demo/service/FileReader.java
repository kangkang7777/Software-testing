package com.example.demo.service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileReader {
    @Resource
    private Calendar calendar;
    @Resource
    private CheckTriangle checkTriangle;

    public List<String> readNextDateFile1() throws IOException {
        List<String> result = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/nextDate.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        for (int i = 2; i < 21; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            int year = (int)xssfRow.getCell(1).getNumericCellValue();
            int month = (int)xssfRow.getCell(2).getNumericCellValue();
            int day = (int)xssfRow.getCell(3).getNumericCellValue();
            result.add(calendar.getNextDate(year, month, day));
            System.out.println(calendar.getNextDate(year, month, day));
        }
        return result;
    }

    public List<String> readNextDateFile2() throws IOException {
        List<String> result = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/nextDate.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        for (int i = 24; i < 56; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            int year = (int)xssfRow.getCell(1).getNumericCellValue();
            int month = (int)xssfRow.getCell(2).getNumericCellValue();
            int day = (int)xssfRow.getCell(3).getNumericCellValue();
            result.add(calendar.getNextDate(year, month, day));
            System.out.println(calendar.getNextDate(year, month, day));
        }
        return result;
    }

    public List<String> readTriangleFile1() throws IOException {
        List<String> result = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/triangle.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        for (int i = 2; i < 21; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            double a = xssfRow.getCell(0).getNumericCellValue();
            double b = xssfRow.getCell(1).getNumericCellValue();
            double c = xssfRow.getCell(2).getNumericCellValue();
            result.add(checkTriangle.checkTriangle(a, b, c));
            System.out.println(checkTriangle.checkTriangle(a, b, c));
        }
        return result;
    }

    public List<String> readTriangleFile2() throws IOException {
        List<String> result = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/triangle.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        for (int i = 24; i < 37; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            double a = xssfRow.getCell(1).getNumericCellValue();
            double b = xssfRow.getCell(2).getNumericCellValue();
            double c = xssfRow.getCell(3).getNumericCellValue();
            result.add(checkTriangle.checkTriangle(a, b, c));
            System.out.println(checkTriangle.checkTriangle(a, b, c));
        }
        return result;
    }
}
