package com.example.demo.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;

@Service
public class Sell {

    public String Calculate(float ann,float holidays,float income){
        float money=0;
        if((ann<0)||(holidays<0)||(holidays>365)||(income)<0||(income)>1){
            return "输入错误";
        }
        else if((ann>2000000)&&(holidays<=10)){
            if(income>=0.6)
                money=ann/7;
            else
                money=0;
        }else{
            if(income<=0.85)
                money=ann/6;
            else
                money=ann/5;
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String str=nf.format(money);
        return str;
    }

    public String testXlsx(){
        File file=new File(System.getProperty("user.dir")+"/src/main/resources/data/selltest.xlsx");
        String str="";
        if(!file.exists()){
            str="文件不存在";
            return str;
        }
        FileInputStream fis = null;
        Workbook workBook = null;
        try {
            fis = new FileInputStream(file);
            workBook = new XSSFWorkbook(fis);
            str=dealWorkBook(workBook);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{ //关流
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(workBook != null){
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    public String dealWorkBook(Workbook workBook){
        Sheet sheet = workBook.getSheetAt(0); // 获取第一个sheet
        StringBuilder output=new StringBuilder();
        int firstRowNum=sheet.getFirstRowNum();
        int rowEnd=sheet.getPhysicalNumberOfRows();
        for (int rowNum=firstRowNum;rowNum<rowEnd;rowNum++){
            Row row=sheet.getRow(rowNum);
            if(row==null){
                continue;
            }
            Cell firstcell=row.getCell(0);
            //System.out.println(firstcell.toString());
            if(firstcell.toString().equals("语句覆盖"))
                output.append("语句覆盖：\n");
            if(firstcell.toString().equals("判断覆盖"))
                output.append("判断覆盖：\n");
            if(firstcell.toString().equals("条件覆盖"))
                output.append("条件覆盖：\n");
            if(firstcell.toString().equals("判定条件覆盖"))
                output.append("判定条件覆盖：\n");
            if(firstcell.toString().equals("条件组合覆盖"))
                output.append("条件组合覆盖：\n");
            switch (firstcell.getCellType()){
                case NUMERIC:
                    float ann=Float.parseFloat(row.getCell(1).toString());
                    float holidays=Float.parseFloat(row.getCell(2).toString());
                    float income=Float.parseFloat(row.getCell(3).toString());
                    String expvalue=row.getCell(4).toString();
                    String money=Calculate(ann,holidays,income);
                    String str="年成交额："+ann+"；请假天数："+holidays+"；现金到账："+income+"；预期结果："+expvalue+"；测试结果："+money;
                    output.append(str);
                    output.append("\n");
                    break;
                default:
                    break;
            }
        }
        return output.toString();
    }


}
