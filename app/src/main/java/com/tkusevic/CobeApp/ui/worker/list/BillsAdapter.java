package com.tkusevic.CobeApp.ui.worker.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Bill;
import com.tkusevic.CobeApp.ui.listeners.OnBillClickListener;
import com.tkusevic.CobeApp.ui.worker.holder.BillsHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 17.01.2018..
 */

public class BillsAdapter extends RecyclerView.Adapter<BillsHolder> {

    private List<Bill> mReceipts = new ArrayList<>();
    private OnBillClickListener listener;

    public void setOnBillClickListener(OnBillClickListener listener) {
        this.listener = listener;
    }

    public void setBill(List<Bill> receipts) {
        mReceipts.clear();
        mReceipts.addAll(receipts);
        notifyDataSetChanged();
    }

    public void addBill(Bill bill) {
        mReceipts.add(bill);
        notifyDataSetChanged();
    }

    @Override
    public BillsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder_bills, parent, false);
        return new BillsHolder(view);
    }

    @Override
    public void onBindViewHolder(BillsHolder holder, int position) {
        if (mReceipts != null) {
            final Bill bill = mReceipts.get(position);
            holder.setBill(bill);
            holder.setListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return mReceipts.size();
    }
}
