package com.m.mvp.logic;

import android.content.Context;
import android.widget.TableLayout;

import com.m.mvp.model.Row;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public interface TableRowFactory {
    void makeRow(TableLayout parentLayout);
    void preLoadRow(TableLayout parentLayout,List<Row> rows);
    TableRowFactory makeHeaderRow(TableLayout parentLayout);
}
