package util;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ProxyIp {
    public CloseableHttpResponse getProxyRes(String uri,String ip,Integer port,String http){
        HttpHost proxy = new HttpHost(ip, port,http);
        //把代理设置到请求配置
        CloseableHttpClient httpclient=null;
        CloseableHttpResponse httpResp=null;

        try {RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//设置连接超时时间
                .setSocketTimeout(5000)//设置读取超时时间
                .setProxy(proxy)
                .build();
            //实例化CloseableHttpClient对象
            httpclient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            //访问目标地址
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
            //请求返回
            httpResp = httpclient.execute(httpGet);
            int statusCode = httpResp.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("成功");
               return httpResp;
            }
        } catch (Exception e) {
            return null;
        } finally {
            //httpResp.close();
        }
        return null;
    }
}
