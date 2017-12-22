package com.headysample.application;

import android.app.Application;

import com.headysample.di.component.ApplicationComponent;
import com.headysample.di.component.DaggerApplicationComponent;
import com.headysample.di.module.ApplicationModule;

import io.realm.Realm;

/**
 * Created by nndra on 06-Dec-17.
 */

public class HeadyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initializeApplicationModules();
    }

    private void initializeApplicationModules() {

        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "https://stark-spire-93433.herokuapp.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
