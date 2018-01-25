package com.tkusevic.CobeApp.ui.add_product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.utils.ValidationUtils;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.data.database.Data;


/**
 * Created by tkusevic on 22.01.2018..
 */

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView price;
    private TextView name;
    private Data data;
    private Button goBack;
    private Button addProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initUI();
        initListeners();
        getData();
    }

    private void initUI() {
        price = (TextView) findViewById(R.id.price_product_view_add);
        name = (TextView) findViewById(R.id.name_product_view_add);
        goBack = (Button) findViewById(R.id.goBackProduct);
        addProduct = (Button) findViewById(R.id.addProduct);
    }

    private void initListeners() {
        goBack.setOnClickListener(this);
        addProduct.setOnClickListener(this);
    }

    private void getData() {
        data = App.getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.goBackProduct):
                finish();
                break;
            case (R.id.addProduct):
                if (ValidationUtils.isPriceValid(price.getText().toString())) {
                    Product product = new Product();
                    if (data != null) {
                        product.setId(data.getProducts().size() + 1);
                        product.setName(name.getText().toString().trim());
                        product.setPrice(Integer.valueOf(price.getText().toString()));
                        product.setImage(R.drawable.image_not_found);
                        data.addProduct(product);
                    }
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    price.setError(getString(R.string.krivi_unos_cijene));
                }
                break;
        }

    }
}
