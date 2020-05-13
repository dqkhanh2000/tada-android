package com.sict.mobile.tada.ui.category;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.sict.mobile.tada.R;
import com.sict.mobile.tada.adapter.CategoryAdapter;
import com.sict.mobile.tada.connection.RetrofitClient;
import com.sict.mobile.tada.model.Category;
import com.sict.mobile.tada.ui.colection.ColectionFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mViewModel;
    private CategoryAdapter categoryAdapter;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.category_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        categoryAdapter = new CategoryAdapter(container.getContext());
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View itemView, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("collection_name", categoryAdapter.getListData().get(position).getName());
                bundle.putString("collection_code", categoryAdapter.getListData().get(position).getCode());
                Navigation.findNavController(view).navigate(R.id.colectionFragment, bundle);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        ArrayList<Category> listCategory = new ArrayList<>();
        RetrofitClient.getAPI().getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    for (Category item : response.body()){
                        item.setImageURL(RetrofitClient.BASE_URL+item.getImageURL());
                        listCategory.add(item);
                    }
                    categoryAdapter.setListData(listCategory);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

}
