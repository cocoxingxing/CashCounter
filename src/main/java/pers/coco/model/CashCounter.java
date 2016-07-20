package pers.coco.model;

import java.util.Set;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Enumeration;

import pers.coco.tools.FileHelper;
import pers.coco.commons.Consts;
import pers.coco.tools.Tools;

/**
 * Created by Administrator on 2016/7/19.
 */
public class CashCounter {
    private Set<String> discountThreeForTwo = new HashSet<String>();
    private Set<String> discount95 = new HashSet<String>();
    private Hashtable<String, Commodity> commodities = new Hashtable<String, Commodity>();
    private Hashtable<String, Integer> originalShoppingItems = new Hashtable<String, Integer>();
    private Set<ShoppingItem> finalShoppingItems = new LinkedHashSet<ShoppingItem>();

    public CashCounter() {
        loadCommodities();
        loadDiscountSetting();
    }

    public Set<String> getDiscountThreeForTwo() {
        return this.discountThreeForTwo;
    }

    public Set<String> getDiscount95() {
        return this.discount95;
    }

    public Hashtable<String, Commodity> getCommodities() {
        return this.commodities;
    }

    public void setOriginalShoppingItems(Hashtable<String, Integer> originalShoppingItems) {
        this.originalShoppingItems = originalShoppingItems;
    }

    public Hashtable<String, Integer> getOriginalShoppingItems() {
        return this.originalShoppingItems;
    }

    public Set<ShoppingItem> getFinalShoppingItems() {
        return this.finalShoppingItems;
    }

    public void loadDiscountSetting() {
        discountThreeForTwo = FileHelper.getDiscountCommoditiesByTag(Consts.DISCOUNT_3_FOR_2_TAG);
        discount95 = FileHelper.getDiscountCommoditiesByTag(Consts.DISCOUNT_95_TAG);
    }

    public void loadCommodities() {
        commodities = FileHelper.getCommodities(Consts.DB_FILE_PATH);
    }

    public ShoppingItem discountItem(String code, int num) {
        if(num == 0) {
            return null;
        }
        ShoppingItem shoppingItem = new ShoppingItem();

        Enumeration<String> keys = this.commodities.keys();
        Commodity commodity = null;
        if(this.commodities.containsKey(code)) {
            commodity = this.commodities.get(code);
        } else {
            return null;
        }
        shoppingItem.setName(commodity.getName());
        shoppingItem.setPrice(commodity.getPrice());
        shoppingItem.setNum(num);
        shoppingItem.setUnit(commodity.getUnit());
        shoppingItem.setTotalPrice(Tools.formatDouble(num* commodity.getPrice()));
        String tag = Consts.DISCOUNT_NONE_TAG;
        if(this.discountThreeForTwo.contains(code) && num >= 3) {
            tag = Consts.DISCOUNT_3_FOR_2_TAG;
            int discountNum = Math.floorDiv(num, 3);
            shoppingItem.setDiscountNum(discountNum);
            shoppingItem.setDiscountPrice(Tools.formatDouble(commodity.getPrice() * discountNum));
        }

        if(tag.equals(Consts.DISCOUNT_NONE_TAG) && discount95.contains(code)) {
            tag = Consts.DISCOUNT_95_TAG;
            shoppingItem.setDiscountPrice(Tools.formatDouble(commodity.getPrice() * num * 0.05));
        }
        if(tag.equals(Consts.DISCOUNT_NONE_TAG)) {
            shoppingItem.setDiscountPrice(0.00);
        }
        shoppingItem.setTotalPrice(Tools.formatDouble(shoppingItem.getTotalPrice() - shoppingItem.getDiscountPrice()));
        shoppingItem.setDiscountTag(tag);
        return shoppingItem;
    }

    public void settlement() {
        this.finalShoppingItems.clear();
        Enumeration<String> keys = this.originalShoppingItems.keys();
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            ShoppingItem item = discountItem(key, this.originalShoppingItems.get(key));
            if(item != null) {
                this.finalShoppingItems.add(item);
            }
        }
    }

    public void printShoppingList() {
        String itemsText = "";
        String discount3For2ItemsStr = "";

        double totalPrice = 0.0d;
        double totalDiscountPrice = 0.0d;

        for(ShoppingItem item : this.finalShoppingItems) {
            totalPrice += item.getTotalPrice();
            if(item.getDiscountTag().equals(Consts.DISCOUNT_3_FOR_2_TAG)) {
                itemsText += "名称：" + item.getName() + "，数量：" + item.getNum() + item.getUnit() + "，单价：" + Tools.formatDoubleToStr(item.getPrice()) + "(元)，小计：" + Tools.formatDoubleToStr(item.getTotalPrice()) + "(元)" + System.getProperty("line.separator");
                discount3For2ItemsStr += "名称：" + item.getName() + "，数量：" + item.getDiscountNum() + item.getUnit() + System.getProperty("line.separator");
                totalDiscountPrice += item.getDiscountPrice();
            } else if(item.getDiscountTag().equals(Consts.DISCOUNT_95_TAG)) {
                itemsText += "名称：" + item.getName() + "，数量：" + item.getNum() + item.getUnit() + "，单价：" + Tools.formatDoubleToStr(item.getPrice()) + "(元)，小计：" + Tools.formatDoubleToStr(item.getTotalPrice()) + "(元)，节省" + Tools.formatDoubleToStr(item.getDiscountPrice()) + "(元)" + System.getProperty("line.separator");
                totalDiscountPrice += item.getDiscountPrice();
            } else {
                itemsText += "名称：" + item.getName() + "，数量：" + item.getNum() + item.getUnit() + "，单价：" + Tools.formatDoubleToStr(item.getPrice()) + "(元)，小计：" + Tools.formatDoubleToStr(item.getTotalPrice()) + "(元)" + System.getProperty("line.separator");
            }
        }
        System.out.println("```");
        System.out.println("***<没钱赚商店>购物清单***");
        System.out.print(itemsText);
        System.out.println("----------------------");
        if(!discount3For2ItemsStr.equals("")) {
            System.out.println("买二赠一商品：");
            System.out.print(discount3For2ItemsStr);
            System.out.println("----------------------");
        }
        System.out.println("总计：" + Tools.formatDoubleToStr(totalPrice) + "(元)");
        if(!Tools.formatDoubleToStr(totalDiscountPrice).equals("0.00")) {
            System.out.println("节省：" + Tools.formatDoubleToStr(totalDiscountPrice) + "(元)");
        }
        System.out.println("**********************");
        System.out.println("```");
        this.finalShoppingItems.clear();
    }
}
