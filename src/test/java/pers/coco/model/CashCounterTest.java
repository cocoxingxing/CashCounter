package pers.coco.model;

import org.junit.Test;
import pers.coco.commons.Consts;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/19.
 */
public class CashCounterTest {

    @Test
    public void should_use_3for2_discount_when_discount_item_ITEM000005() {
        CashCounter cashCounter = new CashCounter();
        ShoppingItem item  = cashCounter.discountItem("ITEM000005", 3);
        System.out.println("discount="+item.getDiscountPrice());
        assertEquals(Consts.DISCOUNT_3_FOR_2_TAG, item.getDiscountTag());
        assertEquals(3, (int)item.getDiscountPrice());
    }

}