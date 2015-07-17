package com.m.mvp.logic;

import android.content.Context;
import android.widget.EditText;
import android.widget.TableRow;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 */
public class CheckBookRowTemplate {

    private TableRow.LayoutParams mTableRowLayoutParams, mHeaderXParam, mHeaderDateParam, mHeaderCheckNoParam, mHeaderTransParam, mHeaderCreditParam, mHeaderDebitParam, mHeaderBalanceParam;
    public int padding = 10;
    private int totalHeaderCount = 7;
    public static final String HEADER_X = "X";
    public static final String HEADER_DATE = "Date";
    public static final String HEADER_CHECK_NO = "Check No";
    public static final String HEADER_TRANSACTION = "Transaction";
    public static final String HEADER_CREDIT = "Credit";
    public static final String HEADER_DEBIT = "Debit";
    public static final String HEADER_BALANCE = "Balance";
    private static final float WEIGHT_X = 0.2f;
    private static final float WEIGHT_DATE = 0.3f;
    private static final float WEIGHT_CHECK_NO = 0.3f;
    private static final float WEIGHT_TRANSACTION = 2.0f;
    private static final float WEIGHT_CREDIT = 0.8f;
    private static final float WEIGHT_DEBIT = 0.8f;
    private static final float WEIGHT_BALANCE = 0.8f;


    public CheckBookRowTemplate() {
        mTableRowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        mHeaderXParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_X);
        mHeaderDateParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_DATE);
        mHeaderCheckNoParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_CHECK_NO);
        mHeaderTransParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_TRANSACTION);
        mHeaderCreditParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_CREDIT);
        mHeaderDebitParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_DEBIT);
        mHeaderBalanceParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, WEIGHT_BALANCE);
    }


    public LinkedHashMap<String, TableRow.LayoutParams> generateTemplate(Context context) {
        LinkedHashMap<String, TableRow.LayoutParams> listHeader = new LinkedHashMap<String, TableRow.LayoutParams>();
        listHeader.put(HEADER_X, mHeaderXParam);
        listHeader.put(HEADER_DATE, mHeaderDateParam);
        listHeader.put(HEADER_CHECK_NO, mHeaderCheckNoParam);
        listHeader.put(HEADER_TRANSACTION, mHeaderTransParam);
        listHeader.put(HEADER_CREDIT, mHeaderCreditParam);
        listHeader.put(HEADER_DEBIT, mHeaderDebitParam);
        listHeader.put(HEADER_BALANCE, mHeaderBalanceParam);
        return listHeader;
    }

}
