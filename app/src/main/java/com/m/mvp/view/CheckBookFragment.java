package com.m.mvp.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.m.mvp.R;
import com.m.mvp.database.CheckBookDataBase;
import com.m.mvp.database.DaoFactory;
import com.m.mvp.logic.BalanceCalculator;
import com.m.mvp.logic.CheckBookRowFactory;
import com.m.mvp.logic.CheckBookRowTemplate;
import com.m.mvp.model.Row;
import com.m.mvp.presenter.CheckBookPresenter;
import com.m.mvp.presenter.UsePresenter;
import com.yahoo.squidb.data.DatabaseDao;

import java.util.List;

/**
 * Created by test on 7/1/15.
 */
@UsePresenter(CheckBookPresenter.class)
public class CheckBookFragment extends CoreFragment implements CheckBookPresenter.Delegate {

    private CheckBookRowFactory mFactory;
    private TableLayout checkLayout;
    private Handler mHandler = new Handler();
    private TextView totalBalance;

    public static CheckBookFragment getNewInstance(Bundle args) {
        CheckBookFragment mInstance = new CheckBookFragment();
        mInstance.setArguments(args);
        return mInstance;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkbook, container, false);
        //DO YOUR INITIALIZATION HERE
        mFactory = new CheckBookRowFactory(getActivity());
        checkLayout = (TableLayout) rootView.findViewById(R.id.table_check_book);
        totalBalance = (TextView) rootView.findViewById(R.id.total_balance);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void loadCheckBookScreen() {
        Button addNewRow = (Button) findViewById(R.id.table_add_new_row);
        Button saveRow = (Button) findViewById(R.id.table_save_row);
        addNewRow.setOnClickListener(addNewRowListener);
        saveRow.setOnClickListener(saveRowListener);
        mFactory.makeHeaderRow(checkLayout);
    }

    @Override
    public void loadRowFromDataBase(final List<Row> rows) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFactory.preLoadRow(checkLayout, rows);
                totalBalance.setText("Total Balance" + mFactory.balanceCalculator._total_balance);
            }
        });

    }

    @Override
    public DatabaseDao getDao() {
        return DaoFactory.getInstance().getDao();
    }

    @Override
    public CheckBookRowFactory getFactory() {
        return mFactory;
    }

    private View.OnClickListener saveRowListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mHandler.post(mSaveRunnable);
        }
    };


    private Runnable mSaveRunnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0, j = checkLayout.getChildCount(); i < j; i++) {
                View view = checkLayout.getChildAt(i);
                if (view instanceof TableRow) {
                    TableRow row = (TableRow) view;
                    Row mData = (Row) row.getTag();
                    try {
                        if (mData != null) {
                            mFactory.calculateCurrentBalance(mData);
                            getDao().persist(mData);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };


    private View.OnClickListener addNewRowListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mFactory.makeRow(checkLayout);
        }
    };
}
