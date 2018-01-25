package com.tkusevic.CobeApp.ui.worker.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.listeners.OnProductClickListener;
import com.tkusevic.CobeApp.ui.listeners.OnWorkerClickListener;

/**
 * Created by tkusevic on 23.01.2018..
 */

public class WorkerHolder extends RecyclerView.ViewHolder {

    private OnWorkerClickListener listener;
    private TextView workersName;
    private TextView workerLastName;
    private TextView salary;
    private TextView type;
    private LinearLayout parentLayout;

    public WorkerHolder(View itemView) {
        super(itemView);
        workersName = itemView.findViewById(R.id.workers_name);
        workerLastName = itemView.findViewById(R.id.workers_lastname);
        salary = itemView.findViewById(R.id.workers_salary);
        type = itemView.findViewById(R.id.workers_type);
        parentLayout = itemView.findViewById(R.id.parent_layout_worker);
    }

    public void setWorker(final Worker worker) {
        workersName.setText(worker.getName());
        workerLastName.setText(worker.getLastName());
        salary.setText(String.valueOf(worker.getSalary()));
        type.setText(worker.getType());
        parentLayout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onWorkerClick(worker);
            }
        });
    }

    public void setListener(OnWorkerClickListener listener) {
        this.listener = listener;
    }
}
