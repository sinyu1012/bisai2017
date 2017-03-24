package com.bisai.sinyu.bisai;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bisai.sinyu.Http.GetHttpConnectionData;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_search,btn_dashboard,btn_ser,btn_yinpin,btn_forget,btn_readcontacts,btn_POST,btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_search= (Button) findViewById(R.id.btn_search);
        btn_dashboard= (Button) findViewById(R.id.btn_dashboard);
        btn_ser= (Button) findViewById(R.id.btn_ser);
        btn_yinpin= (Button) findViewById(R.id.btn_yinpin);
        btn_forget= (Button) findViewById(R.id.btn_forget);
        btn_readcontacts= (Button) findViewById(R.id.btn_readcontacts);
        btn_POST= (Button) findViewById(R.id.btn_POST);
        btn_update= (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_POST.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_dashboard.setOnClickListener(this);
        btn_ser.setOnClickListener(this);
        btn_yinpin.setOnClickListener(this);
        btn_forget.setOnClickListener(this);
        btn_readcontacts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_dashboard:
                Intent intent1=new Intent(Main2Activity.this,DashboardActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_search:
                Intent intent2=new Intent(Main2Activity.this,SearchParkingActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_ser:
                Intent intent3=new Intent(Main2Activity.this,TimerActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_yinpin:
                Intent intent4=new Intent(Main2Activity.this,MediaActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_forget:
                Intent intent5=new Intent(Main2Activity.this,SMSActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_readcontacts:
                Intent intent6=new Intent(Main2Activity.this,ContactsActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_POST:
                Toast.makeText(Main2Activity.this,"1",Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url="http://192.168.1.103:8080/transportservice/type/jason/action/GetCarSpeed.do";
                       // String url="http://202.119.168.66:8080/test/feedbackServlet";//
                        String res=GetHttpConnectionData.getData(url);
                        Log.d("HTTP:",res);
                    }
                }).start();

                String str="http://192.168.1.103:8080/transportservice/type/jason/action/GetAllSense.do";

               // new MyAsyncTask(str).execute();

                break;
            case R.id.btn_update:
                Intent intent7=new Intent(Main2Activity.this,Update5Activity.class);
                startActivity(intent7);
                break;
        }
    }
    class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        String url = null;//要请求的网址
        String str = null;//服务器返回的数据
        String address = null;

        public MyAsyncTask(String url) {
            this.url = url;
        }

        @Override
        protected Void doInBackground(Void... params) {
            str = GetHttpConnectionData.getData(url);
            Log.d("HTTP:", str);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            try {
//                str = str.replace("renderReverse&&renderReverse", "");
//                str = str.replace("(", "");
//                str = str.replace(")", "");
//                JSONObject jsonObject = new JSONObject(str);
//                JSONObject address = jsonObject.getJSONObject("result");
//                String city = address.getString("formatted_address");
//                String district = address.getString("sematic_description");
//               // et_weizhi.setText(city + district);
//                // tv_show.setText("当前位置："+city+district);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            super.onPostExecute(aVoid);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

       // Toast.makeText(this,keyCode,Toast.LENGTH_LONG).show();
        Log.d("key123445",keyCode+"");
        return true;
    }

}
