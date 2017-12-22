package com.headysample.presenter;


import com.headysample.model.db.HeadyDb;
import com.headysample.model.db.HeadyMapper;
import com.headysample.model.response.AllData;
import com.headysample.util.RestApi;
import com.headysample.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by nndra on 06-Dec-17.
 */

public class HeadyPresenter extends BasePresenter<MainView> implements Observer<AllData>{

    @Inject
    protected  RestApi mApiService;
    @Inject
    protected  HeadyMapper headyMapper;
    //@Inject protected HeadyDb headyDb;
    Realm realm;

    @Inject
    public HeadyPresenter() {
        //HeadyMapper headyMapper = new HeadyMapper(this);
    }

    public void getData(){
        getView().onShowDialog("Loading data..");
       // System.out.println("Heady Presenter APIService - "+mApiService.getAllData());
        Observable<AllData> allDataObservable = mApiService.getAllData();
        subcribe(allDataObservable,this);
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(AllData allData) {
        headyMapper.mapAllDatatoDb(allData);
        System.out.println("Heady db next - "+allData.toString());
        //getView().onDataLoaded(allData);

        //need to add db logic here here

    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading data "+e.getMessage());
        System.out.println("Heady error - "+e.toString());
    }

    @Override
    public void onComplete() {
       // getView().onHideDialog();
        getView().onShowToast("Data loaded.");
    }

    public void getDataFromDatabase() {
        realm = Realm.getDefaultInstance();
        HeadyDb dbData = realm.where(HeadyDb.class)
                .findFirst();
        System.out.println("Heady db presenter - "+dbData.toString());
        getView().onClearItems();
        getView().onDataLoadedFromDb(dbData);
        getView().onHideDialog();
    }

    public boolean dbCount(){
        realm = Realm.getDefaultInstance();
        HeadyDb dbData = realm.where(HeadyDb.class)
                .findFirst();
        System.out.println("Heady db size - "+dbData.toString());
        if(dbData != null){
            return true;
        }else{
            return false;
        }
    }

    public void getDataForIdFromDatabase(String id) {
        realm = Realm.getDefaultInstance();
        HeadyDb dbData = realm.where(HeadyDb.class)
                .equalTo("id",id)
                .findFirst();
        System.out.println("Heady db presenter ID - "+dbData.toString());
        //getView().onClearItems();
        getView().onFilteredDataLoaded(dbData);

        //getView().onHideDialog();
    }


   /* @Override
    public void dataAdded() {
        realm = Realm.getDefaultInstance();
        RealmResults<HeadyDb> dbData = realm.where(HeadyDb.class)
                .findAll();
        System.out.println("Heady db presenter - "+dbData.toString());
        getView().onClearItems();
        getView().onDataLoadedFromDb(dbData);
        getView().onHideDialog();
    }*/
}
