package Spider.verical;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Category;

import java.util.*;

public class ThreadVerical implements Runnable {

    private String temp;

    //String third="";
    //String second="";
    public ThreadVerical(String temp) {
        this.temp = temp;
        // run();
    }

    @Override
    public void run() {
        JSONObject jsonObject1 = JSONObject.parseObject(temp, JSONObject.class);
        String firstCateName = jsonObject1.getString("name");
        String firstNum = jsonObject1.getString("privateId");
        Map<String, String> map = new LinkedHashMap<>();
        map.put(firstCateName, firstNum);
        String secondCate2 = jsonObject1.getString("categories");
        JSONArray categoriesArr2 = JSONArray.parseArray(secondCate2);
        if (secondCate2 != "[]" && categoriesArr2.size() > 0) {
            for (int i = 0; i < categoriesArr2.size(); i++) {
                String temp = JSON.toJSONString(categoriesArr2.get(i));
                getNext(temp, map);
            }
        } else {

            Category category = new Category();
            category.setFirst(firstCateName);
            String url = "https://www.verical.com/products/" + firstCateName.toLowerCase() + "-" + firstNum + "/";
            category.setUrl(url);
            category.setObjectid(UUID.nameUUIDFromBytes(url.getBytes()).toString().replace("-", ""));
            SaveVerical.save(category);
        }
    }

    void getNext(String ca, Map<String, String> map) {
        JSONObject jsonObject = JSONObject.parseObject(ca, JSONObject.class);
        String firstCateName = jsonObject.getString("name");
        String firstNum = jsonObject.getString("privateId");

        Map<String, String> mapTemp = new LinkedHashMap<>();
        mapTemp.putAll(map);
        mapTemp.put(firstCateName, firstNum);
        String categories = jsonObject.getString("categories");
        JSONArray categoriesArr = JSONArray.parseArray(categories);
        if (categories != "[]" && categoriesArr.size() > 0) {
            for (int i = 0; i < categoriesArr.size(); i++) {
                String temp = JSON.toJSONString(categoriesArr.get(i));
                getNext(temp, mapTemp);
            }
        } else {
            int flag = mapTemp.size();
            List<String> names = new ArrayList<>();
            List<String> nums = new ArrayList<>();
            int index = 0;
            for (Map.Entry<String, String> entry : mapTemp.entrySet()) {
                names.add(index, entry.getKey());
                nums.add(index, entry.getValue());
                index++;
            }
            Category category = new Category();
            String url = null;
            String urlStr = null;
            switch (flag) {
                case 2:
                    category.setFirst(names.get(0));
                    category.setSecond(names.get(1));
                    urlStr=urlU(names,nums);
                    url = "https://www.verical.com/products/" + urlStr;
                    category.setUrl(url);
                    category.setObjectid(UUID.nameUUIDFromBytes(url.getBytes()).toString().replace("-", ""));
                    SaveVerical.save(category);
                    break;
                case 3:
                    category.setFirst(names.get(0));
                    category.setSecond(names.get(1));
                    category.setThird(names.get(2));
                    urlStr=urlU(names,nums);
                    url = "https://www.verical.com/products/" + urlStr;
                    category.setUrl(url);
                    category.setObjectid(UUID.nameUUIDFromBytes(url.getBytes()).toString().replace("-", ""));
                    SaveVerical.save(category);
                    break;
                case 4:
                default:
                    break;
            }
        }
    }

    String urlU(List<String> names,List<String> nums){
        String Str="";
        for(int i=0;i<names.size();i++){
            Str+= names.get(i).toLowerCase()
                    .replace(" - ", "-")
                    .replace(" ", "-")
                    .replace(" & ", "-&-")
                    .replace("/", "")
                    + "-" + nums.get(i) + "/";
        }
        return Str;
    }
}
