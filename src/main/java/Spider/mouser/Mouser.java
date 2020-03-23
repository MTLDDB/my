package Spider.mouser;

import Spider.Fetch;
import com.alibaba.fastjson.JSON;
import entity.Category;
import mapper.MouserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.util.HSSFColor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Session;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Mouser {
    Logger logger = LoggerFactory.getLogger(Mouser.class);
    private SqlSessionFactory sqlSessionFactory;
    private MouserMapper mouserMapper;

    public void fetch() throws IOException {
        mouserMapper = Session.getMapper(MouserMapper.class);//返回mapper接口的代理对象，
        String uri = "https://www.mouser.com/Electronic-Components/";
        Document document = null;
        Element div = null;
        try {
            document = Fetch.getcontent(uri);
            System.out.println(document);
            div = document.getElementById("tblSplitCategories");
//            Elements divFirsts = div.select("div[class=panel light-grey-panel]");
//            Map<String, List<String>> listMap = new HashMap<>();
//            for (Element divFirst : divFirsts) {
//                Elements div2 = divFirst.select("a[id=lnkTopLvlCategory]");
//                String first = div2.select("h2").text();
//                if (first.equals("Semiconductors")) {
////                    Elements divSecondLis_t = divFirst.select("div[class=col-xs-12 sub-category-section]");
////                    for (Element divSecondLi_t : divSecondLis_t) {
////                        String second = divSecondLi_t.select("a[id=lnkSubCategory]").text();
////                        Elements divThirdLis_t = divSecondLi_t.select("li");
////                        for (Element divThirdLi_t : divThirdLis_t) {
////                            String third = divThirdLi_t.select("a[id=lnkCategory]").text();
////                            String thirdUri = "https://www.mouser.com" + divThirdLi_t.select("a").attr("href");
////                            List<String> levelStr = new ArrayList<>();
////                            levelStr.add(0, first);
////                            levelStr.add(1, second);
////                            levelStr.add(2, third);
////                            listMap.put(thirdUri,levelStr);
////                        }
////                    }
//                } else {
//                    Elements divSecondLis = divFirst.select("li");
//                    for (Element divSecondLi : divSecondLis) {
//                        String second = divSecondLi.select("a[id=lnkCategory]").text();
//                        String secondUri = "https://www.mouser.com" + divSecondLi.select("a[id=lnkCategory]").attr("href");
//                        List<String> levelStr = new ArrayList<>();
//                        levelStr.add(0, first);
//                        levelStr.add(1, second);
//                        listMap.put(secondUri,levelStr);
//                    }
//               }
//           }
//            if(listMap.size()>0){
//                for(Map.Entry<String,List<String>> entry:listMap.entrySet()){
//                   // System.out.println(JSON.toJSONString(entry.getValue()));
//                   // if(JSON.toJSONString(entry.getValue()).contains("Transistors (36,917)")){
//                      //  System.out.println(entry.getKey());
//                        Next.get().getDoc(entry.getKey(),entry.getValue());
//                   // }
//                }
//            }
            System.out.println("done");
        } catch (Exception e) {
            logger.info("出错" + e);
        }
    }
}
