package com.tkusevic.CobeApp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.Recipe;
import com.tkusevic.CobeApp.data.model.User;
import com.tkusevic.CobeApp.ui.OnReceiptClickListener;

import java.util.ArrayList;
import java.util.List;


public class UserActivity extends AppCompatActivity implements OnProductClickListener, OnReceiptClickListener {

    //VIEW-ovi
    private TextView reciepeView;
    private TextView reciepePrice;
    private FrameLayout recipeFrameLayout;
    private Button naplatiBtn;
    private Button clearReciepeView;


    //podaci
    private final Data data = App.getData();

    //global
    Recipe recipe = new Recipe();
    int userId;
    User globalUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        setUser();
        initUi();
        initRecyclerViewMain();
    }

    private void setUser() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt("id");
            for (User user : data.getUsers()) {
                if (user.getId() == userId) {
                    globalUser = user;
                }
            }
        }
    }

    private void initUi() {
        reciepeView = (TextView) findViewById(R.id.reciepeView);
        reciepePrice = (TextView) findViewById(R.id.priceView);
        recipeFrameLayout = (FrameLayout) findViewById(R.id.reciepe_frame_layoutt);
        recipeFrameLayout.setVisibility(View.GONE);
    }

    private void initRecyclerViewMain() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        adapter.setOnProductClickListener(this);
        adapter.setProducts(data.getProducts());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRecyclerViewReceipe() {
        List<Recipe> actualRecipes = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReceipt);
        RecyclerReceiptAdapter adapter = new RecyclerReceiptAdapter();
        adapter.setOnReciepeClickListener(this);
        adapter.setReceipts(actualRecipes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getReciepe(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(reciepeView.getText() + "\nVAŠ RAČUN IZNOSI: " + reciepePrice.getText()
                + " HRK" + "\nŽelite li naplatiti račun?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    recipe.setSumPrice(Integer.parseInt(reciepePrice.getText().toString()));
                    recipe.setUserId(userId);
                    data.addReciepe(recipe);
                    Toast.makeText(getApplicationContext(), data.getRecipes().toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Racun uspješno naplacen!", Toast.LENGTH_SHORT).show();
                    reciepePrice.setText("0");
                    reciepeView.setText("");
                    recipe = new Recipe();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    recipe = new Recipe();
                    Toast.makeText(getApplicationContext(), "Racun nije uspješno naplacen!", Toast.LENGTH_SHORT).show();
                    reciepePrice.setText("0");
                    reciepeView.setText("");
                    break;
            }
        }
    };


    @Override
    public void onProductClick(Product product) {
        recipe.addDrinkToList(product);
        reciepeView.setText(String.format("%s HRK \n", (reciepeView.getText() + product.getName() + " " + product.getPrice())));
        Integer startPrice = Integer.valueOf(reciepePrice.getText().toString());
        Integer finalPrice = startPrice + product.getPrice();
        reciepePrice.setText(finalPrice.toString());

    }


    public void deleteAllFromReciepe(View view) {
        reciepeView.setText("");
        reciepePrice.setText("0");
        recipe = new Recipe();
    }

    public void signOut(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
        finish();
    }

    @Override
    public void onReceiptClick(Recipe recipe) {
        globalUser.addReciepe(recipe);
    }

    public void showRecepts(View view) {
        initRecyclerViewReceipe();
        recipeFrameLayout.setVisibility(View.VISIBLE);
    }

    public void closeRecipesView(View view) {
        recipeFrameLayout.setVisibility(View.GONE);
    }
}
