package com.m.mvp.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.m.mvp.logic.SMSReader;

import java.util.LinkedList;

/**
 */
public class SMSListFragment extends ListFragment {

    LinkedList<String> listOfSMS;

    public static SMSListFragment getNewInstance(Bundle args) {
        SMSListFragment mInstance = new SMSListFragment();
        mInstance.setArguments(args);
        return mInstance;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMSReader smsReader = new SMSReader();
        listOfSMS = smsReader.readSMS(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                listOfSMS);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
