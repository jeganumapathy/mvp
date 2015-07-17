package com.m.mvp.view;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.m.mvp.MainActivity;
import com.m.mvp.R;
import com.m.mvp.presenter.HomePresenter;
import com.m.mvp.presenter.UsePresenter;
import com.m.mvp.widgets.ZoomEditTextView;

/**
 * Created by jegan on 6/8/15.
 */
@UsePresenter(HomePresenter.class)
public class HomeFragment extends CoreFragment implements HomePresenter.Delegate {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public static HomeFragment getNewInstance(Bundle args) {
        HomeFragment mInstance = new HomeFragment();
        mInstance.setArguments(args);
        return mInstance;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //DO YOUR INITIALIZATION HERE
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


    @Override
    public void loadHomeScreen() {
       // TextView mTextView = (TextView) findViewById(R.id.hello_label);
        ZoomEditTextView mEditText = (ZoomEditTextView) findViewById(R.id.edit_query);
    }
}
