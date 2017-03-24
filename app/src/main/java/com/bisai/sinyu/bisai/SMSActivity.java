package com.bisai.sinyu.bisai;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bisai.sinyu.tools.MyObserver;

import java.util.zip.Inflater;

public class SMSActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed1,ed2;
    Button btn_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
    }
    public void forget(View view)
    {
       View v=View.inflate(this,R.layout.dialog_forget,null);
        ed1= (EditText) v.findViewById(R.id.ed1);
        ed2= (EditText) v.findViewById(R.id.ed2);
        btn_get= (Button) v.findViewById(R.id.getyzm);
        btn_get.setOnClickListener(this);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("验证码");
        builder.setView(v);
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定",null);
        builder.show();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1)
            {
                String yzm=msg.obj.toString();
                ed2.setText(yzm);
            }
        }
    };
    @Override
    public void onClick(View v) {
        MyObserver myObserver=new MyObserver(this,handler);
        Uri uri=Uri.parse("content://sms/");
        getContentResolver().registerContentObserver(uri,true,myObserver);

    }
}
