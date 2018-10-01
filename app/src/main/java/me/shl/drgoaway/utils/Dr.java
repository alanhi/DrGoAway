package me.shl.drgoaway.utils;

import android.app.Activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import me.shl.drgoaway.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Dr {
    private final static String Hi = "TO HACK BOY: DISS YOU";

    public static void logout(String username, String passwd, final Activity context) {
        UI.buttonEnable(context, R.id.button_logout, false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .addHeader("User-Agent", "DrCOM-HttpClient")
                            .addHeader("Charset", "utf-8")
                            .url("http://10.10.10.5/F.htm")
                            .get()
                            .build();
                    response = client.newCall(request).execute();
                    if (response.isSuccessful() && response.body() != null) {
                        UI.showToast(context, context.getString(R.string.msg_logout_succeeded));
                    } else {
                        UI.showToast(context, context.getString(R.string.msg_server_error));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    UI.showToast(context, context.getString(R.string.msg_connect_failed));
                } finally {
                    UI.buttonEnable(context, R.id.button_logout, true);
                }
            }
        }).start();
    }

    public static void login(final String username, final String passwd, final Activity context) {
        UI.buttonEnable(context, R.id.button_login, false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder()
                            .add("DDDDD", username)
                            .add("upass", passwd)
                            .add("m1", "000000000000")
                            .add("0MKKey", "0123456789")
                            .add("ver", "1.5.100.201701112.G.L.A.D")
                            .add("sim_sp", "undefine")
                            .add("cver1", "1")
                            .add("cver2", "0010501000")
                            .add("R6", "1")
                            .build();
                    Request request = new Request.Builder()
                            .addHeader("User-Agent", "DrCOM-HttpClient")
                            .addHeader("Uip", "va5=1.2.3.4.7d90b22d5534741c6057abd51f0317a090824c79")
                            .addHeader("Time", String.valueOf(System.currentTimeMillis() / 1000))
                            .addHeader("Date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE).format(System.currentTimeMillis()))
                            .addHeader("Charset", "utf-8")
                            .url("http://10.10.10.5")
                            .post(body)
                            .build();
                    response = client.newCall(request).execute();
                    if (response.isSuccessful() && response.body() != null) {
                        String r = response.body().string();
                        if (r.contains("<!--Dr.COMWebLoginID_2.htm-->")) {
                            UI.showToast(context, context.getString(R.string.msg_login_failed));
                        } else if (r.contains("<!--Dr.COMWebLoginID_3.htm-->")) {
                            UI.showToast(context, context.getString(R.string.msg_login_succeeded));
                        } else {
                            UI.showToast(context, context.getString(R.string.msg_login_failed_unknown));
                        }
                    } else {
                        UI.showToast(context, context.getString(R.string.msg_server_error));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    UI.showToast(context, context.getString(R.string.msg_connect_failed));
                } finally {
                    UI.buttonEnable(context, R.id.button_login, true);
                }
            }
        }).start();
    }
}
