package pers.coco.main;

import pers.coco.model.CashCounter;

import java.util.Hashtable;

import pers.coco.tools.FileHelper;
import pers.coco.commons.Consts;

/**
 * Created by Administrator on 2016/7/19.
 */
public class Main {
    public static CashCounter cashCounter = new CashCounter();

    public static void main(String[] args) {
        Hashtable<String, Integer> shoppingItems = FileHelper.readFileToShoppingItems(Consts.ITEM_FILE_PATH);
        cashCounter.setOriginalShoppingItems(shoppingItems);
        cashCounter.settlement();
        cashCounter.printShoppingList();
    }
}
