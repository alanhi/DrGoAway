package me.shl.drgoaway.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.widget.Toast;

public class UI {
    private final static String Hi = "TO HACK BOY: DISS YOU";

    public static void showToast(final Activity context, final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void buttonEnable(final Activity context, @IdRes final int id, final Boolean enable) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.findViewById(id).setEnabled(enable);
            }
        });
    }
}
