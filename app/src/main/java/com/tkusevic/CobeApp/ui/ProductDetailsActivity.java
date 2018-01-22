package com.tkusevic.CobeApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;

/**
 * Created by tkusevic on 19.01.2018..
 */

public class ProductDetailsActivity extends AppCompatActivity {


    private final Data data = App.getData();

    private ImageView imageView;
    private EditText nameProduct;
    private EditText priceProduct;

    Product product;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        Intent i = getIntent();
        product = (Product) i.getSerializableExtra("object");
        initUi();
        setExtrasToFields();
    }

    private void setExtrasToFields() {
        imageView.setImageResource(product.getImage());
        nameProduct.setText(product.getName());
        priceProduct.setText(String.valueOf(product.getPrice()));
    }

    private void initUi() {
        imageView = (ImageView) findViewById(R.id.image_view_product);
        nameProduct = (EditText) findViewById(R.id.name_product_view);
        priceProduct = (EditText) findViewById(R.id.price_product_view);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, WorkerActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveProduct(View view) {
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
    }
}
