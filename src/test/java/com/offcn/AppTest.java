package com.offcn;

import com.alibaba.fastjson.JSON;
import com.ujiuye.sys.bean.Employee;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppTest {

    @Test
    public void test04(){
        Employee employee = new Employee();
        employee.setEname("张三");
        employee.setEage(18);
        employee.setEsex("true");
        Employee employee2 = new Employee();
        employee2.setEname("李四");
        employee2.setEage(38);
        employee2.setEsex("false");

        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);

        String s = JSON.toJSONString(employee);
        System.out.println(s);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);

        Employee employee1 = JSON.parseObject(s, Employee.class);
        System.out.println(employee1);

        List<Employee> list1 = JSON.parseArray(s1, Employee.class);
        System.out.println(list1);
    }

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("beans-quartz.xml");
    }

    @Test
    public void test02(){
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new File("C:\\Users\\Administrator\\Desktop\\book.xlsx"));
            Sheet sheet = wb.getSheetAt(0);
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value = parseExcelValueToString(cell);
                    if(i>0 && j==0){
                        value = value.substring(0,value.indexOf("."));
                    }
                    System.out.print(value+"    ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String parseExcelValueToString(Cell cell) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String result = "";

        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                result = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                }else {
                    BigDecimal decimal = new BigDecimal(cell.getNumericCellValue()+"");
                    result = decimal.toPlainString();
                }
                break;
            default:
                result = "";
        }
        return result;
    }

    @Test
    public void test01(){
        //1.创建excel表格
        Workbook wb = new XSSFWorkbook();
        //2.在表格里面创建sheet对象
        Sheet sheet1 = wb.createSheet("sheet one");
        Sheet sheet2 = wb.createSheet("sheet two");
        Sheet sheet3 = wb.createSheet("sheet three");
        Sheet sheet4 = wb.createSheet("sheet four");
        //3.创建第一行
        Row row = sheet1.createRow(0);
        //4.创建第一行的第一列
        Cell cell0 = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);
        //5.给单元格设置值
        cell0.setCellValue("id");
        cell1.setCellValue("序号");
        cell2.setCellValue("联系人");

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\cust.xlsx"));
            wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
