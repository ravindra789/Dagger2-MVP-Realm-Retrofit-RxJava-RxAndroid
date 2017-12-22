package com.headysample.model.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nndra on 20-Dec-17.
 */

public class Categories extends RealmObject {

    @PrimaryKey
    private String id;

    private String name;

    public RealmList<String> child_categories = new RealmList<>();

    public RealmList<Products> products = new RealmList<>();



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public RealmList<String> getChild_categories ()
    {
        return child_categories;
    }

    public void setChild_categories (RealmList<String> child_categories)
    {
        this.child_categories = child_categories;
    }

    public RealmList<Products> getProducts ()
    {
        return products;
    }

    public void setProducts (RealmList<Products> products)
    {
        this.products = products;
    }
}
