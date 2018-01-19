package com.tkusevic.CobeApp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class ProductFragment extends android.support.v4.app.Fragment implements OnProductClickListener{

    private final Data data = App.getData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_holder_product, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
        initRecyclerViewAdapter(view);
    }

    private void initRecyclerViewAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragments_products);
        RecyclerViewAdapterProducts adapter = new RecyclerViewAdapterProducts();
        adapter.setOnProductClickListener(this);
        adapter.setProducts(data.getProducts());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initUi(View view) {
    }

    @Override
    public void onProductClick(Product product) {
        //TO DO SOMETHING WITH SELECTED PRODUCT

    }
}
