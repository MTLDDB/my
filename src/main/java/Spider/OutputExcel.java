package Spider;

import com.alibaba.fastjson.JSON;
import entity.Category;
import entity.Excel;
import mapper.DigikeyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OutputExcel {
    private SqlSessionFactory sqlSessionFactory;
    public static void main(String[] args) throws IOException {
        OutputExcel outputExcel=new OutputExcel();
        outputExcel.CreatExcel();
    }
    void CreatExcel() throws IOException {
        String resource = "configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);//ture为自动提交事务
        DigikeyMapper digikeyMapper = session.getMapper(DigikeyMapper.class);//返回mapper接口的代理对象，


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
        String strArray[]={"URL","Domain","Priority","StockUpdate","FilterUpdate"};

        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = rowTitle.createCell(i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        List<Category> urlList=digikeyMapper.getUrlList();
        System.out.println(urlList.get(2).getUrl());
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到,list中字符串的顺序必须和数组strArray中的顺序一致
        for(int k=0;k<urlList.size();k++){
            HSSFRow row = sheet.createRow(k+1);
            Excel excel=new Excel();
            excel.setUrl(urlList.get(k).getUrl());
            List<String> list=new ArrayList();
            list.add(excel.getUrl());
            list.add(excel.getDomain());
            list.add(String.valueOf(excel.getPriority()));
            list.add(String.valueOf(excel.getStockUpdate()));
            list.add(String.valueOf(excel.getFilterUpdate()));
            for (int j = 0; j < strArray.length; j++) {
                row.createCell(j).setCellValue(list.get(j));
            }

            // 第六步，将文件存到指定位置
            try {
                FileOutputStream fout = new FileOutputStream("C:\\Users\\PC\\Desktop\\Members.xls");
                wb.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        }
    }

