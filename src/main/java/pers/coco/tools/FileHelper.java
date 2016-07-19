package pers.coco.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.coco.commons.Consts;
import pers.coco.model.Commodity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/17.
 */
public class FileHelper {

    public static String readFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String content = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                content = content + line;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }

    public static Hashtable<String, Commodity> getCommodities(String path) {
        String text = FileHelper.readFile(path);
        List<Commodity> listCommodities = JSONArray.parseArray(text, Commodity.class);

        Hashtable<String, Commodity> dbItems = new Hashtable<String, Commodity>();
        for(Commodity commodity : listCommodities) {
            dbItems.put(commodity.getCode(), commodity);
        }
        return dbItems;
    }

    public static Set<String> getDiscountCommoditiesByTag(String tag) {
        String text = readFile(Consts.DISCOUNT_FILE_PATH);
        JSONObject jsonObject = JSON.parseObject(text);

        Set<String> result = new HashSet<String>();
        JSONArray jsonArray = JSON.parseArray(jsonObject.get(tag).toString());
        for(int i = 0; i < jsonArray.size(); ++i) {
            result.add(jsonArray.get(i).toString());
        }
        return result;
    }

    public static void addShoppingItem(String code, int num, Hashtable<String, Integer> shoppingItems) {
        if(shoppingItems.containsKey(code)) {
            int tempNum = shoppingItems.get(code);
            shoppingItems.put(code, tempNum + num);
        } else {
            shoppingItems.put(code, num);
        }
    }

    public static void parseShoppingItem(String itemLine, Hashtable<String, Integer> shoppingItems) {
        int startIndex = itemLine.indexOf("'");
        int endIndex = itemLine.indexOf("'", startIndex + 1);
        if(startIndex != -1 && endIndex != -1) {
            itemLine = itemLine.substring(itemLine.indexOf("'") + 1, itemLine.indexOf("'", itemLine.indexOf("'") + 1));
        } else {
            return;
        }
        if(itemLine.indexOf("-") != -1) {
            String code = itemLine.substring(0, itemLine.indexOf("-"));
            int num = Integer.parseInt(itemLine.substring(itemLine.indexOf("-") + 1, itemLine.length()));
            addShoppingItem(code, num, shoppingItems);
        } else {
            addShoppingItem(itemLine, 1, shoppingItems);
        }
    }
}
