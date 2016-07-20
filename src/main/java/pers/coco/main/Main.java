package pers.coco.main;

import java.util.Hashtable;

import pers.coco.model.CashCounter;
import pers.coco.tools.FileHelper;
import pers.coco.commons.Consts;

/**
 * Created by Administrator on 2016/7/19.
 */
public class Main {
    public static CashCounter cashCounter = new CashCounter();

    public static void main(String[] args) {
        testNoDiscount();
        testDiscount3For2();
        testDiscount95();
        testBothDiscount();
    }

    public static void testNoDiscount() {
        Hashtable<String, Integer> shoppingItemsWithNoDiscount = FileHelper.readFileToShoppingItems(Consts.DATA_FOLDER_PATH + "shoppingItemsWithNoDiscount.txt");
        cashCounter.setOriginalShoppingItems(shoppingItemsWithNoDiscount);
        cashCounter.settlement();
        cashCounter.printShoppingList();
    }

    public static void testDiscount3For2() {
        Hashtable<String, Integer> shoppingItemsWithDiscount3for2 = FileHelper.readFileToShoppingItems(Consts.DATA_FOLDER_PATH + "shoppingItemsWithDiscount3for2.txt");
        cashCounter.setOriginalShoppingItems(shoppingItemsWithDiscount3for2);
        cashCounter.settlement();
        cashCounter.printShoppingList();
    }

    public static void testDiscount95() {
        Hashtable<String, Integer> shoppingItemsWithDiscount95 = FileHelper.readFileToShoppingItems(Consts.DATA_FOLDER_PATH + "shoppingItemsWithDiscount95.txt");
        cashCounter.setOriginalShoppingItems(shoppingItemsWithDiscount95);
        cashCounter.settlement();
        cashCounter.printShoppingList();
    }

    public static void testBothDiscount() {
        Hashtable<String, Integer> shoppingItemsWithBothDiscount = FileHelper.readFileToShoppingItems(Consts.DATA_FOLDER_PATH + "shoppingItemsWithBothDiscount.txt");
        cashCounter.setOriginalShoppingItems(shoppingItemsWithBothDiscount);
        cashCounter.settlement();
        cashCounter.printShoppingList();
    }
}
