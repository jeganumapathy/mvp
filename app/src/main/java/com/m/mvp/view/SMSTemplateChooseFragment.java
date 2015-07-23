package com.m.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m.mvp.R;
import com.m.mvp.presenter.HomePresenter;
import com.m.mvp.presenter.UsePresenter;

/**
 * Created by test on 7/23/15.
 */
@UsePresenter(HomePresenter.class)
public class SMSTemplateChooseFragment extends  CoreFragment implements  HomePresenter.Delegate {

    public static SMSTemplateChooseFragment getNewInstance(Bundle args) {
        SMSTemplateChooseFragment mInstance = new SMSTemplateChooseFragment();
        mInstance.setArguments(args);
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sms_template, container, false);
        return rootView;
    }

    @Override
    public void loadHomeScreen() {
    }
}
