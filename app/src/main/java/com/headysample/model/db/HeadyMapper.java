package com.headysample.model.db;

import com.headysample.model.response.AllData;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by nndra on 07-Dec-17.
 */

public class HeadyMapper {



    private Realm realm;
   // private DbUpdateCompleted dbUpdateCompleted ;



    @Inject
    public HeadyMapper() {
        //this.dbUpdateCompleted = dbUpdateCompleted;
    }

    public void mapAllDatatoDb(final AllData allData){

        if(allData != null){
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    System.out.println("Heady Mapper - mapAllDatatoDb");
                    addDataToDB(bgRealm,allData);

                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    // Transaction was a success.
                    System.out.println("Heady Mapper - addDataToDB - success");
                    //HeadyPresenter.DataAddedToDB();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    // Transaction failed and was automatically canceled.
                    System.out.println("Heady Mapper - addDataToDB - error "+error.getMessage());
                }
            });

            //dbUpdateCompleted.dataAdded();
        }
    }

    private void addDataToDB(Realm bgRealm, AllData allData) {


        // increatement index
        Number maxId = bgRealm.where(HeadyDb.class).max("id");
        int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
        HeadyDb headyDb = bgRealm.createObject(HeadyDb.class,nextId);

        //Categories
        RealmList<Categories> categoriesList = new RealmList<Categories>();
        System.out.println("Heady Mapper - allData "+allData.getCategories().size());
        for(int i=0;i< allData.getCategories().size();i++){
            Categories categories = new Categories();
            categories.setId(allData.getCategories().get(i).getId());
            categories.setName(allData.getCategories().get(i).getName());


            RealmList<String> ChildCategoriesList = new RealmList<String>();
            System.out.println("Heady Mapper - getChild_categories "+allData.getCategories().get(i).getChild_categories().size());
            for(int r=0; r<allData.getCategories().get(i).getChild_categories().size();r++){
                ChildCategoriesList.add(allData.getCategories().get(i).getChild_categories().get(r));
            }
            //if(ChildCategoriesList.size() > 0)
            categories.child_categories.addAll(ChildCategoriesList);

            RealmList<Products> productsList = new RealmList<Products>();
            System.out.println("Heady Mapper - getProducts "+allData.getCategories().get(i).getProducts().size());
            for(int j=0; j<allData.getCategories().get(i).getProducts().size(); j++){

                Products products = new Products();
                products.setDate_added(allData.getCategories().get(i).getProducts().get(j).getDate_added());
                products.setId(allData.getCategories().get(i).getProducts().get(j).getId());
                products.setName(allData.getCategories().get(i).getProducts().get(j).getName());
                products.setTax(allData.getCategories().get(i).getProducts().get(j).getTax());

                RealmList<Variants> variantsList = new RealmList<Variants>();
                System.out.println("Heady Mapper - getVariants "+allData.getCategories().get(i).getProducts().get(j).getVariants().size());
                for(int k=0; k < allData.getCategories().get(i).getProducts().get(j).getVariants().size();k++ ){
                    Variants variants = new Variants();
                    variants.setColor(allData.getCategories().get(i).getProducts().get(j).getVariants().get(k).getColor());
                    variants.setId(allData.getCategories().get(i).getProducts().get(j).getVariants().get(k).getId());
                    variants.setPrice(allData.getCategories().get(i).getProducts().get(j).getVariants().get(k).getPrice());
                    variants.setSize(allData.getCategories().get(i).getProducts().get(j).getVariants().get(k).getSize());
                   // System.out.println("Heady Mapper V - "+variantsList.get(k));
                    variantsList.add(variants);
                }
                products.variants.addAll(variantsList);


                productsList.add(products);
            }
            categories.products.addAll(productsList);

            categoriesList.add(categories);
        }
        headyDb.categories.addAll(categoriesList);


        //Rankings
        RealmList<Rankings> rankingsList = new RealmList<Rankings>();
        for(int i=0; i<allData.getRankings().size(); i++){
            Rankings rankings = new Rankings();
            rankings.setRanking(allData.getRankings().get(i).getRanking());

            RealmList<RankingProducts> rankingsProductsList = new RealmList<RankingProducts>();
            for(int j=0; j<allData.getRankings().get(i).getProducts().size(); j++){

                RankingProducts rankingProducts = new RankingProducts();
                rankingProducts.setId(allData.getRankings().get(i).getProducts().get(j).getId());
                rankingProducts.setOrder_count(allData.getRankings().get(i).getProducts().get(j).getDate_added());

                rankingsProductsList.add(rankingProducts);
            }
            rankings.rankingProducts.addAll(rankingsProductsList);
            rankingsList.add(rankings);
        }
        headyDb.rankings.addAll(rankingsList);


        bgRealm.copyToRealmOrUpdate(headyDb);

    }



}
