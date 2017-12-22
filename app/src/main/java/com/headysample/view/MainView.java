package com.headysample.view;

import com.headysample.model.db.HeadyDb;
import com.headysample.model.response.AllData;

import io.realm.RealmResults;

/**
 * Created by nndra on 06-Dec-17.
 */

public interface MainView extends BaseView {

    void onDataLoaded(AllData allData);

    void onHideDialog();

    void onShowToast(String msg);

    void onShowDialog(String msg);

    void onClearItems();

    void onDataLoadedFromDb(HeadyDb dbData);

    void onFilteredDataLoaded(HeadyDb dbData);

}
