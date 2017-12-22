package com.headysample.presenter;

import com.headysample.view.BaseView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nndra on 06-Dec-17.
 */

public class BasePresenter<V extends BaseView>{

    @Inject protected V mView;

    protected V getView() {
        return mView;
    }

    protected  <T> void subcribe(Observable<T> observable, Observer observer) {

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
