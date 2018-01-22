package com.tkusevic.CobeApp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Recipe;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class RecipiesFragment extends android.support.v4.app.Fragment implements OnReceiptClickListener {


    private final Data data = App.getData();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_holder_recipes,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerViewAdapter(view);
    }

    private void initRecyclerViewAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragments_recipes);
        RecyclerViewAdapterReceipt myAdapter = new RecyclerViewAdapterReceipt();
        myAdapter.setOnReciepeClickListener(this);
        myAdapter.setReceipts(data.getRecipes());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onRepeatClick(Recipe recipe) {
        //NULL
    }
}
