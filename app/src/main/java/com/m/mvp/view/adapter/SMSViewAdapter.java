package com.m.mvp.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;

/**
 * Created by test on 7/22/15.
 */
public class SMSViewAdapter extends BaseAdapter {
    private LinkedList<DataHolder> listOfItems;

    public SMSViewAdapter(LinkedList<DataHolder> listOfItems) {
        this.listOfItems = listOfItems;
    }


    @Override
    public int getCount() {
        return listOfItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


    public class DataHolder {
    }

}
