package com.m.mvp.database;

import android.content.Context;

import com.m.mvp.MainApplication;
import com.m.mvp.model.Row;
import com.yahoo.squidb.data.DatabaseDao;
import com.yahoo.squidb.sql.Criterion;

/**
 * Created by test on 7/16/15.
 */
public class DaoFactory {
    private static CheckBookDataBase dataBase;
    private static DatabaseDao dao;
    private static DaoFactory instance;

    private DaoFactory(Context context) {
        dataBase = new CheckBookDataBase(context);
        dao = new DatabaseDao(dataBase);
    }

    public static DaoFactory init(Context context) {
        if (instance == null) {
            instance = new DaoFactory(context);
        }
        return instance;
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory(MainApplication.context);
        }
        return instance;
    }


    public DatabaseDao getDao() {
        return dao;
    }


    public void deleteAllData() {
        dao.deleteWhere(Row.class, Criterion.all);
    }


}
