package com.tkusevic.CobeApp.ui;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.Recipe;
import com.tkusevic.CobeApp.data.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserActivity extends AppCompatActivity implements OnProductClickListener, OnReceiptClickListener {

    private EditText reciepeView;
    private TextView reciepePrice;
    private FrameLayout recipeFrameLayout;

    private final Data data = App.getData();

    Recipe globalRecipe = new Recipe();
    int userId;
    User globalUser;

    private final RecyclerViewAdapterReceipt receiptAdapter = new RecyclerViewAdapterReceipt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setUser();
        initUi();
        initRecyclerViewMain();
        initRecyclerViewReceipe();
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
        reciepeView = (EditText) findViewById(R.id.reciepeView);
        reciepePrice = (TextView) findViewById(R.id.priceView);
        recipeFrameLayout = (FrameLayout) findViewById(R.id.reciepe_frame_layoutt);
        registerForContextMenu(recipeFrameLayout);
        recipeFrameLayout.setVisibility(View.GONE);
    }

    private void initRecyclerViewMain() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        RecyclerViewAdapterProducts adapter = new RecyclerViewAdapterProducts();
        adapter.setOnProductClickListener(this);
        adapter.setProducts(data.getProducts());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRecyclerViewReceipe() {
        List<Recipe> actualRecipes = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReceipt);
        receiptAdapter.setOnReciepeClickListener(this);
        receiptAdapter.setReceipts(actualRecipes);
        recyclerView.setAdapter(receiptAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onProductClick(Product product) {
        globalRecipe.addDrinkToList(product);
        reciepeView.setText(String.format("%s HRK \n", (reciepeView.getText() + product.getName() + " " + product.getPrice())));
        Integer startPrice = Integer.valueOf(reciepePrice.getText().toString());
        Integer finalPrice = startPrice + product.getPrice();
        reciepePrice.setText(finalPrice.toString());
    }

    @Override
    public void onRepeatClick(Recipe recipe) {
        data.addReciepe(recipe);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReceipt);
        receiptAdapter.addReceipt(recipe);
        recyclerView.setAdapter(receiptAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fixSequence();
    }

    public void getReciepe(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(reciepeView.getText() + "\nVAŠ RAČUN IZNOSI: " + reciepePrice.getText()
                + " HRK" + "\nŽelite li naplatiti račun?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    addReciepeToData();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    quitPaying();
                    break;
            }
        }
    };


    private void quitPaying() {
        globalRecipe = new Recipe();
        Toast.makeText(getApplicationContext(), "Racun nije uspješno naplacen!", Toast.LENGTH_SHORT).show();
        reciepePrice.setText("0");
        reciepeView.setText("");
    }

    private void addReciepeToData() {
        globalRecipe.setSumPrice(Integer.parseInt(reciepePrice.getText().toString()));
        globalRecipe.setUserId(userId);
        data.addReciepe(globalRecipe);
        Toast.makeText(getApplicationContext(), "Racun uspješno naplacen!", Toast.LENGTH_SHORT).show();
        reciepePrice.setText("0");
        reciepeView.setText("");
        globalRecipe = new Recipe();
    }

    public void deleteAllFromReciepe(View view) {
        reciepeView.setText("");
        reciepePrice.setText("0");
        globalRecipe = new Recipe();
    }

    public void signOut(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
        finish();
    }

    public void showRecepts(View view) {
        fixSequence();
        recipeFrameLayout.setVisibility(View.VISIBLE);
    }

    private void fixSequence() {
        List<Recipe> items = new ArrayList<>();
        for (Recipe recipe : data.getRecipes()) {
            if (recipe != null && recipe.getUserId() == userId) {
                items.add(recipe);
            }
        }
        Collections.reverse(items);
        receiptAdapter.setReceipts(items);
    }

    public void closeRecipesView(View view) {
        recipeFrameLayout.setVisibility(View.GONE);
    }

}
