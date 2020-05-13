package com.sict.mobile.tada.ui.colection;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sict.mobile.tada.R;
import com.sict.mobile.tada.adapter.ProductRecyclerViewAdapter;
import com.sict.mobile.tada.connection.API;
import com.sict.mobile.tada.connection.RetrofitClient;
import com.sict.mobile.tada.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColectionFragment extends Fragment {

    private ColectionViewModel mViewModel;
    private Bundle bundle;
    private RecyclerView listItem;
    private ProductRecyclerViewAdapter adapter;
    private TextView txtNotFound;

    public static ColectionFragment newInstance() {
        return new ColectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.colection_fragment, container, false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        listItem = view.findViewById(R.id.collection_list_product);
        listItem.setLayoutManager(layoutManager);
        adapter = new ProductRecyclerViewAdapter(this.getContext());
        listItem.setAdapter(adapter);
        bundle = getArguments();
        TextView txtCollectionName = view.findViewById(R.id.txt_collection_name);
        txtNotFound = view.findViewById(R.id.txt_collection_not_found);
        txtCollectionName.setText(bundle.getString("collection_name"));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ColectionViewModel.class);
        RetrofitClient.getAPI().collection(bundle.getString("collection_code")).enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() == 1 && response.body().get(0).getProductName() == null)
                        txtNotFound.setVisibility(View.VISIBLE);
                    else {
                        List<ProductItem> list = new ArrayList<>();
                        for (ProductItem item : response.body()) {
                            item.setUrl(RetrofitClient.BASE_URL + "image/" + item.getUrl());
                            list.add(item);
                        }
                        adapter.setData(list);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
            }
        });
        // TODO: Use the ViewModel
    }

}
