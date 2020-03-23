package Spider.avnet;

import Spider.Fetch;
import Spider.avnet.ThreadAvnet;
import Spider.mouser.Mouser;
import mapper.AvnetMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Session;

import java.io.IOException;

public class Avnet {
    Logger logger = LoggerFactory.getLogger(Mouser.class);
    private AvnetMapper avnetMapper;

    public void fetch() throws IOException {
        avnetMapper = Session.getMapper(AvnetMapper.class);//返回mapper接口的代理对象，
        String uri = "https://www.avnet.com/shop/AllProducts?countryId=apac&catalogId=10001&langId=-1&storeId=715839038&deflangId=-1";//?catalogId=10001&langId=-1&storeId=715839038&krypto=SXnC930b63zqebcvknl0gA5AJy3vqfbBc3yTODdBAlZ2%2FU%2FraDkqIAdEhpwTwIOT8m%2FEKqaLqObQrQ8QbTzFBQ%3D%3D";
        Fetch fetch = new Fetch();
        Document document = null;
        try {
            document = fetch.getcontent(uri);
            //System.out.println(document);
            Element div1=document.getElementById("div_1");
            Element div2=document.getElementById("div_2");
            Element div3=document.getElementById("div_3");
            Element div4=document.getElementById("div_4");
            Element div5=document.getElementById("div_5");
            Element div6=document.getElementById("div_6");
            if(div1!=null){
               // test(div1);
               // test(div2);
//                test(div3);
             //   test(div4);
//                test(div5);
                test(div6);
            }
            System.out.println("done");
        } catch (Exception e) {
            logger.info("出错" + e);
        }
    }
    void test(Element div1s){
        Elements div2s = div1s.select("div[class=category]");
        ThreadAvnet[] threadAvnets=new ThreadAvnet[div2s.size()];
        for (int k = 0; k < div2s.size(); k++) {
            threadAvnets[k]=new ThreadAvnet(div2s.get(k));
            Thread thread=new Thread(threadAvnets[k]);
            thread.start();
            //ThreadVerical threadAvnet=new ThreadVerical(div2s.get(k));
        }
    }
}
