package com.tkusevic.CobeApp.ui.worker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.AlertUtils;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.add_worker.AddWorkerActivity;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.ui.listeners.OnDeleteListener;
import com.tkusevic.CobeApp.ui.worker.list.WorkersAdapter;
import com.tkusevic.CobeApp.ui.worker_details.WorkerDetailsActivity;
import com.tkusevic.CobeApp.ui.listeners.OnWorkerClickListener;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class WorkerFragment extends android.support.v4.app.Fragment implements OnWorkerClickListener, View.OnClickListener {

    private Data data;

    private Worker worker;
    private WorkersAdapter adapter;
    private int globalPosition;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workers, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab);
        initData();
        initAdapter(view);
        initListeners();
    }

    private void initData() {
        data = App.getData();
    }

    private void initListeners() {
        fab.setOnClickListener(this);
        adapter.setOnWorkerClickListener(this);
    }

    private void initAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragments_workers);
        adapter = new WorkersAdapter();
        adapter.setWorkers(data.getWorkers());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
            AlertUtils.getAlertDialogWorker(getActivity(), worker, getOnDeleteListener());
        }
    };

    @Override
    public void onWorkerClick(Worker worker) {
        Intent intent = new Intent(this.getContext(), WorkerDetailsActivity.class);
        intent.putExtra(AppConstants.WORKER, worker);
        startActivityForResult(intent, AppConstants.REQUEST_CODE_EDIT_PRODUCTS);
    }

    private OnDeleteListener getOnDeleteListener() {
        return new OnDeleteListener() {
            @Override
            public void onOptionYes() {
                adapter.removeWorker(globalPosition);
                data.removeWorker(worker);
                adapter.notifyItemRemoved(globalPosition);
            }

            @Override
            public void onOptionNo() {
                adapter.notifyDataSetChanged();
            }
        };
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(this.getContext(), AddWorkerActivity.class);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_ADD_PRODUCTS);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == AppConstants.REQUEST_CODE_ADD_PRODUCTS || requestCode == AppConstants.REQUEST_CODE_EDIT_PRODUCTS) && resultCode == Activity.RESULT_OK) {
            adapter.setWorkers(this.data.getWorkers());
        }
    }
}
