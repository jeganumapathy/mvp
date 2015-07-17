package com.m.mvp.presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.m.mvp.R;

import java.util.ArrayList;

/**
 * Created by test on 6/9/15.
 */
public class HomePresenter  extends CorePresenter<HomePresenter.Delegate> {

    public interface Delegate  {
        void loadHomeScreen();
    }

    @Override
    public void onTakeView(Delegate delegate) {
        super.onTakeView(delegate);
        delegate.loadHomeScreen();
    }
}
