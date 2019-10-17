package Spider;

import java.io.IOException;

public class testErrer {//url出现[]
    public static void main(String[] args) throws IOException {
        String url="https://www.digikey.com/product-detail/en/te-connectivity-deutsch-connectors/DTS20F13-35BD-%5BV001%5D/DTS20F13-35BD%5BV001%5D-ND/7152644";
        Fetch fetch=new Fetch();
        System.out.println(fetch.getcontent(url.trim()));
    }
}
