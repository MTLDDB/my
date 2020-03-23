package Spider.mouser;

import Spider.Fetch;
import Spider.Ip;
import Spider.ProxyIp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class FetchThrad implements Callable {
    private String url;

    public FetchThrad(String url) {
        this.url = url;
    }

    @Override
    public Document call() throws Exception {
        Document document = null;
        try {
            document = Fetch.getcontent(url);
          ///  System.out.println(document);
        } catch (Exception e) {
            System.out.println(e);
        }
        return document;
    }
}
