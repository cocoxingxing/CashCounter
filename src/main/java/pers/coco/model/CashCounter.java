package pers.coco.model;

import java.util.*;

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
        ShoppingItem shoppingItem = new ShoppingItem();
        Enumeration<String> keys = this.commodities.keys();

        Commodity commodity = null;
        if(commodities.containsKey(code)) {
            commodity = commodities.get(code);
        } else {
            return null;
        }
        shoppingItem.setName(commodity.getName());
        shoppingItem.setPrice(commodity.getPrice());
        shoppingItem.setNum(num);
        shoppingItem.setUnit(commodity.getUnit());
        shoppingItem.setTotalPrice(Tools.formatDouble(num* commodity.getPrice()));
        String tag = Consts.DISCOUNT_NONE_TAG;
        if(discountThreeForTwo.contains(code) && num >= 3) {
            tag = Consts.DISCOUNT_3_FOR_2_TAG;
            int discountNum = Math.floorDiv(num, 3);
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
}
