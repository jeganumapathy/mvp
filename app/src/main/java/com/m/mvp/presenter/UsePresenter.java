package com.m.mvp.presenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by test on 6/9/15.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface UsePresenter {

    Class<? extends CorePresenter> value();
}

