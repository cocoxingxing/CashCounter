package pers.coco.model;

/**
 * Created by Administrator on 2016/7/19.
 */

public class Commodity {
    private String name = "";
    private String unit = "";
    private double price = 0.0d;
    private String code = "";

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return this.unit;
    }
}

