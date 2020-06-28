package com.example.demo.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

@Service
public class PhoneBill {
    public String getAllBills()throws IOException {
        return getInput();
    }

    public String getAllBillsByEc()throws IOException{
        return getEcInput();
    }

    private String getInput() throws IOException {
        StringBuilder output = new StringBuilder();
        //获取整个excel
        XSSFWorkbook hb=new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/data/phoneBill.xlsx")));
        //获取第一个表单sheet
        XSSFSheet sheet=hb.getSheetAt(0);
        //获取最后一行
        int lastRow = sheet.getLastRowNum();
        for(int i = 1;i<lastRow+1;i++) {
            Row row = sheet.getRow(i);
            String temp = "第"+ i +"个用例测试结果:" + getBill((double) (row.getCell(0).getNumericCellValue()),(double) (row.getCell(1).getNumericCellValue()),(int)(row.getCell(2).getNumericCellValue()),(int)(row.getCell(3).getNumericCellValue()))+"\n";
            output.append(temp);
            System.out.println(temp);
        }
        return output.toString();
    }

    private String getEcInput() throws IOException {
        StringBuilder output = new StringBuilder();
        //获取整个excel
        XSSFWorkbook hb=new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/data/phoneBill_ec.xlsx")));
        //获取第一个表单sheet
        XSSFSheet sheet=hb.getSheetAt(0);
        //获取最后一行
        int lastRow = sheet.getLastRowNum();
        for(int i = 1;i<lastRow+1;i++) {
            Row row = sheet.getRow(i);
            String temp = "第"+ i +"个用例测试结果:" + getBill((double) (row.getCell(1).getNumericCellValue()),(double) (row.getCell(2).getNumericCellValue()),(int)(row.getCell(3).getNumericCellValue()),(int)(row.getCell(4).getNumericCellValue()))+"\n";
            output.append(temp);
            System.out.println(temp);
        }
        return output.toString();
    }




    public String getBill(double balance, double lastYearLeftMoney, int yearOweTime, int talkTime){

        String errOutput = "";
        OnlinePay onlinePay = new OnlinePay();
        DecimalFormat df = new DecimalFormat( "0.0000");
        if(balance < 0)errOutput += "账户余额不能为负\n";
        else if(!onlinePay.addBalance(balance))errOutput += "余额上限为99,999,999,999元\n";
        if(lastYearLeftMoney < 0)errOutput +=  "跨年度未交金额不能为负\n";
        else if(lastYearLeftMoney > 100000) errOutput += "跨年度未交金额上限为100,000元\n";
        if(yearOweTime < 0 || yearOweTime > 11)errOutput += "未按时缴费次数范围为0-11\n";
        if(talkTime < 0)errOutput += "月通话时间不能为负";
        else if(talkTime > 44640)errOutput += "月通话时间上限为44640分钟";
        if(!errOutput.equals(""))return errOutput;

        double discount = 0;
        if(talkTime <=60 && yearOweTime <= 1)discount = 0.01;
        if(talkTime > 60 && talkTime <=120 && yearOweTime <= 2)discount = 0.015;
        if(talkTime > 120 && talkTime <=180 && yearOweTime <= 3)discount = 0.02;
        if(talkTime > 180 && talkTime <=300 && yearOweTime <= 3)discount = 0.025;
        if(talkTime > 300 && yearOweTime <= 6)discount = 0.03;
        double talkFee = (talkTime * minuteFee * (1 - discount));
        double lateFee = lastYearLeftMoney * 0.05;
        double totalFee = talkFee + lateFee + monthlyFee;


        if(!onlinePay.withdrawBalance(totalFee))return "账户余额不足";
        return "支付成功！您的账单如下：\n"+
                "月租费：" + monthlyFee + " 元\n"+
                "通话费: " + df.format(talkFee) + " 元(折扣 " + discount * 100 + "%) \n"+
                "滞纳金: " + df.format(lateFee) + " 元\n"+
                "总费用: " + df.format(totalFee) + "元\n\n"+
                "账户余额: " + df.format(onlinePay.getBalance()) + "元";


    }

    final private int monthlyFee = 25;
    final private double minuteFee =  0.15;
}
