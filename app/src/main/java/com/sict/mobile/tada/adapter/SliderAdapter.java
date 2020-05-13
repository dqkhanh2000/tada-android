package com.sict.mobile.tada.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sict.mobile.tada.R;
import com.sict.mobile.tada.model.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    private Context context;
    private List<SliderItem> data;

    public SliderAdapter(Context context) {
        this.context = context;
        data = new ArrayList<SliderItem>();
    }

    public SliderAdapter(Context context, List data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_slider_item, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        SliderItem sliderItem = data.get(position);

        if(sliderItem.getDescription() != null)
            viewHolder.txtDescription.setText(sliderItem.getDescription());

        if(sliderItem.getPath() != null)
            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getPath())
                    .centerCrop()
                    .into(viewHolder.imgSlider);
        else
            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getIdImage())
                    .centerCrop()
                    .into(viewHolder.imgSlider);

    }


    @Override
    public int getCount() {
        return data.size();
    }

    class SliderViewHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imgSlider;
        TextView txtDescription;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imgSlider = itemView.findViewById(R.id.image_slider_item);
            txtDescription = itemView.findViewById(R.id.text_slider);
            this.itemView = itemView;
        }
    }

    public void addItem(SliderItem item) {
        this.data.add(item);
        notifyDataSetChanged();
    }

    public void setData(List<SliderItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
