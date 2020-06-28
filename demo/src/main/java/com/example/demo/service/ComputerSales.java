package com.example.demo.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 *电脑销售系统，主机（25￥单位价格，每月最多销售的数量为70），显示器（30￥单位价格，每月最多销售数量为80），外设（45￥单位价格，每月最多销售的数量为90）；
 *每个销售员每月至少销售一台完整的机器，当系统的主机这个变量接受到-1值的时候，系统自动统计该销售员本月的销售总额。
 *当销售额小于等于1000（包括1000）按照10%提佣金，当销售额在1000-1800之间（包括1800）的时候按照15%提佣金，当销售额大于1800时按照20%提佣金。
 */
@Service
public class ComputerSales {

    final int hostPrice = 25,hostMaxNum = 70;
    final int screenPrice = 30,screenMaxNum = 80;
    final int controllerPrice = 45,controllerMaxNum = 90;
    int totalHost = 0,totalScreen = 0,totalController =0;
    int totalSales = 0;
    double commission = 0;

    /*
     *运行
     */
    public String Run() throws IOException {
        return addData();
    }

    /*
     *读取EXCEL表格数据
     */
    public String addData() throws IOException {
        StringBuilder output = new StringBuilder();
        //获取整个excel
        XSSFWorkbook hb=new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/data/computer.xlsx")));
        //获取第一个表单sheet
        XSSFSheet sheet=hb.getSheetAt(0);
        //获取最后一行
        int lastRow = sheet.getLastRowNum();
        for(int i = 1;i<lastRow+1;i++) {
            Row row = sheet.getRow(i);
            String temp = "第"+ i +"月:" + getCommission((int)(row.getCell(0).getNumericCellValue()),(int)(row.getCell(1).getNumericCellValue()),(int)(row.getCell(2).getNumericCellValue()))+"\n";
            output.append(temp);
            System.out.println(temp);
        }
        return output.toString();
    }

    /*
     *添加销量&获取佣金额
     */
    public String getCommission(int hostNum,int screenNum,int controllerNum)
    {
        System.out.println("您本月的销售情况为：主机:"+hostNum+"台，显示器:"+screenNum+"台，外设:"+controllerNum+"件");
        //统计销售总额
//        if(hostNum==-1)
//        {
            totalHost = hostNum;
            totalScreen = screenNum;
            totalController = controllerNum;
            if (!isOutputLegal().equals(""))
                return isOutputLegal();
            totalSales = totalHost*hostPrice+totalScreen*screenPrice+totalController*controllerPrice;
            if(totalSales>1800)
            {
                commission = (totalSales -1800)*0.2 +800 * 0.15 + 1000*0.1;
            }
            else if(totalSales>1000)
            {
                commission = (totalSales -1000)*0.15 + 1000*0.1;
            }
            else
            {
                commission = totalSales *0.1;
            }
            return "您的佣金为：" + (int)commission +"元";
//        }
//        else
//        {//添加销量
//            if(!isInputLegal(hostNum, screenNum, controllerNum, ID).equals(""))
//                return isInputLegal(hostNum, screenNum, controllerNum, ID);
//            totalScreen += screenNum;
//            totalController += controllerNum;
//            totalHost += hostNum;
//            return "销售额添加成功";
//        }

    }

    /*
     *判断输入数据是否合法
     */
    public String isInputLegal(int hostNum,int screenNum,int controllerNum,int ID)
    {

        if(hostNum>=hostMaxNum)
            return "在第" + ID + "数据输入中，您单次销售的主机数量已经达到上限";
        if(screenNum>=screenMaxNum)
            return "在第" + ID + "数据输入中，您单次销售的屏幕数量已经达到上限";
        if(controllerNum>=controllerMaxNum)
            return "在第" + ID + "数据输入中，您单次销售的外设数量已经达到上限";
        if(hostNum<-1)
            return "在第" + ID + "数据输入中，您单次销售的主机数量不合法";
        if(screenNum<0)
            return "在第" + ID + "数据输入中，您单次销售的屏幕数量不合法";
        if(controllerNum<0)
            return "在第" + ID + "数据输入中，您单次销售的外设数量不合法";
        return "";
    }

    /*
     *判断最终数据是否合法
     */
    public String isOutputLegal()
    {
        int sum = totalHost + totalScreen + totalController;
        if(totalHost>hostMaxNum)
            return "您销售的主机数量超过上限";
        if(totalScreen>screenMaxNum)
            return "您销售的屏幕数量超过上限";
        if(totalController>controllerMaxNum)
            return "您销售的外设数量超过上限";
        if(totalHost<0)
            return "您销售的主机数量不合法";
        if(totalScreen<0)
            return "您销售的屏幕数量不合法";
        if(totalController<0)
            return "您销售的外设数量不合法";
        if(totalHost<1||totalScreen<1||totalController<1)
            return "您销售的完整的主机没有超过一台";
        return "";
    }
}
