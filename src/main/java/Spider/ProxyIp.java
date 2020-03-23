package Spider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProxyIp {
    public static List<Ip> ipList=new ArrayList<>();
    public static List<Ip> getIp(int num) throws IOException {
        String url = "http://webapi.http.zhimacangku.com/getip?num="+ num +"&type=2&yys=0&port=11&time=1&ts=1&lb=1&sb=0&pb=45&mr=1";
        CloseableHttpClient client=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        CloseableHttpResponse response = client.execute(httpGet);

        List<Ip> addIpList = new ArrayList<>();
        if (response.getStatusLine().getStatusCode() == 200) {
            String body = null;
            try {
                body = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = JSONObject.parseObject(body);
                if (jsonObject.getBoolean("success")){
                    ProxyIp proxyIp = null;
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.size(); i++) {
                       Ip ip=new Ip();
                        JSONObject ip1 = data.getJSONObject(i);
                        ip.setIp(ip1.getString("ip"));
                        ip.setPort(ip1.getInteger("port"));
//                        proxyIp.setAlive(true);
//                        proxyIp.setExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ip.getString("expire_time")).getTime() / 1000);
                      //  ipList.add(proxyIp);
                        addIpList.add(ip);
                    }
                }
                response.close();
            } catch (Exception e) {

            }
        }
        ipList.addAll(addIpList);
        return addIpList;
    }
}
