package com.sict.mobile.tada.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sict.mobile.tada.R;
import com.sict.mobile.tada.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Category> listData;
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClicked(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CategoryAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.listData = new ArrayList<>();
    }

    public CategoryAdapter(LayoutInflater inflater, List<Category> listData) {
        this.inflater = inflater;
        this.listData = listData;
    }

    public List<Category> getListData() {
        return listData;
    }

    public void setListData(List<Category> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category item = listData.get(position);
        holder.txtCategoryItem.setText(item.getName());
        Glide.with(holder.itemView)
                .load(item.getImageURL())
                .into(holder.imgCategoryItem);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtCategoryItem;
        ImageView imgCategoryItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryItem = itemView.findViewById(R.id.txt_category);
            imgCategoryItem = itemView.findViewById(R.id.img_category_item);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onItemClicked(itemView, getLayoutPosition());
        }
    }
}
