package com.headysample.model.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nndra on 20-Dec-17.
 */

public class Variants extends RealmObject {

    @PrimaryKey
    private String id;

    private String price;

    private String color;

    private String size;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

}
