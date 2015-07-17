package com.m.mvp;

import android.app.Application;
import android.content.Context;

import com.m.mvp.database.DaoFactory;

/**
 * Created by test on 7/16/15.
 */
public class MainApplication  extends Application{

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DaoFactory.init(context);
    }
}
