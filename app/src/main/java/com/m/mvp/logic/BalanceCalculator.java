package com.m.mvp.logic;

/**
 * Created by test on 7/17/15.
 */
public class BalanceCalculator {

    public static float _total_balance = 0;

    public static float convertStringToFloat(String stringFloat) {
        float f = 0;
        try {
            f = Float.parseFloat(stringFloat);
        } catch (NumberFormatException ne) {
        }
        return f;
    }

    public static String convertFloatToString(float _float) {
        String s = "";
        try {
            s = Float.toString(_float);
        } catch (NumberFormatException ne) {
        }
        return s;
    }

    public String calculateBalance(String credit, String debit) {
        float _credit = convertStringToFloat(credit);
        float _debit = convertStringToFloat(debit);
        float _current_balance = ( _credit -_debit );
        return convertFloatToString(_current_balance);
    }

    public String calculateTotalBalance(String balance) {
        float _balance = convertStringToFloat(balance);
        _total_balance = _total_balance + _balance;
        return convertFloatToString(_total_balance);
    }
}
