package Spider.verical;

import Spider.Fetch;
import Spider.avnet.ThreadAvnet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mapper.AvnetMapper;
import mapper.VericalMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Verical {
    private VericalMapper vericalMapper;
    Logger logger = LoggerFactory.getLogger(Verical.class);

    public void fetch() throws IOException {
        vericalMapper = Session.getMapper(VericalMapper.class);//返回mapper接口的代理对象，
        String uri = "https://www.verical.com/static/generated/master.json?format=json";//?catalogId=10001&langId=-1&storeId=715839038&krypto=SXnC930b63zqebcvknl0gA5AJy3vqfbBc3yTODdBAlZ2%2FU%2FraDkqIAdEhpwTwIOT8m%2FEKqaLqObQrQ8QbTzFBQ%3D%3D";
        Fetch fetch = new Fetch();
        Document document = null;
        try {
            document = fetch.getcontent(uri);
            String doc=document.select("body").text();
            JSONObject jsonObject=JSONObject.parseObject(doc,JSONObject.class);
            String categoryTree=jsonObject.getString("categoryTree");
            JSONObject jsonObject1=JSONObject.parseObject(categoryTree,JSONObject.class);
            String categories=jsonObject1.getString("categories");
            JSONArray categoriesArr=JSONArray.parseArray(categories);
            ThreadVerical[] threadVericals=new ThreadVerical[categoriesArr.size()];
            for(int i=0;i<categoriesArr.size();i++){
                String temp = JSON.toJSONString(categoriesArr.get(i));
                threadVericals[i]=new ThreadVerical(temp);
                Thread thread=new Thread(threadVericals[i]);
                thread.start();
                }
            System.out.println("done");
        } catch (Exception e) {
            logger.info("出错" + e);
        }

    }
    String  getNext(String ca){
        JSONObject jsonObject=JSONObject.parseObject(ca,JSONObject.class);
        String categories=jsonObject.getString("categories");
        JSONArray categoriesArr=JSONArray.parseArray(categories);
        String firstCates=null;
        String next=null;
        for(int i=0;i<categoriesArr.size();i++){
            String temp = JSON.toJSONString(categoriesArr.get(i));
            JSONObject jsonObject2=JSONObject.parseObject(temp,JSONObject.class);
            firstCates=jsonObject2.getString("name");
            String secondCate=jsonObject2.getString("categories");
            System.out.println(firstCates);
            if(secondCate!="[]"){
                next=secondCate;
            }
        }
        return next;
    }
}
