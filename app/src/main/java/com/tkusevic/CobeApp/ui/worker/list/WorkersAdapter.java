package com.tkusevic.CobeApp.ui.worker.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.listeners.OnWorkerClickListener;
import com.tkusevic.CobeApp.ui.worker.holder.WorkerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class WorkersAdapter extends RecyclerView.Adapter<WorkerHolder> {

    private List<Worker> mWorkers = new ArrayList<>();
    private OnWorkerClickListener listener;

    public void setOnWorkerClickListener(OnWorkerClickListener listener) {
        this.listener = listener;
    }

    public Worker getWorker(int position) {
        return mWorkers.get(position);
    }

    public void removeWorker(int position) {
        mWorkers.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public WorkerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder_worker, parent, false);
        return new WorkerHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkerHolder holder, int position) {
        if (mWorkers != null) {
            final Worker worker = mWorkers.get(position);
            holder.setWorker(worker);
            holder.setListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return mWorkers.size();
    }

    public void setWorkers(List<Worker> workers) {
        mWorkers.clear();
        mWorkers.addAll(workers);
        notifyDataSetChanged();
    }
}
