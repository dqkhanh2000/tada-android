package com.sict.mobile.tada.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sict.mobile.tada.R;
import com.sict.mobile.tada.model.ProductItem;
import com.sict.mobile.tada.adapter.ProductRecyclerViewAdapter;
import com.sict.mobile.tada.adapter.SliderAdapter;
import com.sict.mobile.tada.model.SliderItem;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private View view;
    private RecyclerView mRecyclerViewTopWomen, mRecyclerViewTopMen;
    private SliderView mSliderView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.home_fragment, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);

        mRecyclerViewTopWomen = view.findViewById(R.id.home_list_product_women);
        mRecyclerViewTopWomen.setLayoutManager(layoutManager);
        mRecyclerViewTopWomen.setNestedScrollingEnabled(false);

        mRecyclerViewTopMen = view.findViewById(R.id.home_list_product_men);
        mRecyclerViewTopMen.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        mRecyclerViewTopMen.setNestedScrollingEnabled(false);

        mSliderView = view.findViewById(R.id.image_slider);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.setDataTopForWomen();
        mViewModel.setDataTopForMen();
        ProductRecyclerViewAdapter topWomenAdapter = new ProductRecyclerViewAdapter(this.getContext());
        mRecyclerViewTopWomen.setAdapter(topWomenAdapter);
        mViewModel.dataTopForWomen.observe(this.getViewLifecycleOwner(), new Observer<List<ProductItem>>() {
            @Override
            public void onChanged(List<ProductItem> productItems) {
                topWomenAdapter.setData(productItems);
            }
        });


        ProductRecyclerViewAdapter topMenAdapter = new ProductRecyclerViewAdapter(this.getContext());
        mRecyclerViewTopMen.setAdapter(topMenAdapter);
        mViewModel.dataTopForMen.observe(this.getViewLifecycleOwner(), new Observer<List<ProductItem>>() {
            @Override
            public void onChanged(List<ProductItem> productItems) {

                topMenAdapter.setData(productItems);
            }
        });

        mViewModel.getDataSlider();
        SliderAdapter sliderAdapter = new SliderAdapter(HomeFragment.this.getContext());
        mSliderView.setSliderAdapter(sliderAdapter);

        mViewModel.dataSlider.observe(this.getViewLifecycleOwner(), new Observer<List<SliderItem>>() {
            @Override
            public void onChanged(List<SliderItem> data) {
                sliderAdapter.setData(data);
            }
        });


    }



}
