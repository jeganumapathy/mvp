package com.m.mvp.logic;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.m.mvp.view.adapter.SMSViewAdapter;

import java.util.LinkedList;

/**
 * Created by test on 7/20/15.
 */
public class SMSReader {

    LinkedList<SMSViewAdapter.DataHolder> listOfSMS = new LinkedList<>();

    public LinkedList<SMSViewAdapter.DataHolder> readSMS(Context context) {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                SMSViewAdapter.DataHolder dataHolder = new SMSViewAdapter.DataHolder();
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    String key =cursor.getColumnName(idx);
                    String value =  cursor.getString(idx);
                    if ("body".equalsIgnoreCase(key)) {
                        dataHolder.body = value;
                    }
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                listOfSMS.add(dataHolder);
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        cursor.close();
        return listOfSMS;
    }

    public void formatSMStoCredit() {
    }

    public void formatSMStoDebit() {
    }
}
