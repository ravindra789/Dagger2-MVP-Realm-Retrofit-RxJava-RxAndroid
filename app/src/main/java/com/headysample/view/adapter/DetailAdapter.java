package com.headysample.view.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.content.Context;

import com.headysample.R;
import com.headysample.model.db.Categories;
import com.headysample.model.db.HeadyDb;
import com.headysample.model.response.AllData;
import com.headysample.view.activities.DetailsActivity;

import java.util.ArrayList;

/**
 * Created by nndra on 22-Dec-17.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context mContext;
    private AllData mImageList = null;
    private LayoutInflater mLayoutInflater;
    private Categories categories;
    private ArrayList<String> variantsList ;

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

        final int currentPosition = position;

        holder.txtProductsName.setText(categories.getProducts().get(position).getName());
        holder.txtProductDateAdded.setText(categories.getProducts().get(position).getDate_added());
        holder.txtProductTaxName.setText(categories.getProducts().get(position).getTax().getName());
        holder.txtProductTaxValue.setText(categories.getProducts().get(position).getTax().getValue());

        categories.getProducts().get(position).getVariants();


        variantsList = new ArrayList<String>();
        for (int i=0; i<categories.getProducts().get(position).getVariants().size();i++) {
            variantsList.add(" "+categories.getProducts().get(position).getVariants().get(i).getColor());
            //holder.txtDisplayProductVariants.setText(" "+categories.getProducts().get(position).getVariants().get(i).getColor()+" ,");
        }

        holder.txtDisplayProductVariants.setText(""+variantsList);

       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, variantsList);

        adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.horizontalScrollView.setAdapter(adapter);
*/

        //GlideApp.with(this).load(dbData.getCategories().get(position).).into(holder.displayImage);


    }

    @Override
    public int getItemCount() {
        if(categories != null){
            //return mImageList.getCategories().size();
            return categories.products.size();
        }else{
            return  0;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductsName, txtProductDateAdded, txtDisplayProductVariants, txtProductTaxName, txtProductTaxValue;
        HorizontalScrollView horizontalScrollView;

        public ViewHolder(View itemView) {
            super(itemView);

             txtProductsName = (TextView) itemView.findViewById(R.id.txt_products_name);
             txtProductDateAdded = (TextView) itemView.findViewById(R.id.txt_products_date_added);
            txtDisplayProductVariants = (TextView) itemView.findViewById(R.id.txt_display_product_variants);
            txtProductTaxName = (TextView) itemView.findViewById(R.id.txt_product_tax_name);
            txtProductTaxValue = (TextView) itemView.findViewById(R.id.txt_product_tax_value);
            horizontalScrollView = (HorizontalScrollView) itemView.findViewById(R.id.hor_display_scroll);



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

    public void addDbData(Categories categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

}