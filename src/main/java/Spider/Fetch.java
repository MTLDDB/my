package Spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fetch {

    static Logger logger = LoggerFactory.getLogger(Fetch.class);

    public static Document getcontent(String url) throws IOException {
//       File file=new File("C:\\Users\\PC\\Desktop\\ip.txt");
//       BufferedReader bufferedReader =new BufferedReader(new FileReader(file));
//       String linetxt = null;
//       //result用来存储文件内容
//       StringBuilder result = new StringBuilder();
//       //按使用readLine方法，一次读一行
//       List<Ip> list=new ArrayList<>();
//       while((linetxt =bufferedReader.readLine())!=null)
//
//       {
//           Ip ip=new Ip();
//           String[] re = linetxt.split("\t");//用split()函数直接分割
//          // System.out.println(re[0]+"："+re[1]);
//           ip.setIp(re[0]);
//           ip.setPort(Integer.parseInt(re[1]));
//           list.add(ip);
//       }

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        Document document = null;
        List<Ip> ipList = ProxyIp.ipList;//proxyIp.getIp(5);
        try {
            while (response == null) {
                int index = ThreadLocalRandom.current().nextInt(ipList.size());
                Ip ip = ipList.get(index);
                if (index < ipList.size())
                    index++;
                HttpHost proxy = new HttpHost(ip.getIp(), ip.getPort());
                //把代理设置到请求配置
                RequestConfig defaultRequestConfig = RequestConfig.custom()
                        .setConnectTimeout(30000)//设置连接超时时间
                        .setSocketTimeout(30000)//设置读取超时时间
                        .setProxy(proxy)
                        .build();
                client = HttpClients.createDefault();
                HttpGet httpget = new HttpGet(url);
                httpget.setConfig(defaultRequestConfig);
                httpget.setHeader("X-Requested-With", "XMLHttpRequest");
                httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
                httpget.setHeader(HTTP.CONTENT_TYPE, "application/json");
                //httpget.setHeader("Cookie","SC_ANALYTICS_GLOBAL_COOKIE=34828085090c42aab92059d15337a16f|False; i10c.uid=1575875201289:9945; pf-accept-language=en-US; ping-accept-language=en-US; optimizelyEndUserId=oeu1575875204068r0.8530193021315291; EG-U-ID=B05a0ccdb6-76b8-44fc-ba1c-579827ba07f4; _evga_8774=f2f26ccb7838f208.; _ga=GA1.2.1124841400.1575875268; _msuuid_27490huz44870=7A89BA2E-0150-4FA1-B5B9-B4DC9F71A5E7; LPVID=llN2ZhMmE1ZTdlMDliMDk3; _CT_RS_=Recording; WRUID=2558248983528169; _cs_ex=1; _cs_c=1; sid=180144005727497640xDPEXUYCD6PR35MX8GVOJE337HGEPJKTK9MHBVRKS56VVFNDJE8IIQ04XSCAVZVQO; csscxt=1473186314.20480.0000; TS01cecf1b=01460246b6c330edccade947a6d609c2ad6e96bf2d72ca2fa276cee8814f20ae8acb7ae7feb530a2b61c8f9ca2a1477ab13a24335c; TS0138bc45=01460246b6bfdca6b6b15a9a66aa87d1ef3372deb19d2c47fbf82333b3cfe33e43ac839efe82d5e3586a2e29f8990d987ea42078d1; i10c.ss=1576480541434; TS0184e6b9=01460246b643c65468d8f1cd47e47db1478805bddb5ff54c0afc10fa3edc4ff0db177c013525430d1a8347f92d4be8eb41c91b109b; dkuhint=false; EG-S-ID=C813d979eb-bbae-446f-aaa1-72e5b2e7db4c; _gid=GA1.2.707658948.1576480574; TS01d239f3=01460246b624d543409ed905f9c1490c5fa9fc3780734407578e11bced92bac209a5a0eb391d6af7ce151eab9b1364dc8887e712a4; website#lang=; TS017613a9=01460246b615c7c792600af5d06b1b83f1005d80cdb48cedafa935873b4b7b9555411976eed27fbb92b5ec2d14db841227922bcf3f; TS018060f7=01460246b6286b8791aaee263c4d354d170d707ade8e94ea44e524b36b63f23e559c49e86fe54c729284b2abff8416dca0a01f1a53; TS013c3a0b=01460246b67c79d5d9e671bf705cf9da28d85119b1e1770666e28987cb053dd32ff4ee86bbe46da783f799622e8cc7ed4f8f8c2c53; _aa7988=1x71c3; TS01d5128f=01460246b6fce61ffc874a619cabc2e5ba1170543d4e97f39e6828e0a539bc0f6bf042021ab589e96fac129d9f5b1e0738da4e3dbd; utag_main=v_id:016ee97b8a5a007af48e868f907003070007f0680093c$_sn:10$_ss:0$_st:1576487589551$ses_id:1576480569051%3Bexp-session$_pn:14%3Bexp-session; i10c.uservisit=103; QSI_HistorySession=https%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen%2Fbattery-products%2Fbatteries-non-rechargeable-primary%2F90~1576480575448%7Chttps%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen%2Fbattery-products%2Fbattery-packs%2F89%2Fpage%2F5~1576480644975%7Chttps%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen~1576481294048%7Chttps%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen%3Fkeywords%3D102-1216-ND~1576482943456%7Chttps%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen~1576483538062%7Chttps%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen%2Fboxes-enclosures-racks%2Fcard-rack-accessories%2F601~1576485223277%7Chttps%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen%2Fboxes-enclosures-racks%2Fbackplanes%2F589~1576485261582%7Chttps%3A%2F%2Fwww.digikey.com%2Fproduct-detail%2Fen%2Fmolex%2F0502128000-12-W6-D%2F0502128000-12-W6-D-ND%2F10131315%23images~1576485807759; __CT_Data=gpv=49&ckp=tld&dm=digikey.com&apv_53368_www=93&cpv_53368_www=44&rpv_53368_www=22; ctm={'pgv':2219989871233501|'vst':5949724382093333|'vstr':7043098129549564|'intr':1576485856796|'v':1|'lvst':4211}; i10c.bdddb=c2-760e0H3BeoT3qsxcU4uBmhLBMxKouFTRumRKxy7JrxLoSOLFWa1ChRABMXKnMWMzeeMGqthBpZLtVTFhzQzBEZ5GMsQLNQZS1eMqqs9S2tJnKUsgKVZ6mRITHxPiNsWMvJHGvFQgkyJiQ1IcP5uBmvLBMxKouIORVeMG5Xy2ZaEnPOLFNQzlhRAdWjHYDFTRqkuHqsjBpyg2xJzcPVuCKU8HS4KnwFTRFrHGvnAontJNKTK2iQzBhSiIRsPNHKTng28oWn9GkzrmKTucPVOncMAGHyxiRtORv9WfBFaBpyEoxOKhPZuBPREBMxKnwFTRB3aBvs4HNtJnOZRcP8yHsMAGHxziMKmhG1HGvnAokyJmOOKKOUuBmMFrHxziMKmDTKyrqs9BqWHiP3FhPv96mR5HusPnHN2QykQBvS8Jt4EnzSOfQQzllVBKHxzmQOXMvJLK2u4GPxOnROKHOa2HhRkFR4PiMuSXuqHJWn9GkytiPTmxKVz6nzGGK4KnwFTRMYE6mn9GkzrlKTucPV9uS1sBMxKouIORVeMGK24GptKLNOKHKVza9MAGHyxiMKORweNpqslBp1IHKTKcU6");

                httpget.setHeader("cookie","opp=098");
                response = client.execute(httpget);
                if (response.getStatusLine().getStatusCode() != 200) {
                    ipList.remove(ip);
                    ProxyIp.getIp(1);
                }
            }
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            document = Jsoup.parse(content);
          //  System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            logger.info("Next error" + e);
            // document=getcontent(url);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return document;
    }
}
