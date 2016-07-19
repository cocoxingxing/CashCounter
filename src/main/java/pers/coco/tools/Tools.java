package pers.coco.tools;

/**
 * Created by Administrator on 2016/7/18.
 */

public class Tools {
    public static double formatDouble(double value) {
        String result = String .format("%.2f", value);
        return Double.parseDouble(result);
    }

    public static String formatDoubleToStr(double value) {
        return String.format("%.2f", value);
    }
}
