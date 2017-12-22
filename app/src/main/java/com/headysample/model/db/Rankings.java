package com.headysample.model.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by nndra on 20-Dec-17.
 */

public class Rankings extends RealmObject {

    public RealmList<RankingProducts> rankingProducts = new RealmList<>();

    public String ranking;

    public RealmList<RankingProducts> getProducts ()
    {
        return rankingProducts;
    }

    public void setProducts (RealmList<RankingProducts> products)
    {
        this.rankingProducts = products;
    }

    public String getRanking ()
    {
        return ranking;
    }

    public void setRanking (String ranking)
    {
        this.ranking = ranking;
    }

}
