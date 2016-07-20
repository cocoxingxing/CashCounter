package pers.coco.model;

import org.junit.Test;
import pers.coco.commons.Consts;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/19.
 */
public class CashCounterTest {

    @Test
    public void should_return_null_when_discount_item_ITEM000005_with_num_0() {
        CashCounter cashCounter = new CashCounter();
        assertNull(cashCounter.discountItem("ITEM000005", 0));
    }

    @Test
    public void should_use_95_discount_when_discount_item_ITEM000005_with_num_1() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000005", 1);
        assertEquals(Consts.DISCOUNT_95_TAG, item.getDiscountTag());
        assertEquals(0.15, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_95_discount_when_discount_item_ITEM000005_with_num_2() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000005", 2);
        assertEquals(Consts.DISCOUNT_95_TAG, item.getDiscountTag());
        assertEquals(0.3, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_3for2_discount_when_discount_item_ITEM000005_with_num_3() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000005", 3);
        assertEquals(Consts.DISCOUNT_3_FOR_2_TAG, item.getDiscountTag());
        assertEquals(3.0, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_3for2_discount_when_discount_item_ITEM000005_with_num_4() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000005", 4);
        assertEquals(Consts.DISCOUNT_3_FOR_2_TAG, item.getDiscountTag());
        assertEquals(3.0, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_no_discount_when_discount_item_ITEM000001_with_num_1() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item = cashCounter.discountItem("ITEM000001", 1);
        assertEquals(Consts.DISCOUNT_NONE_TAG, item.getDiscountTag());
        assertEquals(0.0, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_no_discount_when_discount_item_ITEM000001_with_num_2() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000001", 2);
        assertEquals(Consts.DISCOUNT_NONE_TAG, item.getDiscountTag());
        assertEquals(0.0, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_3for2_discount_when_discount_item_ITEM000001_with_num_3() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000001", 3);
        assertEquals(Consts.DISCOUNT_3_FOR_2_TAG, item.getDiscountTag());
        assertEquals(5.5, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_3for2_discount_when_discount_item_ITEM000001_with_num_4() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000001", 4);
        assertEquals(Consts.DISCOUNT_3_FOR_2_TAG, item.getDiscountTag());
        assertEquals(5.5, item.getDiscountPrice(), 0.0001d);
    }

    @Test
    public void should_use_95_discount_when_discount_item_ITEM000003_with_num_4() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000003", 4);
        assertEquals(Consts.DISCOUNT_95_TAG, item.getDiscountTag());
        assertEquals(0.2, item.getDiscountPrice(), 0.0001d);
    }
}