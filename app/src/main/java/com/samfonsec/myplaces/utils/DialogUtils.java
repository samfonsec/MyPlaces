package com.samfonsec.myplaces.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;

import com.samfonsec.myplaces.R;

public class DialogUtils {
    private static AlertDialog alertDialog;

    public static void showProgressDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_progress, null);
        builder.setView(dialogView);

        alertDialog = builder.create();
        alertDialog.setCancelable(false);

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        alertDialog.show();
    }

    public static void hideProgressDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public static void showErrorDialog(Context context, String btText, boolean cancelable,
                                       DialogInterface.OnClickListener listener) {
        hideProgressDialog();

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setCancelable(cancelable);
        builder.setPositiveButton(btText, listener);
        builder.setTitle(R.string.error_dialog_title)
                .setMessage(R.string.error_dialog_message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}
