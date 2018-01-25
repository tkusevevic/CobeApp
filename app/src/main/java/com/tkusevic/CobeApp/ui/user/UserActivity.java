package com.tkusevic.CobeApp.ui.user;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.AlertUtils;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.Bill;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.ui.listeners.OnBillClickListener;
import com.tkusevic.CobeApp.ui.listeners.OnProductClickListener;
import com.tkusevic.CobeApp.ui.listeners.OnRepeatClickListener;
import com.tkusevic.CobeApp.ui.worker.list.BillsAdapter;
import com.tkusevic.CobeApp.ui.worker.list.ProductsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UserActivity extends AppCompatActivity implements OnProductClickListener, OnBillClickListener, View.OnClickListener {

    private EditText billView;
    private TextView billPrice;
    private FrameLayout billFrameLayout;
    private Button getBill;
    private Button deleteAllFromBill;
    private Button closeBillsView;
    private Button showBills;
    private Button signOutUser;

    private ProductsAdapter productAdapter;
    private BillsAdapter billAdapter;
    private Data data;
    private Bill globalBill = new Bill();
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initData();
        setUser();
        initUi();
        initProductAdapter();
        initBillsAdapter();
        iniListeners();
    }

    private void initData() {
        data = App.getData();
    }

    private void iniListeners() {
        getBill.setOnClickListener(this);
        deleteAllFromBill.setOnClickListener(this);
        signOutUser.setOnClickListener(this);
        showBills.setOnClickListener(this);
        closeBillsView.setOnClickListener(this);
        productAdapter.setOnProductClickListener(this);
        billAdapter.setOnBillClickListener(this);
    }

    private void setUser() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getInt(AppConstants.ID);
        }
    }

    private void initUi() {
        billView = (EditText) findViewById(R.id.reciepeView);
        billPrice = (TextView) findViewById(R.id.priceView);
        billFrameLayout = (FrameLayout) findViewById(R.id.reciepe_frame_layoutt);
        registerForContextMenu(billFrameLayout);
        billFrameLayout.setVisibility(View.GONE);

        signOutUser = (Button) findViewById(R.id.signOutUser);
        showBills = (Button) findViewById(R.id.showBills);
        closeBillsView = (Button) findViewById(R.id.closeBillsView);
        deleteAllFromBill = (Button) findViewById(R.id.deleteAllFromBill);
        getBill = (Button) findViewById(R.id.getBill);
    }

    private void initProductAdapter() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUser);
        productAdapter = new ProductsAdapter();
        productAdapter.setProducts(data.getProducts());
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initBillsAdapter() {
        List<Bill> actualBills = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReceipt);
        billAdapter = new BillsAdapter();
        billAdapter.setBill(actualBills);
        recyclerView.setAdapter(billAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onProductClick(Product product) {
        if (product != null) {
            globalBill.addDrinkToList(product);
            billView.setText(String.format("%s %s \n", (billView.getText() + product.getName() + " " + product.getPrice()),getString(R.string.valuta)));
            Integer startPrice = Integer.valueOf(billPrice.getText().toString());
            Integer finalPrice = startPrice + product.getPrice();
            billPrice.setText(finalPrice.toString());
        }
    }

    @Override
    public void onRepeatClick(Bill bill) {
        if (bill != null) {
            globalBill = bill;
            AlertUtils.getAlertDialogBill(this, bill, getOnRepeatClickListener());
        }
    }

    private OnRepeatClickListener getOnRepeatClickListener() {
        return new OnRepeatClickListener() {
            @Override
            public void onOptionYes() {
                if (globalBill != null) {
                    data.addBill(globalBill);
                    billAdapter.addBill(globalBill);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReceipt);
                    billAdapter.addBill(globalBill);
                    recyclerView.setAdapter(billAdapter);
                    fixSequence();
                }
            }
            @Override
            public void onOptionNo() {
                globalBill = new Bill();
                billPrice.setText(R.string.broj_nula_string);
                billView.setText("");
                billAdapter.notifyDataSetChanged();
            }
        };
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
        globalBill = new Bill();
        Toast.makeText(App.getInstance(), R.string.racun_nije_naplacen, Toast.LENGTH_SHORT).show();
        billPrice.setText(R.string.broj_nula_string);
        billView.setText("");
    }

    private void addReciepeToData() {
        globalBill.setSumPrice(Integer.parseInt(billPrice.getText().toString()));
        globalBill.setUserId(userId);
        data.addBill(globalBill);
        Toast.makeText(getApplicationContext(), R.string.racun_naplacen, Toast.LENGTH_SHORT).show();
        billPrice.setText(R.string.broj_nula_string);
        billView.setText("");
        globalBill = new Bill();
    }

    private void fixSequence() {
        List<Bill> items = new ArrayList<>();
        for (Bill bill : data.getBills()) {
            if (bill != null && bill.getUserId() == userId) {
                items.add(bill);
            }
        }
        Collections.reverse(items);
        billAdapter.setBill(items);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.showBills):
                fixSequence();
                billFrameLayout.setVisibility(View.VISIBLE);
                break;
            case (R.id.signOutUser):
                finish();
                break;
            case (R.id.deleteAllFromBill):
                billView.setText("");
                billPrice.setText(R.string.broj_nula_string);
                globalBill = new Bill();
                break;
            case (R.id.closeBillsView):
                billFrameLayout.setVisibility(View.GONE);
                break;
            case (R.id.getBill):
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(String.format(Locale.getDefault(),getString(R.string.vas_racun_izgleda_format), globalBill.getProducts(),globalBill.getSumPrice()))
                        .setPositiveButton(R.string.da, dialogClickListener)
                        .setNegativeButton(R.string.ne, dialogClickListener)
                        .show();
        }
    }
}
