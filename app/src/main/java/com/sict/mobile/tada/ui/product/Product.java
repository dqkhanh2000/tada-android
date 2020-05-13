package com.sict.mobile.tada.ui.product;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sict.mobile.tada.R;
import com.sict.mobile.tada.adapter.SliderAdapter;
import com.sict.mobile.tada.connection.API;
import com.sict.mobile.tada.connection.RetrofitClient;
import com.sict.mobile.tada.model.ProductColor;
import com.sict.mobile.tada.model.ProductItem;
import com.sict.mobile.tada.model.SliderItem;
import com.smarteist.autoimageslider.SliderView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Product extends Fragment {

    private ProductViewModel mViewModel;
    private TextView txtProductName, txtProductPrice, txtProductOldPrice,txtProductSaleOff, txtProductDescription;

    public static Product newInstance() {
        return new Product();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        NumberFormat format = NumberFormat.getInstance(new Locale("en", "EN"));
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        Bundle bundle = getArguments();
        ProductItem item = bundle.getParcelable("item");
        txtProductName = view.findViewById(R.id.txt_product_name);
        txtProductPrice = view.findViewById(R.id.txt_product_price);
        txtProductOldPrice = view.findViewById(R.id.txt_product_old_price);
        txtProductSaleOff = view.findViewById(R.id.txt_product_sale_off);
        txtProductDescription = view.findViewById(R.id.txt_product_description);

        if(item.getSaleOff() == 0){
            txtProductOldPrice.setVisibility(View.INVISIBLE);
            txtProductSaleOff.setVisibility(View.INVISIBLE);
            txtProductPrice.setText(format.format(item.getPrice()));
        }
        else{
            txtProductSaleOff.setText("-"+item.getSaleOff()+"%");
            txtProductPrice.setText(format.format(item.calculateSaleOff()));
            txtProductOldPrice.setText(format.format(item.getPrice()));
        }

        txtProductName.setText(item.getProductName());
        if(item.getDescription() != null)
        txtProductDescription.setText(Html.fromHtml(item.getDescription(), Html.FROM_HTML_MODE_COMPACT));

        ArrayList<SliderItem> dataSlider = new ArrayList<>();
        ArrayList<CharSequence> colors = new ArrayList<>();
        for(ProductColor productColor : item.getProductColors()){
            colors.add(productColor.getColor());
            for(String pathImage : productColor.getImages())
                dataSlider.add(new SliderItem(RetrofitClient.BASE_URL+"image/"+pathImage));
        }

        SliderAdapter sliderAdapter = new SliderAdapter(getContext(), dataSlider);
        SliderView sliderView = view.findViewById(R.id.product_slider_view);
        sliderView.setSliderAdapter(sliderAdapter);

        Spinner spinnerColor = view.findViewById(R.id.spinner_product_color);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this.getContext(),
                android.R.layout.simple_spinner_dropdown_item, colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        // TODO: Use the ViewModel
    }

}
