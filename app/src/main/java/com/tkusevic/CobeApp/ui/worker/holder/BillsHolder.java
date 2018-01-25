package com.tkusevic.CobeApp.ui.worker.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Bill;
import com.tkusevic.CobeApp.ui.listeners.OnBillClickListener;

/**
 * Created by tkusevic on 23.01.2018..
 */

public class BillsHolder extends RecyclerView.ViewHolder {

    private OnBillClickListener listener;
    private TextView receiptView;
    private TextView priceView;
    private LinearLayout parentLayout;

    public BillsHolder(View view) {
        super(view);
        receiptView = itemView.findViewById(R.id.recieptsView);
        priceView = itemView.findViewById(R.id.priceView);
        parentLayout = itemView.findViewById(R.id.parent_layout_receipt);
    }

    public void setBill(final Bill bill) {
        receiptView.setText(bill.toString());
        priceView.setText(String.valueOf(bill.getSumPrice()));
        parentLayout.setOnClickListener((View v) -> {
            if (listener != null) {
                listener.onRepeatClick(bill);
            }
        });
    }

    public void setListener(OnBillClickListener listener) {
        this.listener = listener;
    }
}
