package Spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fetch {
   private static CloseableHttpClient client=null;
    Document document;
   public Document getcontent(String url) throws IOException {
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
       CloseableHttpResponse response=null;
       ProxyIp proxyIp=new ProxyIp();
       List<Ip> ipList=proxyIp.getIp(5);
       //int index=0;
           int code=0;
           try {
               while (response == null) {
                   int index = ThreadLocalRandom.current().nextInt(ipList.size());

                   Ip ip = ipList.get(index);
                   if (index < ipList.size())
                       index++;
                   HttpHost proxy = new HttpHost(ip.getIp(), ip.getPort());
                   //把代理设置到请求配置
                   RequestConfig defaultRequestConfig = RequestConfig.custom()
                           .setConnectTimeout(20000)//设置连接超时时间
                           .setSocketTimeout(20000)//设置读取超时时间
                           .setProxy(proxy)
                           .build();
                   if (client == null) {
                       client = HttpClients.createDefault();
                   }

                   HttpGet httpget = new HttpGet(url);
                   httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
                   httpget.setHeader("Origin", " https://www.digikey.com");
                   httpget.setHeader("cookie", "optimizelyEndUserId=oeu1566888715026r0.032682918843295106; _evga_8774=a28d58b16019b557.; _msuuid_27490huz44870=C55A3EF7-EB36-4527-8418-75B50A089059; _ga=GA1.2.833529576.1566888716; i10c.uid=1566889171994:3383; EG-U-ID=B193e5395a-e001-425c-b8d3-96cdc9ae1913; LPVID=MxYTkxNmRlY2U5MzM4OWM2; _CT_RS_=Recording; WRUID=2413880709497356; _cs_ex=1; _cs_c=1; cur=USD; SiteForCur=US; __atuvc=8%7C36; _gid=GA1.2.410752656.1567991583; optimizelyEndUserId=oeu1566888715026r0.032682918843295106; pf-accept-language=en-US; ping-accept-language=en-US; SC_ANALYTICS_GLOBAL_COOKIE=0db52c1f336345db8b77db4210df9d09|True; sid=180144002554781740xS56XMQLI91JLMJ1KAHQWWNIF2GW6OUP14F5Z28FKT2GMRJYG0ABQ70LB9HXK17VR; csscxt=1473186314.20480.0000; dtCookie=7F59BD78A0CB30572DE5A91C7EC9E2E6|X2RlZmF1bHR8MQ; TS017613a9=01460246b6bc4b58c6b086f58f95b6f2a89406dd957e7881f650e06777d4d30c5f875832779760e54943539f1cf9f1b834b6cc411f; TS01cecf1b=01460246b6bc4b58c6b086f58f95b6f2a89406dd957e7881f650e06777d4d30c5f875832779760e54943539f1cf9f1b834b6cc411f; TS018060f7=01460246b69bd89b07b47ad359671757bfa0c1e8122779acadc0ac5da03883de78dd50de38a1856d994d3bc8a05c00741db2080b13; TS0138bc45=01460246b69bd89b07b47ad359671757bfa0c1e8122779acadc0ac5da03883de78dd50de38a1856d994d3bc8a05c00741db2080b13; i10c.ss=1568164256996; TS013c3a0b=01460246b674250313ff50a006f9c92c5a6335751c477618a5fbd8f948d20203d9d38ee998ac14b57021c7b4580b48b92685134f47; TS0184e6b9=01460246b674250313ff50a006f9c92c5a6335751c477618a5fbd8f948d20203d9d38ee998ac14b57021c7b4580b48b92685134f47; dkuhint=false; TS01d5128f=01460246b6c31a4852d083a15a78f11875491218884694ffbf07708c23040c9764fbc292b6053cce9ef29f57613641d070a6dcc7ca; EG-S-ID=C8db88be2b-ba79-400d-a901-c82a4c22d2fd; _gat_Production=1; _aa7988=1x30d4; i10c.uservisit=1194; utag_main=v_id:016cd1d864e50002d174f73792610307000370680093c`$_sn:41`$_ss:0`$_st:1568166108454`$ses_id:1568164289345%3Bexp-session`$_pn:3%3Bexp-session; website#lang=en-US; TS01d239f3=01460246b60a32b4491e48c6ecb363e2b1f53c01638a20857c51a696a63a5655ff25c1d3a5591c8bc4a3122f14688e41ec80127a97; ctm={'pgv':2937929044676914|'vst':8761263287712177|'vstr':8518401075425761|'intr':1568164314361|'v':1|'lvst':899}; __CT_Data=gpv=550&ckp=tld&dm=digikey.com&apv_53368_www=919&cpv_53368_www=369&rpv_53368_www=278; i10c.bdddb=c2-f0103ZLNqAeI3BH6yYOfG7TZlRtCrMwzKDQfPMtvESnCuVjBtyWjMuimqUxOxsswxKtpOHdkESNCtx04RiOfGqfItRyNsjxVKDqCXHdkETLIz3pHoz3aGvqxlRtCrMwqPnlkPUqfJSiIR0jBTuTfdC4fqRoIOmsvz8qktXYkJNoprsolozTpvkQUSMtHlpVtKDQfPM0u5KY8oxo6uXUaGVZkqo8pgTsvP8rISKeqQNnroxoV1uTfBwCilRTCqoIEKDqfQufpESNCtxAwCl1GBvefrzsCqOsvPcSaKMdfK0iMSsoBJ9svdMZkqMuploxvU8qNPRYkJNnroxoRDDOfGqfIlRtIrmsv2EqrKMdfJ2iHtG4WBuTfBwCfqRsHloauP8qkKREfJ2iHtGajUb4aGvZlOPoHQjxvpNlkPHeIESnCwWnEu3OfquhpsMtrpsvwKDQjTNcfJ2mLxyjBTyYdEqeKpWtJloXuUGvfPwcpQQiHTwuAxuWGBvefq1oHqGDqPDllxLYktNnHKmg1kuTfBwCilRTCqo7e5nYfPMYlrQiHTsoBI9OfGqfIoMtrloxKm8qkKNBfJSiHuspkoz5JbTZkqMys; QSI_HistorySession=https%3A%2F%2Fwww.digikey.com%2Fproducts%2Fen%2Frf-if-and-rfid%2Frf-amplifiers%2F860~1568164317447");
                   httpget.setConfig(defaultRequestConfig);
                   response = client.execute(httpget);
               }

               //String uri="https://www.digikey.com/products/en";
               HttpEntity entity=response.getEntity();
               String content= EntityUtils.toString(entity,"utf8");
               document= Jsoup.parse(content);
           }catch (Exception e){
               System.out.println(e);
                document=getcontent(url);
           }finally {
               if(response!=null){
                   response.close();
               }
           }
        return document;
    }
}
