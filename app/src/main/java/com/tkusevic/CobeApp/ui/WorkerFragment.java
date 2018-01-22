package com.tkusevic.CobeApp.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Worker;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class WorkerFragment extends android.support.v4.app.Fragment implements OnWorkerClickListener, View.OnClickListener {

    public static final int REQUEST_CODE_ADD_PRODUCTS = 1;
    public static final int REQUEST_CODE_EDIT_PRODUCTS = 2;

    private final Data data = App.getData();

    private Worker worker;
    RecyclerViewAdapterWorkers adapter;

    private int globalPosition;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_holder_workers, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerViewAdapter(view);
    }

    private void initRecyclerViewAdapter(View view) {
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragments_workers);
        RecyclerViewAdapterWorkers myAdapter = new RecyclerViewAdapterWorkers();
        myAdapter.setOnWorkerClickListener(this);
        myAdapter.setWorkers(data.getWorkers());
        adapter = myAdapter;
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onWorkerClickListener(Worker worker) {
        Intent intent = new Intent(this.getContext(), WorkerDetailsActivity.class);
        intent.putExtra("object",worker);
        startActivityForResult(intent, REQUEST_CODE_EDIT_PRODUCTS);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = (viewHolder.getAdapterPosition());
            worker = adapter.getWorker(position);
            globalPosition = position;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Jeste li sigurni da zelite obrisati radnika:  " + worker.getName() + " " + worker.getLastName() + " ?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    public DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    adapter.removeWorker(globalPosition);
                    data.removeWorker(worker);
                    adapter.notifyItemRemoved(globalPosition);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(this.getContext(), AddWorkerActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCTS);


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if((requestCode == 1 || requestCode == 2) && resultCode == Activity.RESULT_OK) {
            adapter.setWorkers(this.data.getWorkers());
        }
    }
}
