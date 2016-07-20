package pers.coco.tools;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import pers.coco.commons.Consts;
import pers.coco.model.Commodity;

/**
 * Created by Administrator on 2016/7/19.
 */

public class FileHelperTest {
    @Test
    public void should_return_empty_when_read_nonexist_file() throws Exception {
        String filePath = "test.json";
        assertEquals("", FileHelper.readFile(filePath));
    }

    @Test
    public void should_return_size_3_when_get_commodities() {
        Hashtable<String, Commodity> commodities = FileHelper.getCommodities(Consts.DB_FILE_PATH);
        assertEquals(4, commodities.size());
    }

    @Test
    public void should_return_size_2_when_get_3for2_discount_commodities() {
        Set<String> discounts = FileHelper.getDiscountCommoditiesByTag("3for2");
        assertEquals(2, discounts.size());
    }

    @Test
    public void should_return_size_1_when_add_new_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        FileHelper.addShoppingItem("ITEM000001", 1, shoppingItems);
        assertEquals(1, shoppingItems.size());
    }

    @Test
    public void should_return_num_1_when_add_new_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        FileHelper.addShoppingItem("ITEM000001", 1, shoppingItems);
        assertEquals(1, shoppingItems.get("ITEM000001").intValue());
    }

    @Test
    public void should_return_size_1_when_add_exist_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        shoppingItems.put("ITEM000001", 1);
        FileHelper.addShoppingItem("ITEM000001", 1, shoppingItems);
        assertEquals(1, shoppingItems.size());
    }

    @Test
    public void should_return_num_2_when_add_exist_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        shoppingItems.put("ITEM000001", 1);
        FileHelper.addShoppingItem("ITEM000001", 1, shoppingItems);
        assertEquals(2, shoppingItems.get("ITEM000001").intValue());
    }

    @Test
    public void should_return_size_1_when_parse_new_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        FileHelper.parseShoppingItem("'ITEM000001',", shoppingItems);
        assertEquals(1, shoppingItems.size());
    }

    @Test
    public void should_return_size_1_when_parse_exist_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        shoppingItems.put("ITEM000001", 1);
        FileHelper.parseShoppingItem("'ITEM000001',", shoppingItems);
        assertEquals(1, shoppingItems.size());
    }

    @Test
    public void should_return_num_2_when_parse_exist_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        shoppingItems.put("ITEM000001", 1);
        FileHelper.parseShoppingItem("'ITEM000001',", shoppingItems);
        assertEquals(2, shoppingItems.get("ITEM000001").intValue());
    }

    @Test
    public void should_return_num_3_when_parse_exist_item_to_shopping_item() {
        Hashtable<String, Integer> shoppingItems = new Hashtable<String, Integer>();
        shoppingItems.put("ITEM000001", 1);
        FileHelper.parseShoppingItem("'ITEM000001-2',", shoppingItems);
        assertEquals(3, shoppingItems.get("ITEM000001").intValue());
    }

    @Test
    public void should_return_size_3_when_read_file_to_shopping_items() {
        Hashtable<String, Integer> shoppingItems = FileHelper.readFileToShoppingItems(Consts.DATA_FOLDER_PATH + "shoppingItemsWithBothDiscount.txt");
        assertEquals(3, shoppingItems.size());
    }
}