package com.m.mvp.logic;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by test on 7/20/15.
 */
public class SMSReader {

    public void readSMS(Context context) {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
    }

    public void formatSMStoCredit(){
    }

    public void formatSMStoDebit(){
    }
}
