package com.headysample.model.db;

import io.realm.RealmObject;

/**
 * Created by nndra on 20-Dec-17.
 */
public class RankingProducts extends RealmObject {

    private String id;
    private String order_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
    }
}