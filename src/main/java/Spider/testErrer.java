package Spider;

import java.io.IOException;

public class testErrer {//url出现[]
    public static void main(String[] args) throws IOException {
        String url="https://www.avnet.com";
        Fetch fetch=new Fetch();
        System.out.println(fetch.getcontent(url.trim()));
    }
}
