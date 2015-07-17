package com.m.mvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.m.mvp.model.Row;
import com.yahoo.squidb.data.AbstractDatabase;
import com.yahoo.squidb.sql.Table;

/**
 * Created by test on 7/16/15.
 */
public class CheckBookDataBase extends AbstractDatabase {

    private static final int VERSION = 1;

    public CheckBookDataBase(Context context) {
        super(context);
    }

    @Override
    protected String getName() {
        return "my-database.db";
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                // List all tables here
                Row.TABLE,
        };
    }

    @Override
    protected boolean onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        return false;
    }

    @Override
    protected int getVersion() {
        return VERSION;
    }


}
