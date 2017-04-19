package com.mohse78.bankcardbase.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ab.mohammadi on 2/5/2017.
 */
public class getStringSplitedWithNumber {

    public static String setCardNumber4Digits(String cardNumber , int numberForStringSplit) {

        char[] chars = cardNumber.toCharArray();
        StringBuilder cardNumberNew = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (i % numberForStringSplit == 0) {
                cardNumberNew.append(" ");
            }
            cardNumberNew.append(chars[i]);
        }
        return cardNumberNew.toString();

    }

}
