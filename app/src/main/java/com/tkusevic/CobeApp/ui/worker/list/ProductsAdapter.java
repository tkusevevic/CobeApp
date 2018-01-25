package com.tkusevic.CobeApp.ui.worker.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.ui.listeners.OnProductClickListener;
import com.tkusevic.CobeApp.ui.worker.holder.ProductHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 16.01.2018..
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductHolder> {

    private List<Product> mProducts = new ArrayList<>();
    private OnProductClickListener listener;

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.listener = listener;
    }

    public void setProducts(List<Product> products) {
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    public void removeProduct(int position) {
        mProducts.remove(position);
        notifyItemRemoved(position);
    }

    public Product getProduct(int position) {
        return mProducts.get(position);
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, final int position) {
        if (mProducts != null && mProducts.size() > 0) {
            final Product product = mProducts.get(position);
            holder.setProduct(product);
            holder.setListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
