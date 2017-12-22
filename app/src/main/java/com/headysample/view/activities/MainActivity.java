package com.headysample.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.headysample.R;
import com.headysample.di.component.DaggerHeadyComponent;
import com.headysample.di.component.DaggerHeadyComponent;
import com.headysample.di.module.HeadyModule;
import com.headysample.model.db.Categories;
import com.headysample.model.db.HeadyDb;
import com.headysample.model.response.AllData;
import com.headysample.presenter.HeadyPresenter;
import com.headysample.util.NetworkUtils;
import com.headysample.view.MainView;
import com.headysample.view.adapter.MainAdapter;


import javax.inject.Inject;


public class MainActivity extends BaseActivity implements MainView {

    @Inject protected HeadyPresenter headyPresenter;

    private MainAdapter mainAdapter;
    protected RecyclerView recyclerView;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //System.out.println("Heady Main First ");
        initializeList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        if(headyPresenter.dbCount()){
            headyPresenter.getDataFromDatabase();
        }else{
            headyPresenter.getData();
        }
    }

    private void initializeList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mainAdapter = new MainAdapter(this,getLayoutInflater());
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependancy() {
        DaggerHeadyComponent.builder()
                .applicationComponent(getApplicationComponent())
                .headyModule(new HeadyModule(this))
                .build()
                .inject(this);
    }

    private void load() {
        if(NetworkUtils.isNetAvailable(this)) {
            headyPresenter.getData();
        } else {
            headyPresenter.getDataFromDatabase();
        }
    }

    @Override
    public void onDataLoaded(AllData allData) {
       // mainAdapter.addData(allData);
        System.out.println("Heady Main Categories - "+allData.getCategories().size());
        System.out.println("Heady Main Rankings - "+allData.getRankings().size());
        System.out.println("Heady Main - "+allData.toString());
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
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
        System.out.println("Heady Maindb - "+dbData.toString());
        mainAdapter.addDbData(dbData);
    }

    @Override
    public void onFilteredDataLoaded(Categories categories) {

    }
}
