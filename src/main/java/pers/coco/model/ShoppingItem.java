package pers.coco.model;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ShoppingItem {
    private String name = "";
    private int num = 0;
    private int discountNum = 0;
    private double price = 0.0d;
    private String unit = "";
    private double discountPrice = 0.0d;
    private String discountTag = "";
    private double totalPrice = 0.0d;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public int getNum() {
        return this.num;
    }

    public void setDiscountNum(int discountNum) {
        this.discountNum = discountNum;
    }

    public int getDiscountNum() {
        return this.discountNum;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return this.unit;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountTag(String discountTag) {
        this.discountTag = discountTag;
    }
    public String getDiscountTag() {
        return this.discountTag;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }
}
