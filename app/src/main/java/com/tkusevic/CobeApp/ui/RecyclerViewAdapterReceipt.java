package com.tkusevic.CobeApp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 17.01.2018..
 */

public class RecyclerViewAdapterReceipt extends RecyclerView.Adapter<RecyclerViewAdapterReceipt.ViewHolder> {


    private List<Recipe> mReceipts = new ArrayList<>();
    private OnReceiptClickListener listener;

    public void setOnReciepeClickListener(OnReceiptClickListener listener) {
        this.listener = listener;
    }


    public void setReceipts(List<Recipe> receipts) {
        mReceipts.clear();
        mReceipts.addAll(receipts);
        notifyDataSetChanged();
    }

    public void addReceipt(Recipe recipe) {
        mReceipts.add(recipe);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewAdapterReceipt.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_holder_receipt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterReceipt.ViewHolder holder, int position) {
        if (mReceipts != null) {
            final Recipe recipe = mReceipts.get(position);
            holder.receiptView.setText(recipe.toString());
            holder.priceView.setText(String.valueOf(recipe.getSumPrice()));
            holder.repeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRepeatClick(recipe);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mReceipts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView receiptView;
        TextView priceView;
        LinearLayout parentLayout;
        Button repeat;

        public ViewHolder(View itemView) {
            super(itemView);
            receiptView = itemView.findViewById(R.id.recieptsView);
            priceView = itemView.findViewById(R.id.priceView);
            parentLayout = itemView.findViewById(R.id.parent_layout_receipt);
            repeat = itemView.findViewById(R.id.repeat);
        }
    }
}
