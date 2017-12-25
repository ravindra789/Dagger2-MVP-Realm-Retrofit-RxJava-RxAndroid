package com.headysample.presenter;


import com.headysample.model.db.Categories;
import com.headysample.model.db.HeadyDb;
import com.headysample.model.db.HeadyMapper;
import com.headysample.model.response.AllData;
import com.headysample.util.RestApi;
import com.headysample.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


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
        getDataFromDatabase();
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
        if(dbData != null){
            System.out.println("Heady db size - "+dbData.toString());
            return true;
        }else{
            return false;
        }
    }

    public void getDataForIdFromDatabase(String id) {
        realm = Realm.getDefaultInstance();
        /*HeadyDb dbData = realm.where(HeadyDb.class)
                .findFirst();*/
       // if(dbData != null){
            Categories categories = realm.where(Categories.class)
                    .equalTo("id",id)
                    .findFirst();
        //}

        System.out.println("Heady db presenter ID - "+categories.toString());
        //getView().onClearItems();
        getView().onFilteredDataLoaded(categories);

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

   /* void filter(String searchString){
        List<Categories> realmResults = new ArrayList();

        if (searchString == null || "".equals(searchString)) {

            appUsersData = realm.where(Categories.class).equalTo("user_status",1).findAllSorted("user_name", Sort.ASCENDING);
            nonAppUsersData = realm.where(Categories.class).equalTo("user_status",0).findAllSorted("first_name_on_device", Sort.ASCENDING);

            realmResults.addAll(appUsersData);
            realmResults.addAll(nonAppUsersData);

            *//*realmResults = realm.where(QuiphTable.class)
                    .findAll();*//*
        } else {
            realmResults = realm.where(Categories.class)
                    .contains("first_name_on_device", searchString, Case.INSENSITIVE)
                    .or()
                    .contains("user_name", searchString, Case.INSENSITIVE)
                    .findAll();
        }
        mAdapter.updateList(realmResults);

    }*/

}
