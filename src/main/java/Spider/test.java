package Spider;
import entity.Enumhttp;
import entity.IP;
import mapper.IpMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.ProxyIp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//@Component
public class test {

    private SqlSessionFactory sqlSessionFactory;
    public   void ma() throws IOException {

        //@Autowired
        //mybatis配置文件
        String resource = "configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用SqlSessionFactoryBuilder创建sessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取session
        SqlSession session = sqlSessionFactory.openSession(true);//ture为自动提交事务
        //或者可以用session.commit（）；
        //获取mapper接口的代理对象
        IpMapper ipMapper = session.getMapper(IpMapper.class);//返回mapper接口的代理对象，
        // 该对象关联了SqlSession对象，
        // 开发者可以通过该对象直接调用方法操作数据库，参数type是Mapper的接口类型。
        CloseableHttpClient client= HttpClients.createDefault();
        List<IP> lists =new ArrayList<IP>();
        String uri="https://www.xicidaili.com/nn/";
        HttpGet httpget=new HttpGet(uri);
        RequestConfig defaultRequetConfig =RequestConfig
                .custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        httpget.setConfig(defaultRequetConfig);
        CloseableHttpResponse response=client.execute(httpget);
        ProxyIp ip=new ProxyIp();
//        CloseableHttpResponse response=ip.getProxyRes(uri,"171.212.91.173",61234,"HTTP");
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        response.close(); // 关闭流和释放系统资源
        Document doc = Jsoup.parse(content); // 解析网页 得到文档对象
        Elements trs = doc.getElementsByClass("odd");
        //System.out.println(trs);
        List<IP> list =new ArrayList<IP>();
        for (Element tr:trs){
            IP i=new IP();
            i.setIp(tr.child(1).text());
            i.setPort(tr.child(2).text());
            Enumhttp http=Enumhttp.valueOf(tr.child(5).text());
            i.setHttp(http);
            list.add(i);
        }
        System.out.println(list.size());
        String uri1="https://www.baidu.com/";
        for(int i=0;i<list.size();i++) {
            //设置代理IP、端口、协议（请分别替换）
            IP p = list.get(i);
            CloseableHttpResponse response1 = ip.getProxyRes(uri1, p.getIp(), Integer.valueOf(p.getPort()), p.getHttp().toString());
            if (response1 != null) {
                System.out.println("成功" + p.getIp() + " " + p.getPort() + " " + p.getHttp());
                ipMapper.insertIp(p);
            }else{
                System.out.println("失败");
            }
            //response1.close();
        }
        session.close();
    }
}
