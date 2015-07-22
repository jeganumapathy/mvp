package com.m.mvp.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.FragmentManager;

import com.m.mvp.R;
import com.m.mvp.logic.SMSReader;
import com.m.mvp.view.adapter.SMSViewAdapter;

import java.util.LinkedList;

/**
 */
public class SMSListFragment extends ListFragment {

    LinkedList<SMSViewAdapter.DataHolder> listOfSMS;

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
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, SMSListFragment.getNewInstance(null)).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SMSViewAdapter viewAdapter = new SMSViewAdapter(listOfSMS,inflater.getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                listOfSMS);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
