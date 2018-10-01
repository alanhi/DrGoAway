package me.shl.drgoaway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import me.shl.drgoaway.utils.Dr;

public class MainActivity extends AppCompatActivity {
    private final static String Hi = "TO HACK BOY: DISS YOU";
    private EditText edit_username;
    private EditText edit_passwd;
    private SharedPreferences.Editor edit;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_username = findViewById(R.id.edit_username);
        edit_passwd = findViewById(R.id.edit_passwd);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        edit = sp.edit();

        edit_username.setText(sp.getString("username", ""));
        edit_passwd.setText(sp.getString("password", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboard:
                dashboard();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dashboard() {
        Uri uri = Uri.parse("http://10.10.10.8:8080/Self");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void login(View v) {
        if (!edit_username.getText().toString().equals("") || !edit_passwd.getText().toString().equals("")) {
            Dr.login(edit_username.getText().toString(), edit_passwd.getText().toString(), MainActivity.this);
            edit.putString("username", edit_username.getText().toString());
            edit.putString("password", edit_passwd.getText().toString());
            edit.apply();
        }
    }

    public void logout(View v) {
        Dr.logout(edit_username.getText().toString(), edit_passwd.getText().toString(), MainActivity.this);
    }
}
