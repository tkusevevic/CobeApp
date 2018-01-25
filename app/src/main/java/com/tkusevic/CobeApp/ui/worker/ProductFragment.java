package com.tkusevic.CobeApp.ui.worker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.AlertUtils;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.ui.add_product.AddProductActivity;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.ui.listeners.OnDeleteListener;
import com.tkusevic.CobeApp.ui.product_details.ProductDetailsActivity;
import com.tkusevic.CobeApp.ui.worker.list.ProductsAdapter;
import com.tkusevic.CobeApp.ui.listeners.OnProductClickListener;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class ProductFragment extends android.support.v4.app.Fragment implements OnProductClickListener, View.OnClickListener {

    private Data data;

    FloatingActionButton addProductButton;
    RecyclerView recyclerView;
    ProductsAdapter adapter;
    int globalPosition;
    Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
        initData();
        initAdapter();
        initListeners();
    }

    private void initData() {
        data = App.getData();
    }

    private void initUi(View view) {
        addProductButton = view.findViewById(R.id.add_product_btn);
        recyclerView = view.findViewById(R.id.recycler_view_fragments_products);
    }

    private void initListeners() {
        adapter.setOnProductClickListener(this);
        addProductButton.setOnClickListener(this);
    }

    private void initAdapter() {
        adapter = new ProductsAdapter();
        adapter.setProducts(data.getProducts());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            product = adapter.getProduct(position);
            globalPosition = position;
            AlertUtils.getAlertDialogProduct(getActivity(), product, getOnDeleteListener());
        }
    };

    private OnDeleteListener getOnDeleteListener() {
        return new OnDeleteListener() {
            @Override
            public void onOptionYes() {
                adapter.removeProduct(globalPosition);
                data.removeProduct(product);
            }

            @Override
            public void onOptionNo() {
                adapter.notifyDataSetChanged();
            }
        };
    }


    @Override
    public void onProductClick(Product product) {
        startActivity(ProductDetailsActivity.getLaunchIntent(getActivity(), product));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_product_btn) {
            Intent intent = new Intent(getActivity(), AddProductActivity.class);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_ADD_PRODUCTS);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQUEST_CODE_ADD_PRODUCTS && resultCode == Activity.RESULT_OK) {
            adapter.setProducts(this.data.getProducts());
        }
    }
}

