package com.sict.mobile.tada.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sict.mobile.tada.R;
import com.sict.mobile.tada.model.ProductItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private List<ProductItem> data;
    private LayoutInflater inflater;
    private Context context;

    public interface OnItemClickListener {
        void onItemClicked(View itemView, ProductItem item);
    }

    public ProductRecyclerViewAdapter(Context context, List<ProductItem> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setData(List<ProductItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ProductRecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductItem item = data.get(position);

        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View itemView, ProductItem item) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", (Parcelable) item);
                Navigation.findNavController(itemView).navigate(R.id.product_fragment, bundle);
            }
        });

        holder.txtProductName.setText(item.getProductName());

        NumberFormat format = NumberFormat.getInstance(new Locale("en", "EN"));

        if(item.getUrl() != null)
            Glide.with(holder.itemView)
                    .load(item.getUrl())
                    .centerCrop()
                    .into(holder.imgProduct);
        else
            Glide.with(holder.itemView)
                    .load(item.getId())
                    .centerCrop()
                    .into(holder.imgProduct);
        if(!item.isHot())
            holder.tagHot.setVisibility(View.INVISIBLE);
        if(item.getSaleOff() == 0){
            holder.txtOldPrice.setVisibility(View.INVISIBLE);
            holder.tagSaleOff.setVisibility(View.INVISIBLE);
            holder.txtPrice.setText(format.format(item.getPrice()));
        }
        else{
            holder.txtTagSaleOff.setText("-"+item.getSaleOff()+"%");
            holder.txtPrice.setText(format.format(item.calculateSaleOff()));
            holder.txtOldPrice.setText(format.format(item.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtTagSaleOff, txtProductName, txtPrice, txtOldPrice;
        private ImageView imgProduct;
        private RelativeLayout tagSaleOff, tagHot;
        private OnItemClickListener listener;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            listener = itemClickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgProduct = itemView.findViewById(R.id.img_product_item);
            this.tagHot = itemView.findViewById(R.id.tag_hot);
            this.tagSaleOff = itemView.findViewById(R.id.tag_sale_off);
            this.txtOldPrice = itemView.findViewById(R.id.txt_product_item_old_price);
            this.txtPrice = itemView.findViewById(R.id.txt_product_item_price);
            this.txtProductName = itemView.findViewById(R.id.txt_product_item_name);
            this.txtTagSaleOff = itemView.findViewById(R.id.txt_tag_sale_off);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null)
                listener.onItemClicked(itemView, data.get(getLayoutPosition()));
        }
    }
}
