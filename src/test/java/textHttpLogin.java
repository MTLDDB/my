import entity.*;
import mapper.IpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class textHttpLogin {
    public static void main(String args[]) throws Exception {
        textHttpLogin t = new textHttpLogin();
        t.setUp();
    }

    private SqlSessionFactory sqlSessionFactory;

    public void setUp() throws Exception {
//            ProxyIp ip=new ProxyIp();
//            CloseableHttpResponse response=ip.getProxyRes("https://www.xicidaili.com/","171.212.91.173",61234,"HTTP");
        //System.out.println(response);
//            response.addHeader("Set-Cookie","c1=a; path=/; domain=localhost");
//            response.addHeader("Set-Cookie","c2=b; path=\"/\", c3=c; domain=\"localhost\"");
//            Header h1 = response.getFirstHeader("Set-Cookie");
//            System.out.println(h1);
//            HeaderElementIterator it = new BasicHeaderElementIterator(
//                    response.headerIterator("Set-Cookie"));
//            while (it.hasNext()) {
//                HeaderElement elem = it.nextElement();
//                System.out.println(elem.getName() + " = " + elem.getValue());
//                NameValuePair[] params = elem.getParameters();
//                for (int i = 0; i < params.length; i++) {
//                    System.out.println(" " + params[i]);
//                }

//            StringEntity myEntity = new StringEntity("important message",
//                    ContentType.create("textHttpLogin/plain", "UTF-8"));

        // HttpEntity entity = response.getEntity();
//            System.out.println(entity.getContentType());
//            System.out.println(entity.getContentLength());
//            System.out.println(EntityUtils.toString(entity));
        // System.out.println(EntityUtils.toByteArray(entity).length);
//            if (entity != null) {
//                InputStream instream = entity.getContent();
//                try {
//                    System.out.println(instream);
//                    System.out.println(instream.read());
//                    System.out.println(instream.read());
//                } finally {
//                    //instream.close();
//                }
//            }
//            if (entity != null) {
//                entity = new BufferedHttpEntity(entity);
//                System.out.println( entity.getContentLength());
//            }
//            response.close();
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//           // HttpClient client = HttpClientBuilder.create().build();
//            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//            formparams.add(new BasicNameValuePair("userName", "HCL纵横"));
//            formparams.add(new BasicNameValuePair("password", "HUANGCHANG0724"));
//            UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams,
//                   Consts.UTF_8);
//            HttpPost httppost = new HttpPost("https://passport.baidu.com/v2/api/?login");
//            httppost.setHeader("X-Requested-With", "XMLHttpRequest");
//            httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
//            httppost.setHeader("Referrer-Policy","origin");
//            httppost.setEntity(entity1);
//            //httppost.setEntity(new UrlEncodedFormEntity(formparams, HTTP.UTF_8));
//
//            CloseableHttpResponse response1 = httpclient.execute(httppost);
//            Header locationHeader = response1.getFirstHeader("location");
//           // String location = String.valueOf(locationHeader.getValue());
//            System.out.println(locationHeader);
//            HttpEntity entity = response1.getEntity();

//            System.out.println(entity.getContentType());
        //String content = EntityUtils.toString(entity,"utf-8");
        // Document dov= Jsoup.parse(content);
        //Elements meta= dov.getElementsByTag("meta");
        // System.out.println(meta.attr("url"));
        //if(content.contains("登陆成功")){
        //     System.out.println("登陆成功！！！");
        //  }
//            System.out.println(EntityUtils.toString(entity,"utf-8"));
//            System.out.println(response1.getStatusLine().getStatusCode());
//            response1.close();
//            HttpGet httpget = new HttpGet("https://passport.baidu.com/v2Jump.html?callback=&index=0&codestring=&username=&phonenumber=&mail=&tpl=&u=&needToModifyPassword=&gotourl=&auth=&error=100023&traceid=");
//            CloseableHttpResponse  response = httpclient.execute(httpget);
//            System.out.println(response.getEntity().getContent());
//            HttpEntity entity2 = response.getEntity();
//            System.out.println(EntityUtils.toString(entity2,"utf-8"));
        //String content = EntityUtils.toString(entity, "utf-8");
        //System.out.println(content);


        //            //mybatis配置文件
        String resource = "configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用SqlSessionFactoryBuilder创建sessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取session
        SqlSession session = sqlSessionFactory.openSession(true);//ture为自动提交事务
        //或者可以用session.commit（）；
        //获取mapper接口的代理对象
        IpMapper ipMapper = session.getMapper(IpMapper.class);
        //test t=new test();
        // t.ma();

            IP ip=new IP();
            ip.setIp("7777777777");
            ip.setPort("port");
            ip.setHttp(Enumhttp.HTTPS);
            ipMapper.insertIp(ip);
            IP ip1=new IP();
            ip1.setPort("89899");
            ip1.setIp("7777777777");
            ipMapper.update(ip1);
            IP ip2=ipMapper.selectOne(ip.getIp());
            System.out.println(ip2.getPort());

//        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
//        List<Student> stu = studentMapper.selectStudent();
//        for (Student s : stu) {
//            System.out.print(s.getId() + " ");
//            System.out.print(s.getName() + " ");
//            System.out.print(s.getSex() + " ");
//            System.out.print(s.getAge() + " ");
//            System.out.print(s.getClazz().getCode());
//            System.out.println();
//        }
//        Clazz clazz = studentMapper.selectClazzById(2);
//        System.out.println(clazz.getCode());
//        System.out.println(clazz.getStudents().size());
//        for (Student s : clazz.getStudents()) {
//            System.out.print(s.getId() + " ");
//            System.out.print(s.getName() + " ");
//            System.out.print(s.getSex() + " ");
//            System.out.print(s.getAge() + " ");
//            System.out.print(s.getClazz().getCode());
//            System.out.println();
//        }


//        MoretableMapper moretableMapper=session.getMapper(MoretableMapper.class);
//        User user=moretableMapper.selectUserById(1);
//        System.out.println(user.getUsername());
        session.close();
    }
}
