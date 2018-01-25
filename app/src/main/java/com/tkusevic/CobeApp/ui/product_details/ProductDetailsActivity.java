package com.tkusevic.CobeApp.ui.product_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.ValidationUtils;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.ui.worker.WorkerActivity;

/**
 * Created by tkusevic on 19.01.2018..
 */

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Data data;
    private ImageView imageView;
    private EditText nameProduct;
    private EditText priceProduct;
    private Product product;
    private Button editProduct;
    private Button goBack;

    public static Intent getLaunchIntent(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(AppConstants.PRODUCT, product);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        getExtrasFromActivity();
        initUi();
        setExtras();
        getData();
        setListeners();
    }

    private void setListeners() {
        goBack.setOnClickListener(this);
        editProduct.setOnClickListener(this);
    }

    private void getData() {
        data = App.getData();
    }

    private void getExtrasFromActivity() {
        Intent i = getIntent();
        product = (Product) i.getSerializableExtra(AppConstants.PRODUCT);
    }

    private void setExtras() {
        if (product != null) {
            imageView.setImageResource(product.getImage());
            nameProduct.setText(product.getName());
            priceProduct.setText(String.valueOf(product.getPrice()));
        }
    }

    private void initUi() {
        imageView = (ImageView) findViewById(R.id.image_view_product);
        nameProduct = (EditText) findViewById(R.id.name_product_view);
        priceProduct = (EditText) findViewById(R.id.price_product_view);
        goBack = (Button) findViewById(R.id.goBackProductDetails);
        editProduct = (Button) findViewById(R.id.editProduct);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.editProduct):
                if (ValidationUtils.isPriceValid(priceProduct.getText().toString().trim())) {
                    product.setName(nameProduct.getText().toString().trim());
                    product.setPrice(Integer.parseInt(priceProduct.getText().toString().trim()));
                    for (Product currentProduct : data.getProducts()) {
                        if (currentProduct.getId() == product.getId()) {
                            currentProduct.setName(product.getName());
                            currentProduct.setPrice(product.getPrice());
                            Intent intent = new Intent(this, WorkerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                } else {
                    priceProduct.setError(getString(R.string.krivi_unos_cijene));
                }
                break;
            case (R.id.goBackProductDetails):
                finish();
                break;
        }
    }
}

