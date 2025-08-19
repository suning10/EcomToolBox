package com.ecom.common.utils;

public class ConvertSKU {

    public static String convert(String sku){

        String left1 = sku.substring(0,1);
        String left2 = sku.substring(0,2);
        String left3 = sku.substring(0,3);
        String left4 = sku.substring(0,4);
        String left5 = sku.substring(0,5);

        if(left4.equals("SM-S") || left4.equals( "SM-G") ||
                left3 .equals("SM5") || left4 .equals("SM-F") || left4.equals("SM-A") ) return "HHP";

        if(left3.equals("SM-L") || left5.equals( "SM-R8") ||
                left5 .equals("SM-R9")) return "Watch";
        if(left3.equals("SM-R")) return "Wearable";

        if(left1.equals("E") || left1.equals( "G") ||
                left1.equals("O") ) return "ACC";

        if(left4.equals("SM-W") || left4.equals( "SM-P") ||
                left4 .equals("SM-X")  ) return "TAB";

        if(left2.equals("XE") || left2.equals("NP")) return "PC";

        if(left1.equals("M")) return "Memeory";

        if(left2.equals("HA")) return "Water Filter";


        return "Other";
    }
}
