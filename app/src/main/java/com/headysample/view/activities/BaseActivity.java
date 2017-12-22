package com.headysample.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.headysample.application.HeadyApplication;
import com.headysample.di.component.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by nndra on 06-Dec-17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Heady BaseActivity.");
        setContentView(getContentView());
        onViewReady(savedInstanceState,getIntent());
    }

    @Override
    protected void onDestroy() {
        //ButterKnife.unbind(this);
        super.onDestroy();
    }


    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependancy();
    }

    protected void resolveDaggerDependancy() {
    }

    protected void showDialog(String message){
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
    protected abstract int getContentView();

    protected ApplicationComponent getApplicationComponent(){
        //HeadyApplication headyApplication = (HeadyApplication) getApplication();
        //return headyApplication.getApplicationComponent();

        System.out.println("Heady - headyApplication");
       return ((HeadyApplication) getApplication()).getApplicationComponent();
    }
}
