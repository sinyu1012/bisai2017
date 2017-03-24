package com.bisai.sinyu.bisai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by sinyu on 2017/2/20.
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

    }
    public void login(View v)
    {
        Intent intent=new Intent(LoginActivity.this,SelectParkingActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Toast.makeText(this,keyCode,Toast.LENGTH_LONG).show();
        return true;
    }
}
