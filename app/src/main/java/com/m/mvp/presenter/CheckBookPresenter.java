package com.m.mvp.presenter;

import android.widget.TextView;

import com.m.mvp.logic.CheckBookRowFactory;
import com.m.mvp.model.Row;
import com.yahoo.squidb.data.DatabaseDao;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CheckBookPresenter extends CorePresenter<CheckBookPresenter.Delegate> {

    Delegate delegate;

    public interface Delegate {

        void loadCheckBookScreen();

        void loadRowFromDataBase(List<Row> rows);

        DatabaseDao getDao();

        CheckBookRowFactory getFactory();
    }

    @Override
    public void onTakeView(Delegate delegate) {
        super.onTakeView(delegate);
        this.delegate = delegate;
        delegate.loadCheckBookScreen();
        reloadRows();
    }

    public void reloadRows(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fetchRows();
            }
        });
        thread.start();

    }

    private void fetchRows() {
        try {
            //Row row = delegate.getDao().fetch(Row.class, 1);
            SquidCursor<Row> rowSquidCursor = delegate.getDao().query(Row.class, Query.select());
            try {
                List<Row> rows = new ArrayList<>();
                while (rowSquidCursor.moveToNext()) {
                    Row row = new Row();
                    row.readPropertiesFromCursor(rowSquidCursor);
                    delegate.getFactory().calculateTotalBalance(row);
                    rows.add(row);
                }
                delegate.loadRowFromDataBase(rows);

            } finally {
                rowSquidCursor.close();
            }
        } catch (Exception e) {
        }
    }


}
