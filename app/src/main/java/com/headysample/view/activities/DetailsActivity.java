package com.headysample.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.headysample.R;
import com.headysample.application.HeadyApplication;
import com.headysample.di.component.DaggerHeadyComponent;
import com.headysample.di.module.HeadyModule;
import com.headysample.model.db.Categories;
import com.headysample.model.db.HeadyDb;
import com.headysample.model.response.AllData;
import com.headysample.presenter.HeadyPresenter;
import com.headysample.view.MainView;
import com.headysample.view.adapter.DetailAdapter;

import javax.inject.Inject;

/**
 * Created by nndra on 06-Dec-17.
 */

public class DetailsActivity extends BaseActivity implements MainView {

    @Inject protected HeadyPresenter headyPresenter;
    protected RecyclerView recyclerView;
    private DetailAdapter detailAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_detail);
        initializeList();
        String id = getIntent().getExtras().getString("Id");
        if(headyPresenter != null) {
            System.out.println("Heady Details - "+headyPresenter);
            headyPresenter.getDataForIdFromDatabase(id);
        }else{
            System.out.println("Heady Details - "+headyPresenter);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_details;
    }

    @Override
    protected void resolveDaggerDependancy() {
        DaggerHeadyComponent.builder()
                .applicationComponent(getApplicationComponent())
                .headyModule(new HeadyModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initializeList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        detailAdapter = new DetailAdapter(this,getLayoutInflater());
        recyclerView.setAdapter(detailAdapter);
    }


    @Override
    public void onDataLoaded(AllData allData) {

    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String msg) {
        Toast.makeText(DetailsActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowDialog(String msg) {
        showDialog(msg);
    }

    @Override
    public void onClearItems() {
        // mainAdapter.clearData();
    }

    @Override
    public void onDataLoadedFromDb(HeadyDb dbData) {

    }

    @Override
    public void onFilteredDataLoaded(Categories categories) {
        detailAdapter.addDbData(categories);
    }
}
