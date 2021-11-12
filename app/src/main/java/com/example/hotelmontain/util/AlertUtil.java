package com.example.hotelmontain.util;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

public class AlertUtil {

    public static void showAlert(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setTitle("Atenção")
                .setPositiveButton("OK", null)
                .show();
    }

    public static void showSucesso(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setTitle("Informação")
                .setPositiveButton("OK", null)
                .show();
    }
}