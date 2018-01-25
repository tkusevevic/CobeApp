package com.tkusevic.CobeApp.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.data.model.Bill;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.ui.listeners.OnDeleteListener;
import com.tkusevic.CobeApp.ui.listeners.OnRepeatClickListener;

import java.util.Locale;

/**
 * Created by tkusevic on 23.01.2018..
 */

public class AlertUtils {

    public static void getAlertDialogProduct(Context context, Product product, final OnDeleteListener onDeleteListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(String.format(Locale.getDefault(), context.getString(R.string.jeste_li_sigurni_format_1), product.getName()))
                .setPositiveButton(context.getString(R.string.da), (dialog, which) -> onDeleteListener.onOptionYes())
                .setNegativeButton(context.getString(R.string.ne), (dialog, which) -> onDeleteListener.onOptionNo()).
                show();
    }

    public static void getAlertDialogWorker(Context context, Worker worker, final OnDeleteListener onDeleteListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(String.format(Locale.getDefault(), context.getString(R.string.jeste_li_sigurni_format_2), worker.getName(), worker.getLastName()))
                .setPositiveButton(context.getString(R.string.da), (dialog, which) -> onDeleteListener.onOptionYes())
                .setNegativeButton(context.getString(R.string.ne), (dialog, which) -> onDeleteListener.onOptionNo()).
                show();
    }

    @SuppressLint("StringFormatMatches")
    public static void getAlertDialogBill(Context context, Bill bill, final OnRepeatClickListener onRepeatClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(String.format(Locale.getDefault(), context.getString(R.string.aler_dialog_bill_format), bill.getProducts(), bill.getSumPrice()))
                .setPositiveButton(context.getString(R.string.da), (dialog, which) -> onRepeatClickListener.onOptionYes())
                .setNegativeButton(context.getString(R.string.ne), (dialog, which) -> onRepeatClickListener.onOptionNo()).
                show();
    }
}

