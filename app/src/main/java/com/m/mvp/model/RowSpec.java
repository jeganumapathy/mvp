package com.m.mvp.model;

import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by test on 7/16/15.
 */
@TableModelSpec(className = "Row", tableName = "record")
public class RowSpec {

    public String cNo ="";
    public String trans ="";
    public String cDate ="";
    public String creditValue ="";
    public String debitValue ="";
    public String balanceValue ="";
}