package Spider;

import com.alibaba.fastjson.JSON;
import entity.Category;
import mapper.DigikeyMapper;
import mapper.IpMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Digikey {
    private SqlSessionFactory sqlSessionFactory;
    public void fetch() throws IOException {
        String resource = "configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);//ture为自动提交事务
        DigikeyMapper digikeyMapper = session.getMapper(DigikeyMapper.class);//返回mapper接口的代理对象，
        String uri="https://www.digikey.com/products/en";
        Fetch fetch=new Fetch();
        Document document=null;
        Element div=null;
        while (div==null||document==null) {
            document = fetch.getcontent(uri);
            div=document.getElementById("productIndexList");
        }
        Elements h2=div.select(".catfiltertopitem");
        Elements ul=div.select("ul[class=catfiltersub]");
        int text=h2.size();
        System.out.println(text+"--"+ul.size());
        Map<String, List<String>> map=new HashMap<String, List<String>>();
        Boolean flag=h2.size()==ul.size();
        System.out.println(flag+":"+text);
        if(flag){
            List<Category> categoryList=new ArrayList<Category>();
            for(int i=0;i<h2.size();i++){
                String second=h2.get(i).text().trim();
                if(!"Connectors, Interconnects".equals(second)){
                    Elements thirds=ul.get(i).select("li");
                    for(Element third:thirds){
                        Category category=new Category();
                        Elements a=third.select("a");
                        String thirdString=a.text();
                        String url="https://www.digikey.com"+a.attr("href");
                        String item=third.ownText();
                        category.setSecond(second);
                        category.setThred(thirdString);
                        category.setItem_mum(item);
                        category.setUrl(url);
                        category.setObjectid(UUID.nameUUIDFromBytes(url.getBytes()).toString().replace("-",""));
                       // categoryList.add(category);
                        digikeyMapper.saveCategory(category);
                    }
                }else {
                    String first="Connectors, Interconnects";
                    String uri2="https://www.digikey.com/en/resources/connectors/index";
                    Document document2=null;
                    while (document2==null) {
                        document2 = fetch.getcontent(uri2);
                    }
                    Elements div1=document2.getElementsByClass("richtextcontent");
                    Elements div2S=div1.select("div[class=connectorFamily]");
                    for(Element div2:div2S){
                        String second2=div2.select("h2").text();
                        String href=div2.select("a").attr("href").trim();
                        String url=null;
                        if(href.contains("product")){
                            url="https://www.digikey.com"+href;
                        }else {
                            url="https://www.digikey.com/en/resources/connectors/"+href;
                        }
                        System.out.println(url);
                        Document document3=null;
                        while(document3==null) {
                            document3 = fetch.getcontent(url);
                        }
                        int  type=0;
                        Elements div3s=document3.select("div[cookie-tracking=ref_page_event=Family%20Link]");
                        if(div3s.size()>0){
                            type=1;
                        }
                        Elements div4s=document3.select("div[id=productIndexList]");
                        if(div4s.size()>0){
                            type=2;
                        }
                        Category category = new Category();
                        switch (type){
                            case 0:
                                Category category1 = new Category();
                                category1.setUrl(url);
                                category1.setObjectid(UUID.nameUUIDFromBytes(url.getBytes()).toString().replace("-", ""));
                                category1.setFirst(first);
                                category1.setSecond(second2);
                                category1.setItem_mum(document3.select("span[id=matching-records-count]").text());
                                digikeyMapper.saveCategory(category1);
                                break;
                            case 1:
                                for (Element div3 : div3s) {
                                    Elements lis = div3.select("li");
                                    for (Element li : lis) {
                                        Elements a = li.select("a");
                                        String item = li.select("span").html();
                                        String itemreal=null;
                                        //System.out.println(item);
                                        String thirdString3 = a.text().replace(item,"");
                                        //System.out.println(thirdString3);
                                        String url3 = "https://www.digikey.com" + a.attr("href");
                                        category.setFirst(first);
                                        category.setSecond(second2);
                                        category.setThred(thirdString3);
                                        category.setItem_mum(itemreal);
                                        category.setUrl(url3);
                                        category.setObjectid(UUID.nameUUIDFromBytes(url3.getBytes()).toString().replace("-", ""));
                                        digikeyMapper.saveCategory(category);
                                    }
                                }
                                break;
                            case 2:
                                Elements li4s=div4s.select("ul[class=catfiltersub]").select("li");
                                for(Element li4:li4s){
                                    Elements a = li4.select("a");
                                    String thirdString3 = a.text();
                                    //System.out.println(thirdString3);
                                    String url4 = "https://www.digikey.com" + a.attr("href");
                                    String item = li4.ownText();
                                    category.setFirst(first);
                                    category.setSecond(second2);
                                    category.setThred(thirdString3);
                                    category.setItem_mum(item);
                                    category.setUrl(url4);
                                    category.setObjectid(UUID.nameUUIDFromBytes(url4.getBytes()).toString().replace("-", ""));
                                    digikeyMapper.saveCategory(category);
                                }
                                break;
                             default:break;
                        }
                    }

                }
            }
        }
    }

}
