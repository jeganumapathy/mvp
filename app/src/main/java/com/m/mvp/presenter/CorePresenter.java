package com.m.mvp.presenter;

import android.content.Context;


/**
 * Created by test on 6/9/15.
 */
public class CorePresenter<T>   {

    private Context context;

    private T delegate;

    protected void register(){
    }

    public void onTakeView(T delegate) {
        this.delegate = delegate;
    }

    public void setCoreContext(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

}
