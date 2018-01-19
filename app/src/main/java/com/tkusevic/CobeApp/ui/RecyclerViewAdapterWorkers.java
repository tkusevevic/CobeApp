package com.tkusevic.CobeApp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Recipe;
import com.tkusevic.CobeApp.data.model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class RecyclerViewAdapterWorkers extends RecyclerView.Adapter<RecyclerViewAdapterWorkers.ViewHolder> {

    private List<Worker> mWorkers = new ArrayList<>();
    onWorkerClickListener listener;

    public void setOnWorkerClickListener(onWorkerClickListener listener){
        this.listener=listener;
    }

    public void addWorker(Worker worker){
            mWorkers.add(worker);
            notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_holder_worker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mWorkers != null) {
            final Worker worker = mWorkers.get(position);
            holder.workersData.setText(worker.getName() +" " + worker.getLastName());
            holder.type.setText(String.valueOf(worker.getType()));
            holder.salary.setText(String.valueOf(worker.getSalary()));
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onWorkerClickListener(worker);
                }
            });
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText workersData;
        EditText salary;
        EditText type;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            workersData = itemView.findViewById(R.id.workers_data);
            salary = itemView.findViewById(R.id.workers_salary);
            type = itemView.findViewById(R.id.workers_type);
            parentLayout = itemView.findViewById(R.id.parent_layout_worker);

        }
    }
}
