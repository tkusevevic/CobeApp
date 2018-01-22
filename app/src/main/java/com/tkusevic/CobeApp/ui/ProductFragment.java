package com.tkusevic.CobeApp.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class ProductFragment extends android.support.v4.app.Fragment implements OnProductClickListener, FragmentClickable, View.OnClickListener{

    private final Data data = App.getData();

    public static final int REQUEST_CODE = 1;

    FloatingActionButton addProductButton ;
    RecyclerViewAdapterProducts adapter;
    int globalPosition;
    Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_holder_product, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerViewAdapter(view);
    }

    private void initRecyclerViewAdapter(View view) {
        addProductButton = view.findViewById(R.id.add_product_btn);
        addProductButton.setOnClickListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragments_products);
        adapter = new RecyclerViewAdapterProducts();
        adapter.setOnProductClickListener(this);
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
            int position = (viewHolder.getAdapterPosition());
            product = adapter.getProduct(position);
            globalPosition = position;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Jeste li sigurni da zelite obrisati proizvod " + product.getName() + " ?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    };

    public DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    adapter.removeProduct(globalPosition);
                    data.removeProduct(product);
                    adapter.notifyItemRemoved(globalPosition);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onProductClick(Product product) {
        Intent intent = new Intent(this.getContext(), ProductDetailsActivity.class);
        intent.putExtra("object", product);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_product_btn) {
            Intent intent = new Intent(this.getContext(), AddProductActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            adapter.setProducts(this.data.getProducts());
        }
    }
}

