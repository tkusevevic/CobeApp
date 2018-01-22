package com.tkusevic.CobeApp.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;


/**
 * Created by tkusevic on 22.01.2018..
 */

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener{

    private Data data = App.getData();

    private TextView priceView;
    private TextView nameView;

    Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initUI();
    }

    private void initUI() {
        priceView = (TextView) findViewById(R.id.price_product_view_add);
        nameView = (TextView) findViewById(R.id.name_product_view_add);
    }

    public void addProduct(View view) {
        if(isPriceValid()) {
            product.setId(data.getProducts().size() + 1);
            product.setName(nameView.getText().toString().trim());
            product.setPrice(Integer.valueOf(priceView.getText().toString()));
            product.setImage(R.drawable.image_not_found);
            data.addProduct(product);
            Intent returnIntent = new Intent();
            setResult(RESULT_OK,returnIntent);
            finish();
        }

    }

    private boolean isPriceValid() {
        try {
            int tryy = Integer.parseInt(priceView.getText().toString());
            return true;
        }catch(Throwable throwable){
            priceView.setError("Invalid price!");
            return false;
        }
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
