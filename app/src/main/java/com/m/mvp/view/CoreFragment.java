package com.m.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m.mvp.presenter.CorePresenter;
import com.m.mvp.presenter.UsePresenter;

import java.util.HashMap;

/**
 * Created by jegan on 6/9/15.
 */
public abstract class CoreFragment<Presenter extends CorePresenter> extends Fragment {


    private static final String TAG = CoreFragment.class.getSimpleName();

    private static final String PRESENTER_ID_KEY = "id";

    private static final String PRESENTER_STATE_KEY = "state";

    private static HashMap<String, CorePresenter> idToPresenter = new HashMap<>();

    private static HashMap<CorePresenter, String> presenterToId = new HashMap<>();

    private int nextId;

    private Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = provide(findPresenterClass(getClass()), savedInstanceState);
        if (mPresenter != null) mPresenter.setCoreContext(getActivity());
    }

    @NonNull
    protected View findViewById(int resId) {
        return getView().findViewById(resId);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        takeView(this);
    }


    public Presenter getPresenter() {
        return mPresenter;
    }

    private Class<Presenter> findPresenterClass(Class<?> viewClass) {
        UsePresenter annotation = viewClass.getAnnotation(UsePresenter.class);
        return annotation == null ? null : (Class<Presenter>) annotation.value();
    }


    private <Presenter extends CorePresenter> Presenter provide(Class presenterClass, Bundle savedState) {
        String id = providePresenterId(presenterClass, savedState);
        if (idToPresenter.containsKey(id)) {
            return (Presenter) idToPresenter.get(id);
        }
        CorePresenter presenter = instantiatePresenter(presenterClass, id);
        return (Presenter) presenter;
    }


    private CorePresenter instantiatePresenter(Class<? extends CorePresenter> presenterClass,
                                               String id) {
        CorePresenter presenter;
        try {
            presenter = presenterClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return presenter;
    }


    private String providePresenterId(Class<? extends CorePresenter> presenterClass,
                                      Bundle savedState) {
        return savedState != null ? savedState.getString(PRESENTER_ID_KEY) :
                CoreFragment.class.getSimpleName() + " (" + nextId++ + "/" + System.nanoTime() + "/"
                        + (int) (Math.random() * Integer.MAX_VALUE) + ")";
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void takeView(Object view) {
        if (mPresenter != null) {
            mPresenter.setCoreContext(getActivity());
            mPresenter.onTakeView(view);
        }
    }
}
