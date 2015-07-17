package com.m.mvp.logic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.m.mvp.R;
import com.m.mvp.model.Row;
import com.m.mvp.widgets.DatePickerEditText;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by test on 7/3/15.
 */
public class CheckBookRowFactory implements TableRowFactory {


    private Context mContext;
    private TableRow.LayoutParams mTableRowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
    private static final int HEADER_TEXT_COLOR = Color.BLACK;
    private CheckBookRowTemplate rowTemplate;
    private BalanceCalculator balanceCalculator;

    public CheckBookRowFactory(Context context) {
        this.rowTemplate = new CheckBookRowTemplate();
        this.mContext = context;
        this.balanceCalculator = new BalanceCalculator();
    }

    @Override
    public void makeRow(TableLayout parentLayout) {
        Row mRow = new Row();
        mRow.setBalanceValue("0");//set default to zero
        createUIRow(parentLayout, mRow);
    }

    private void createUIRow(TableLayout parentLayout, Row mRow) {
        TableRow row = new TableRow(mContext);
        row.setTag(mRow);
        LinkedHashMap<String, TableRow.LayoutParams> listHeader = rowTemplate.generateTemplate(mContext);
        int padding = rowTemplate.padding;
        row.setLayoutParams(mTableRowLayoutParams);
        for (Map.Entry<String, TableRow.LayoutParams> entry : listHeader.entrySet()) {
            String key = entry.getKey();
            TableRow.LayoutParams value = entry.getValue();
            row.addView(configRow(key, value, mRow));
        }
        row.startAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in));
        parentLayout.addView(row);

    }

    @Override
    public void preLoadRow(TableLayout parentLayout, List<Row> rows) {
        for (Row row : rows) {
            createUIRow(parentLayout, row);
        }
    }


    private View configRow(String key, TableRow.LayoutParams value, Row mRow) {
        EditText mEditText = new EditText(mContext);
        mEditText.setSingleLine(true);
        mEditText.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.table_border));
        mEditText.setLayoutParams(value);
        mEditText.setTag(key);

        EditTextTextWatcher mEditTextTextWatcher = new EditTextTextWatcher(mEditText, mRow);
        mEditText.addTextChangedListener(mEditTextTextWatcher);
        if (CheckBookRowTemplate.HEADER_X.equalsIgnoreCase(key)) {
            if (mRow != null && mRow.containsValue(Row.ID)) {
                mEditText.setText("" + mRow.getId());
            }
            return mEditText;
        } else if (CheckBookRowTemplate.HEADER_DATE.equalsIgnoreCase(key)) {
            DatePickerEditText mDatePickerEditText = new DatePickerEditText(mContext);
            mDatePickerEditText.setTag(key);
            mDatePickerEditText.setSingleLine(true);
            mDatePickerEditText.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.table_border));
            mDatePickerEditText.setLayoutParams(value);
            return mDatePickerEditText;
        } else if (CheckBookRowTemplate.HEADER_CHECK_NO.equalsIgnoreCase(key)) {
            if (mRow != null && mRow.containsValue(Row.C_NO)) {
                mEditText.setText(mRow.getCNo());
            }
            return mEditText;
        } else if (CheckBookRowTemplate.HEADER_TRANSACTION.equalsIgnoreCase(key)) {
            if (mRow != null && mRow.containsValue(Row.TRANS)) {
                mEditText.setText(mRow.getTrans());
            }
            return mEditText;
        } else if (CheckBookRowTemplate.HEADER_CREDIT.equalsIgnoreCase(key)) {
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            mEditText.setTextColor(Color.GREEN);
            if (mRow != null && mRow.containsValue(Row.CREDIT_VALUE)) {
                mEditText.setText(mRow.getCreditValue());
            }
            return mEditText;

        } else if (CheckBookRowTemplate.HEADER_DEBIT.equalsIgnoreCase(key)) {
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            mEditText.setTextColor(Color.RED);
            if (mRow != null && mRow.containsValue(Row.DEBIT_VALUE)) {
                mEditText.setText(mRow.getDebitValue());
            }
            return mEditText;
        } else if (CheckBookRowTemplate.HEADER_BALANCE.equalsIgnoreCase(key)) {
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            mEditText.setEnabled(false);
            if (mRow != null && mRow.containsValue(Row.BALANCE_VALUE)) {
                mEditText.setText(mRow.getBalanceValue());
            }
            return mEditText;
        } else {
            return mEditText;
        }
    }

    public void calculateCurrentBalance(Row mRow){
        if (mRow != null && mRow.containsValue(Row.DEBIT_VALUE) && mRow.containsValue(Row.CREDIT_VALUE) ) {
            String balance = balanceCalculator.calculateBalance(mRow.getCreditValue(), mRow.getDebitValue());
            mRow.setBalanceValue(balance);
        }
    }

    public void calculateTotalBalance(Row mRow){
        if (mRow != null && mRow.containsValue(Row.BALANCE_VALUE) ) {
            String balance = balanceCalculator.calculateTotalBalance(mRow.getBalanceValue());
        }
    }


    public class EditTextTextWatcher implements TextWatcher {
        EditText mEditText;
        Row mRow;

        public EditTextTextWatcher(EditText mEditText, Row mRow) {
            this.mEditText = mEditText;
            this.mRow = mRow;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String key = (String) mEditText.getTag();
            String value = editable.toString();
            if (CheckBookRowTemplate.HEADER_X.equalsIgnoreCase(key)) {
            } else if (CheckBookRowTemplate.HEADER_DATE.equalsIgnoreCase(key)) {
                mRow.setCDate(value);
            } else if (CheckBookRowTemplate.HEADER_CHECK_NO.equalsIgnoreCase(key)) {
                mRow.setCNo(value);
            } else if (CheckBookRowTemplate.HEADER_TRANSACTION.equalsIgnoreCase(key)) {
                mRow.setTrans(value);
            } else if (CheckBookRowTemplate.HEADER_CREDIT.equalsIgnoreCase(key)) {
                mRow.setCreditValue(value);
            } else if (CheckBookRowTemplate.HEADER_DEBIT.equalsIgnoreCase(key)) {
                mRow.setDebitValue(value);
            } else if (CheckBookRowTemplate.HEADER_BALANCE.equalsIgnoreCase(key)) {
                mRow.setBalanceValue(value);
            }

        }
    }


    @Override
    public CheckBookRowFactory makeHeaderRow(TableLayout parentLayout) {
        if (parentLayout == null) {
            throw new RuntimeException("Header or parent layout cannot be null");
        }
        TableRow row = new TableRow(mContext);
        LinkedHashMap<String, TableRow.LayoutParams> listHeader = rowTemplate.generateTemplate(mContext);
        int padding = rowTemplate.padding;
        row.setLayoutParams(mTableRowLayoutParams);
        for (Map.Entry<String, TableRow.LayoutParams> entry : listHeader.entrySet()) {
            String key = entry.getKey();
            TableRow.LayoutParams value = entry.getValue();
            TextView mTextView = new TextView(mContext);
            mTextView.setTypeface(null, Typeface.BOLD);
            mTextView.setTextColor(HEADER_TEXT_COLOR);
            mTextView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.table_border));
            mTextView.setLayoutParams(value);
            mTextView.setPadding(padding, padding, padding, padding);
            mTextView.setText(key);
            row.addView(mTextView);
        }
        row.startAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in));
        parentLayout.addView(row);
        return this;
    }
}
