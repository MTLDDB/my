package Spider.mouser;

import Spider.Fetch;
import com.alibaba.fastjson.JSON;
import entity.Category;
import mapper.MouserMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class ThreadMouser implements Runnable {
    private String uri;
    private Document document;
    private List<String> levelStr;

    public ThreadMouser(Document document, List<String> levelStr, String uri) {
        this.uri = uri;
        this.document = document;
        this.levelStr=levelStr;
    }

    @Override
    public void run() {
        String level;
        String levelUri;
        try {
            Element div = document.select("ul[class=list-unstyled category-list-items]").first();
            //System.out.println(div);
            if(div==null){
                System.out.println(document.select("div[id=distilIdentificationBlock]").first());
            }
            if (div != null) {
                Elements Lis = div.select("li");
                for (Element Li : Lis) {
                    List<String> levels_ = new ArrayList<>();
                    levels_.addAll(levelStr);
                    level = Li.select("a[id=lnkCategory]").text();
                    levelUri = "https://www.mouser.com" + Li.select("a").attr("href");
                    levels_.add(level);
                    Document document1=Fetch.getcontent(levelUri);
                    Element div1 = document1.select("ul[class=list-unstyled category-list-items]").first();
                    if (div1 != null) {
                        Elements Lis1 = div.select("li");
                        for (Element Li1 : Lis1) {
                            List<String> levels1_ = new ArrayList<>();
                            levels1_.addAll(levels_);
                            level = Li.select("a[id=lnkCategory]").text();
                            levelUri = "https://www.mouser.com" + Li.select("a").attr("href");
                            levels1_.add(level);
                            save_(levels1_,levelUri);
                           // Next.get().getDoc(levelUri, levels_);
                        }
                    }else {
                        save_(levels_,levelUri);
                    }
                }
            } else {
               save_(levelStr, uri);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void save_(List<String> levelStr, String uri) {
        System.out.println(levelStr.size());
        System.out.println(JSON.toJSONString(levelStr));
        Category category = new Category();
        String id = UUID.nameUUIDFromBytes(uri.getBytes()).toString().replace("-", "");
        category.setObjectid(id);
        int index = levelStr.size();
        switch (index) {
            case 2:
                category.setFirst(levelStr.get(0));
                category.setSecond(levelStr.get(1));
                break;
            case 3:
                category.setFirst(levelStr.get(0));
                category.setSecond(levelStr.get(1));
                category.setThird(levelStr.get(2));
                break;
            case 4:
                category.setFirst(levelStr.get(0));
                category.setSecond(levelStr.get(1));
                category.setThird(levelStr.get(2));
                category.setFour(levelStr.get(3));
                break;
            case 5:
                category.setFirst(levelStr.get(0));
                category.setSecond(levelStr.get(1));
                category.setThird(levelStr.get(2));
                category.setFour(levelStr.get(3));
                category.setFive(levelStr.get(4));
                break;
        }
        category.setUrl(uri);
        SaveMouser.save(category);
    }
}
