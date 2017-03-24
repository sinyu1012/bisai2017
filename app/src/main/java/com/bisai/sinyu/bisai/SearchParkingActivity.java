package com.bisai.sinyu.bisai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bisai.sinyu.DB.ParkingDao;
import com.bisai.sinyu.Http.GetHttpConnectionData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class SearchParkingActivity extends AppCompatActivity {
    LocationManager locationManager;
    private String provider;//位置提供器
    private Location location;

    private ImageView img_dingwei;
    private Button btn_ok;
    private EditText et_weizhi;
    private ParkingDao dao;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parking);
        img_dingwei = (ImageView) findViewById(R.id.img_dingwei);
        btn_ok = (Button) findViewById(R.id.btn_OK);
        et_weizhi = (EditText) findViewById(R.id.et_weizhi);
        dao = new ParkingDao(SearchParkingActivity.this);
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {/**如果装载TTS成功*/
                        int result = tts.setLanguage(Locale.ENGLISH);/**有Locale.CHINESE,但是不支持中文*/
                    if (result == TextToSpeech.LANG_MISSING_DATA/**表示语言的数据丢失。*/
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {/**语言不支持*/
                        Toast.makeText(SearchParkingActivity.this, "我说不出口", Toast.LENGTH_SHORT).show();
                    } else {
                       //
                        tts.speak("I MISS YOU", TextToSpeech.QUEUE_FLUSH,
                                null);
                    }
                }
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et_weizhi.getText().toString();
                // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                tts.setPitch(1.0f);
                // 设置语速
                tts.setSpeechRate(0.3f);
                if (str.length() >= 1) {
                    tts.speak(str.toString(),
                            TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    tts.speak("Nothing to say", TextToSpeech.QUEUE_FLUSH, null);
                }

               // dao.addParking("大厦停车场", 100, 60, "大厦", "6:00-21:00", 1, 0);
//                Intent intent = new Intent(SearchParkingActivity.this, SelectParkingActivity.class);
//                startActivity(intent);
            }
        });
        img_dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("", "0");
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                provider = judgeProvider(locationManager);
                if (provider != null) {
                    if (ActivityCompat.checkSelfPermission(SearchParkingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SearchParkingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    location = locationManager.getLastKnownLocation(provider);
                    if (location != null) {
                        getLocation(location);//得到当前经纬度并开启线程去反向地理编码
                    } else {
                    }
                } else {//不存在位置提供器的情况

                }
            }
        });

    }

    public void getLocation(Location location) {
        String latitude = location.getLatitude() + "";
        String longitude = location.getLongitude() + "";
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=pPGNKs75nVZPloDFuppTLFO3WXebPgXg&callback=renderReverse&location=" + latitude + "," + longitude + "&output=json&pois=0";
        Log.d("", url);
        new MyAsyncTask(url).execute();
    }

    /**
     * 判断是否有可用的内容提供器
     */
    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(SearchParkingActivity.this, "没有可用位置提供器", Toast.LENGTH_SHORT).show();
        }
        return null;
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
            try {
                str = str.replace("renderReverse&&renderReverse", "");
                str = str.replace("(", "");
                str = str.replace(")", "");
                JSONObject jsonObject = new JSONObject(str);
                JSONObject address = jsonObject.getJSONObject("result");
                String city = address.getString("formatted_address");
                String district = address.getString("sematic_description");
                et_weizhi.setText(city + district);
                // tv_show.setText("当前位置："+city+district);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(aVoid);
        }
    }

}
