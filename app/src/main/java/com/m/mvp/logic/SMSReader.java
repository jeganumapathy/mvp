package com.m.mvp.logic;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.LinkedList;

/**
 * Created by test on 7/20/15.
 */
public class SMSReader {

    LinkedList<String> listOfSMS = new LinkedList<>();

    public LinkedList<String> readSMS(Context context) {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    if ("body".equalsIgnoreCase(cursor.getColumnName(idx))) {
                    }
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                listOfSMS.add(msgData);
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
