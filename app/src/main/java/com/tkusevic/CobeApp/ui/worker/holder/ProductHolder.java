package com.tkusevic.CobeApp.ui.worker.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.ui.listeners.OnProductClickListener;

/**
 * Created by tkusevic on 23.01.2018..
 */

public class ProductHolder extends RecyclerView.ViewHolder {
    private ImageView image;
    private TextView name;
    private LinearLayout parentLayout;
    private OnProductClickListener listener;

    public ProductHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        parentLayout = itemView.findViewById(R.id.parent_layout_products);
    }

    public void setProduct(final Product product) {
        name.setText(product.toString());
        image.setImageResource(product.getImage());
        parentLayout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(product);
            }
        });
    }

    public void setListener(OnProductClickListener listener) {
        this.listener = listener;
    }
}
