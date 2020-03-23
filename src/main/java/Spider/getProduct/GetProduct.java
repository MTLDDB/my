package Spider.getProduct;


import Spider.digikey.Digikey;
import entity.Detailedinfo;
import entity.SolrBean;
import mapper.DigikeyMapper;

import org.apache.poi.hssf.usermodel.*;
import util.QuerySolr;
import util.Session;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetProduct {

    private static DigikeyMapper digikeyMapper;
    List<Detailedinfo> products=new ArrayList<>();
////        ids.add("3eb3b7e2b1ed3d47b2f8aae412276bb4");
////        ids.add("3efec03a601c3969b53b395676f1b962");
////        List<Price> products=testService.getPrice(ids);
//        for(String id:ids){
//            System.out.println(id);
//            Detailedinfo p=new Detailedinfo();
//            String price=testService.getPrice(id);
//            System.out.println(price);
//            p.setPrice_json(price);
//            p.setObjectid(id);
//            products.add(p);
//        }
public  void getProduct_db(){
    List<String> ids= getString("C:\\\\Users\\\\PC\\\\Desktop\\\\taiyo\\\\txt.txt");
    digikeyMapper= Session.getMapper(DigikeyMapper.class);

    List<Detailedinfo> products=new ArrayList<>();
        for(String id:ids){
            Detailedinfo p=digikeyMapper.getProductById(id);
            p.setPrice_json(digikeyMapper.getPrice(id));
           p.setAttr_json(digikeyMapper.getAttr(id));
            products.add(p);
        }
    getExcel("Taiyo_Yuden_other",products);
}
    public  void getProduct_solr(){
        QuerySolr query=new QuerySolr();
        digikeyMapper= Session.getMapper(DigikeyMapper.class);
        List<Detailedinfo> productList = digikeyMapper.getMfr("Taiyo Yuden");
        List<Detailedinfo> products=new ArrayList<>();
        for(Detailedinfo p:productList){
            SolrBean solrBean=query.querySolr(p.getObjectid());
            if(solrBean==null)continue;
            String price=solrBean.getPrice();
            String attr=solrBean.getAttr();
            p.setAttr_json(attr);
            p.setPrice_json(price);
            products.add(p);
        }
        getExcel("Taiyo_Yuden",products);
    }

    public static void getExcel(String excelName, List<Detailedinfo> list) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        HSSFRow rowTitle = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        String strArray[] = {"mpn", "webmpn", "description", "detailed description", "spq", "category_first", "category_second", "leadtime", "img", "pdfs", "mfr", "packaging", "attr_json","stock","price"};
        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = rowTitle.createCell(i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            List<String> string = new ArrayList<>();
            string.add(0, list.get(i).getMpn());
            string.add(1, list.get(i).getWebmpn());
            string.add(2, list.get(i).getDescript());
            string.add(3, list.get(i).getDetailed());
            string.add(4, list.get(i).getSpq());
            string.add(5, list.get(i).getSecondary_classification());
            string.add(6, list.get(i).getThreelevel_classification());
            string.add(7, list.get(i).getLeadtime());
            string.add(8, list.get(i).getImg());
            string.add(9, list.get(i).getPdfs());
            string.add(10, list.get(i).getMfr());
            string.add(11, list.get(i).getPackaging());
            string.add(12, list.get(i).getAttr_json());
            string.add(13, String.valueOf(list.get(i).getStock()));
            string.add(14, list.get(i).getPrice_json());
            for (int j = 0; j < strArray.length; j++) {
                row.createCell(j).setCellValue(string.get(j));
            }
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\PC\\Desktop\\taiyo\\" + excelName + ".xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getString(String path) {
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
                    if (re[i].contains("error:")) {
                        String id=re[i].replace("error:","");
                       System.out.println(id);
                    list.add(id);
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
