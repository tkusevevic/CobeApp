package com.tkusevic.CobeApp.ui.worker;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Bill;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.ui.worker.list.BillsAdapter;
import com.tkusevic.CobeApp.ui.listeners.OnBillClickListener;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class BillsFragment extends android.support.v4.app.Fragment implements OnBillClickListener {

    private Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bills, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initAdapter(view);
    }

    private void initData() {
        data = App.getData();
    }

    private void initAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragments_recipes);
        BillsAdapter myAdapter = new BillsAdapter();
        myAdapter.setBill(data.getBills());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myAdapter.setOnBillClickListener(this);
    }

    @Override
    public void onRepeatClick(Bill bill) {
    }
}
