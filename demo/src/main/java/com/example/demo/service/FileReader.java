package com.example.demo.service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FileReader {
    @Resource
    private Calendar calendar;
    @Resource
    private CheckTriangle checkTriangle;

    public String readNextDateFile1() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/nextDate.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < 21; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            int year = (int)xssfRow.getCell(1).getNumericCellValue();
            int month = (int)xssfRow.getCell(2).getNumericCellValue();
            int day = (int)xssfRow.getCell(3).getNumericCellValue();
            String str = calendar.getNextDate(year, month, day) + "\n";
            stringBuilder.append(str);
            //System.out.println(calendar.getNextDate(year, month, day));
        }
        return stringBuilder.toString();
    }

    public String readNextDateFile2() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/nextDate.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 24; i < 56; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            int year = (int)xssfRow.getCell(1).getNumericCellValue();
            int month = (int)xssfRow.getCell(2).getNumericCellValue();
            int day = (int)xssfRow.getCell(3).getNumericCellValue();
            String str = calendar.getNextDate(year, month, day) + "\n";
            stringBuilder.append(str);
            //System.out.println(calendar.getNextDate(year, month, day));
        }
        return stringBuilder.toString();
    }

    public String readTriangleFile1() throws IOException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/triangle.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < 21; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            double a = xssfRow.getCell(0).getNumericCellValue();
            double b = xssfRow.getCell(1).getNumericCellValue();
            double c = xssfRow.getCell(2).getNumericCellValue();
            String str = checkTriangle.checkTriangle(a, b, c) + "\n";
            stringBuilder.append(str);
            //System.out.println(checkTriangle.checkTriangle(a, b, c));
        }
        return stringBuilder.toString();
    }

    public String readTriangleFile2() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data/triangle.xlsx")));
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 24; i < 37; i++){
            XSSFRow xssfRow = xssfSheet.getRow(i);
            double a = xssfRow.getCell(1).getNumericCellValue();
            double b = xssfRow.getCell(2).getNumericCellValue();
            double c = xssfRow.getCell(3).getNumericCellValue();
            String str = checkTriangle.checkTriangle(a, b, c) + "\n";
            stringBuilder.append(str);
            //System.out.println(checkTriangle.checkTriangle(a, b, c));
        }
        return stringBuilder.toString();
    }
}
