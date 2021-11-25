package com.example.hotelmontain.util;

import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;

public class EditTextUtil {

    public static int getInt(TextInputEditText inputEditText) {
        try {
            return Integer.parseInt(inputEditText.getText().toString());

        } catch (Exception e) {
            Log.e("getInt", e.getMessage());
        }

        return 0;
    }

    public static double getDouble(TextInputEditText inputEditText) {
        try {
            return Double.parseDouble(inputEditText.getText().toString());

        } catch (Exception e) {
            Log.e("getDouble", e.getMessage());
        }

        return 0;
    }

    public static boolean isEmpty(TextInputEditText inputEditText) {
        return inputEditText.getText().toString().isEmpty();
    }

    public static String getTexto(TextInputEditText inputEditText) {
        return inputEditText.getText().toString().trim();
    }
}
