package pers.coco.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/19.
 */
public class CashCounter {
    private Set<String> discountThreeForTwo = new HashSet<String>();
    private Set<String> discount95 = new HashSet<String>();
    private Hashtable<String, Commodity> dbItems = new Hashtable<String, Commodity>();
    private Hashtable<String, Integer> originalShoppingItems = new Hashtable<String, Integer>();
    private Set<ShoppingItem> finalShoppingItems = new LinkedHashSet<ShoppingItem>();

    public CashCounter() {

    }
}
