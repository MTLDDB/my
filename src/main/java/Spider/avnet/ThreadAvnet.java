package Spider.avnet;

import Spider.avnet.SaveAvnet;
import entity.Category;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ThreadAvnet implements Runnable {
    private final Element element;
    private SaveAvnet saveAvnet;

    //String third="";
    //String second="";
    public ThreadAvnet(Element element) {
        this.element = element;
       // run();
    }

    @Override
    public void run() {
            saveAvnet=new SaveAvnet();
        List<Category> categoryList=new ArrayList<>();
            String first = element.select("h4").select("a").text();
            String firstUrl = element.select("h4").select("a").attr("href");
            String num1 = element.select("div[class=title]").select("span[class=count]").text();
            Elements secondLevel = element.select("ul[class=products collapse in]").select("ul[class=level-2]");
            if(secondLevel!=null&&secondLevel.size()>0){
                for (Element s : secondLevel) {
                    String second = s.parent().select("a").first().text();
                    String secondUrl = s.parent().select("a").first().attr("href");
                    String num2 = s.parent().select("span[class=count]").first().text();
                    Elements thirdLevel=s.select("li");
                    if(thirdLevel!=null&&thirdLevel.size()>0){
                        for(Element ss:thirdLevel){
                            String third = ss.select("a").first().text();
                            String thirdUrl = ss.select("a").first().attr("href");
                            String num3 = ss.select("span[class=count]").first().text();
                            Elements fourLevel=ss.select("ul[class=level-3]");
                            if(fourLevel!=null&&fourLevel.size()>0){
                                for(Element sss:fourLevel){
                                    String four = sss.select("a").text();
                                    String fourUrl = sss.select("a").attr("href");
                                    String num4 = sss.select("span[class=count]").text();
                                    Category category1=new Category();
                                    category1.setFirst(first);
                                    category1.setSecond(second);
                                    category1.setThird(third);
                                    category1.setFour(four);
                                    category1.setUrl(fourUrl);
                                    category1.setItem_mum(num4);
                                    category1.setObjectid(UUID.nameUUIDFromBytes(fourUrl.getBytes()).toString().replace("-",""));
                                    categoryList.add(category1);
                                    // String test= JSON.toJSONString(category1);
                                   // Category c= JSONObject.parseObject(test,Category.class);
                                    //saveAvnet.save(category1);
                                   // System.out.println(first+"___________"+second+"___________"+third+"______"+four+fourUrl);
                                }
                            }else {
                                Category category2=new Category();
                                category2.setFirst(first);
                                category2.setSecond(second);
                                category2.setThird(third);
                                category2.setUrl(thirdUrl);
                                category2.setItem_mum(num3);
                                category2.setObjectid(UUID.nameUUIDFromBytes(thirdUrl.getBytes()).toString().replace("-",""));
                                //categoryList.add(category2);
                                 saveAvnet.save(category2);
                               // System.out.println(first+"___________"+second+"___________"+third);
                            }
                        }
                    }else {
                        Category category3=new Category();
                        category3.setFirst(first);
                        category3.setSecond(second);
                        category3.setUrl(secondUrl);
                        category3.setItem_mum(num2);
                        category3.setObjectid(UUID.nameUUIDFromBytes(secondUrl.getBytes()).toString().replace("-",""));
                        categoryList.add(category3);
                        //saveAvnet.save(category3);
                        //System.out.println(first+"___________"+second);
                    }
                }
            }else {
            Category category=new Category();
            category.setFirst(first);
            category.setUrl(firstUrl);
            category.setItem_mum(num1);
            category.setObjectid(UUID.nameUUIDFromBytes(firstUrl.getBytes()).toString().replace("-",""));
            categoryList.add(category);
            // saveAvnet.save(category);
            //System.out.println(first+"___________");
            }
            saveAvnet.saveList(categoryList);
    }
}
