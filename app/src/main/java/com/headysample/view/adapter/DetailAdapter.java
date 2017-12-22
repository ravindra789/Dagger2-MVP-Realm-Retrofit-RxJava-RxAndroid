package com.headysample.view.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import com.headysample.R;
import com.headysample.model.db.HeadyDb;
import com.headysample.model.response.AllData;
import com.headysample.view.activities.DetailsActivity;

/**
 * Created by nndra on 22-Dec-17.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context mContext;
    private AllData mImageList = null;
    private LayoutInflater mLayoutInflater;
    private HeadyDb dbData;

    public DetailAdapter(Context context, LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
        mContext = context;
        //mImageList = imageList;

    }

    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_adapter, parent, false);
*/
        //return new ViewHolder(v);

        View v = mLayoutInflater.inflate(R.layout.product_details,parent,false);;

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder, int position) {

        final int currentPosition =position;

        holder.txtProductsName.setText(dbData.getCategories().get(position).getName());
        holder.txtProductDateAdded.setText(dbData.getCategories().get(position).getName());
        holder.txtProductsName.setText(dbData.getCategories().get(position).getName());
        holder.txtProductsName.setText(dbData.getCategories().get(position).getName());
        holder.txtProductsName.setText(dbData.getCategories().get(position).getName());


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

        TextView txtProductsName, txtProductDateAdded, txtDisplayProductVariants, txtProductTaxName, txtProductTaxValue;

        public ViewHolder(View itemView) {
            super(itemView);

             txtProductsName = (TextView) itemView.findViewById(R.id.txt_products_name);
             txtProductDateAdded = (TextView) itemView.findViewById(R.id.txt_products_date_added);
            txtDisplayProductVariants = (TextView) itemView.findViewById(R.id.txt_display_product_variants);
            txtProductTaxName = (TextView) itemView.findViewById(R.id.txt_product_tax_name);
            txtProductTaxValue = (TextView) itemView.findViewById(R.id.txt_product_tax_value);

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