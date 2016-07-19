package pers.coco.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

import pers.coco.tools.FileHelper;
import pers.coco.commons.Consts;

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
}
