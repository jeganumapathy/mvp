package com.m.mvp.view.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.m.mvp.R;

import java.util.LinkedList;

/**
 * Created by test on 7/22/15.
 */
public class SMSViewAdapter extends BaseAdapter {
    private LinkedList<DataHolder> listOfItems;
    private Context context;
    private LayoutInflater inflater;

    public SMSViewAdapter(LinkedList<DataHolder> listOfItems, Context context) {
        this.listOfItems = listOfItems;
        if (listOfItems == null) {
            throw new RuntimeException("List item should not be null");
        }
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listOfItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_sms_view, null);
            mViewHolder = new ViewHolder();
            mViewHolder.creditRadio = (RadioButton) convertView.findViewById(R.id.credit_radio);
            mViewHolder.debitRadio = (RadioButton) convertView.findViewById(R.id.debit_radio);
            mViewHolder.smsBodyText = (TextView) convertView.findViewById(R.id.sms_body);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        populateView(i, mViewHolder);
        return convertView;
    }

    private void populateView(int position, ViewHolder mViewHolder) {
        DataHolder dataHolder = (DataHolder) getItem(position);
        mViewHolder.smsBodyText.setText("" + dataHolder.body);
        mViewHolder.creditRadio.setChecked(dataHolder.isCreditTemplate);
        mViewHolder.debitRadio.setChecked(dataHolder.isDebitTemplate);
    }

    public class ViewHolder {
        TextView smsBodyText;
        RadioButton creditRadio;
        RadioButton debitRadio;
    }


    public static class DataHolder {
        public String body;
        public String sender;
        public boolean isCreditTemplate;
        public boolean isDebitTemplate;
    }
}
