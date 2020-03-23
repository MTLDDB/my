package util;

import entity.Excel;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetFileString {
    public static void main(String[] args) {
        String index;

        Map<String,List<String>> map=new HashMap<>();
        for(int i=1;i<31;i++){
            if(i<10){
                index="0"+i;
            }else {
                index=String.valueOf(i);
            }
            String path="C:\\Users\\PC\\Desktop\\log\\localhost_access_log.2019-11-"+index+".txt";
            List<String> list = getString(path);
            List<String> stringList=new ArrayList<>();
            for (int k = 0; k < list.size(); k++) {
                String[] string = list.get(k).split(" ");
                stringList.add(string[6]);
            }
            map.put(index,stringList);
        }
       // System.out.println(stringList.size());
        getExcel("pdf_url",map);
    }
    public static void getExcel(String excelName, Map<String,List<String>> map) {
        //创建工作薄
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow rowTitle = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        String strArray[] = {"URL","date"};

        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = rowTitle.createCell(i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        int k=0;
        for (Map.Entry<String,List<String>> entry:map.entrySet()) {
            for(int i=0;i<entry.getValue().size();i++){
                HSSFRow row = sheet.createRow(k + 1);
                k++;
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue().get(i));
            }

//            Excel excel = new Excel();
//            excel.setUrl(urlList.get(k));
//            excel.setDomain("verical.com");
//            List<String> list = new ArrayList();
//            list.add(excel.getUrl());
//            list.add(excel.getDomain());
//            list.add(String.valueOf(excel.getPriority()));
//            list.add(String.valueOf(excel.getStockUpdate()));
//            list.add(String.valueOf(excel.getFilterUpdate()));
//            for (int j = 0; j < strArray.length; j++) {
//                row.createCell(j).setCellValue(urlList.get(j));
//            }

            // 第六步，将文件存到指定位置
            try {
                FileOutputStream fout = new FileOutputStream("C:\\Users\\PC\\Desktop\\PDF_excel\\" + excelName + ".xls");
                wb.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static List<String> getString(String path) {
        //"C:\\Users\\PC\\Desktop\\log\\localhost_access_log.2019-11-02.txt"
        File file = new File(path);
        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linetxt = null;
            //result用来存储文件内容
            StringBuilder result = new StringBuilder();
            //按使用readLine方法，一次读一行
            while ((linetxt = bufferedReader.readLine()) != null) {
                String[] re = linetxt.split("\t");
                for (int i = 0; i < re.length; i++) {
                    if (re[i].contains(".pdf")) {
                        //   System.out.println(re[i]);
                        list.add(re[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
