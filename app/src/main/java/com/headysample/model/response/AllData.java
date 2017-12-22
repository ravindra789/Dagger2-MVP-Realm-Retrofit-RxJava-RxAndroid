package com.headysample.model.response;

import com.headysample.model.Tax;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by nndra on 06-Dec-17.
 */

public class AllData {

    private List<Rankings> rankings;

    private List<Categories> categories;

    public List<Rankings> getRankings ()
    {
        return rankings;
    }

    public void setRankings (List<Rankings> rankings)
    {
        this.rankings = rankings;
    }

    public List<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (List<Categories> categories)
    {
        this.categories = categories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rankings = "+rankings+", categories = "+categories+"]";
    }

    public class Rankings
    {
        private List<Products> products;

        private String ranking;

        public List<Products> getProducts ()
        {
            return products;
        }

        public void setProducts (List<Products> products)
        {
            this.products = products;
        }

        public String getRanking ()
        {
            return ranking;
        }

        public void setRanking (String ranking)
        {
            this.ranking = ranking;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [products = "+products+", ranking = "+ranking+"]";
        }
    }

    public class Products
    {
        private String id;

        private Tax tax;

        private String date_added;

        private String name;

        private List<Variants> variants;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public Tax getTax ()
        {
            return tax;
        }

        public void setTax (Tax tax)
        {
            this.tax = tax;
        }

        public String getDate_added ()
        {
            return date_added;
        }

        public void setDate_added (String date_added)
        {
            this.date_added = date_added;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public List<Variants> getVariants ()
        {
            return variants;
        }

        public void setVariants (List<Variants> variants)
        {
            this.variants = variants;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", tax = "+tax+", date_added = "+date_added+", name = "+name+", variants = "+variants+"]";
        }
    }

    public class Categories
    {
        private String id;

        private String name;

        private List<String> child_categories;

        private List<Products> products;

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

        public List<String> getChild_categories ()
        {
            return child_categories;
        }

        public void setChild_categories (List<String> child_categories)
        {
            this.child_categories = child_categories;
        }

        public List<Products> getProducts ()
        {
            return products;
        }

        public void setProducts (List<Products> products)
        {
            this.products = products;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", name = "+name+", child_categories = "+child_categories+", products = "+products+"]";
        }
    }

    public class Variants
    {
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

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", price = "+price+", color = "+color+", size = "+size+"]";
        }
    }

}
