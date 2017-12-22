
package com.headysample.model.db;


import com.headysample.model.response.AllData;

import java.util.List;

import dagger.Provides;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HeadyDb extends RealmObject {

    @PrimaryKey
    public int id;

    public RealmList<Rankings> rankings = new RealmList<>();

    public RealmList<Categories> categories = new RealmList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Rankings> getRankings ()
    {
        return rankings;
    }

    public void setRankings (RealmList<Rankings> rankings)
    {
        this.rankings = rankings;
    }

    public RealmList<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (RealmList<Categories> categories)
    {
        this.categories = categories;
    }

}

