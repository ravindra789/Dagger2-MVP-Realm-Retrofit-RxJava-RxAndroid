package com.headysample.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.headysample.R;
import com.headysample.model.db.HeadyDb;
import com.headysample.model.response.AllData;
import com.headysample.view.activities.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by nndra on 07-Dec-17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mContext;
    private AllData mImageList = null;
    private LayoutInflater mLayoutInflater;
    private HeadyDb dbData;

    public MainAdapter(Context context, LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
        mContext = context;
        //mImageList = imageList;

    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_adapter, parent, false);
*/
        //return new ViewHolder(v);

        View v = mLayoutInflater.inflate(R.layout.main_adapter,parent,false);;

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

        final int currentPosition =position;

        holder.txtCategorieNames.setText(dbData.getCategories().get(position).getName());


        holder.txtCategorieNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("Id", dbData.getCategories().get(currentPosition).getId());
                mContext.startActivity(intent);
            }
        });

        //holder.txtProductsName.setText(dbData.getCategories().get(position).getName());
        //holder.txtProductDateAdded.setText(dbData.getCategories().get(position).getName());
        //holder.txtProductsName.setText(dbData.getCategories().get(position).getName());
        //holder.txtProductsName.setText(dbData.getCategories().get(position).getName());
        //holder.txtProductsName.setText(dbData.getCategories().get(position).getName());


        //GlideApp.with(this).load(dbData.getCategories().get(position).).into(holder.displayImage);


    }

    @Override
    public int getItemCount() {
        if(dbData != null){
            //return mImageList.getCategories().size();
            return dbData.getCategories().size();
        }else{
          return  0;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //TextView txtProductsName, txtProductDateAdded, txtDisplayProductVariants, txtProductTaxName, txtProductTaxValue;

        TextView txtCategorieNames;

        public ViewHolder(View itemView) {
            super(itemView);

            txtCategorieNames = (TextView) itemView.findViewById(R.id.txt_categorie_names);

           // txtProductsName = (TextView) itemView.findViewById(R.id.txt_products_name);
           // txtProductDateAdded = (TextView) itemView.findViewById(R.id.txt_products_date_added);
            //txtDisplayProductVariants = (TextView) itemView.findViewById(R.id.txt_display_product_variants);
            //txtProductTaxName = (TextView) itemView.findViewById(R.id.txt_product_tax_name);
            //txtProductTaxValue = (TextView) itemView.findViewById(R.id.txt_product_tax_value);

        }
    }

    public void addData(AllData imageList){
        mImageList = imageList;
        notifyDataSetChanged();
    }

    public void clearData() {
        mImageList = null;
        notifyDataSetChanged();
    }

    public void addDbData(HeadyDb dbData){
        this.dbData = dbData;
        notifyDataSetChanged();
    }

}