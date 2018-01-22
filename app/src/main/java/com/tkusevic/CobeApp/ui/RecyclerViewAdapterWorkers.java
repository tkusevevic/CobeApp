package com.tkusevic.CobeApp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 18.01.2018..
 */

public class RecyclerViewAdapterWorkers extends RecyclerView.Adapter<RecyclerViewAdapterWorkers.ViewHolder> {

    private Data data = App.getData();
    private List<Worker> mWorkers = new ArrayList<>();
    OnWorkerClickListener listener;

    public void setOnWorkerClickListener(OnWorkerClickListener listener){
        this.listener=listener;
    }

    public void addWorker(Worker worker){

            mWorkers.add(worker);
            notifyDataSetChanged();
    }

    public Worker getWorker(int position){
       return mWorkers.get(position);
    }

    public void removeWorker(int position){
        mWorkers.remove(position);
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
            holder.workersName.setText(worker.getName());
            holder.workerLastname.setText(worker.getLastName());
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
        TextView workersName;
        TextView workerLastname;
        TextView salary;
        TextView type;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            workersName = itemView.findViewById(R.id.workers_name);
            workerLastname = itemView.findViewById(R.id.workers_lastname);
            salary = itemView.findViewById(R.id.workers_salary);
            type = itemView.findViewById(R.id.workers_type);
            parentLayout = itemView.findViewById(R.id.parent_layout_worker);

        }
    }
}
