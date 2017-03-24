package com.bisai.sinyu.bisai;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bisai.sinyu.DB.ParkingDao;
import com.bisai.sinyu.DB.ParkingInfo;

import java.util.List;

public class SelectParkingActivity extends Activity {
    private ListView lv_tcc;
    private boolean isClose=true;
    private ParkingDao dao;
    private List<ParkingInfo> infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stopcar);
        dao = new ParkingDao(this);
        infos = dao.findParking();
        Log.d("infos:", infos.size() + "   ----");
        lv_tcc = (ListView) findViewById(R.id.lv_tcc);
        Myadpter myadpter = new Myadpter();
        lv_tcc.setAdapter(myadpter);
        SortedList<ParkingInfo> INFO;

        infos.add(null);
        myadpter.notifyDataSetChanged();

        lv_tcc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SelectParkingActivity.this, ParkingInfoActivity.class);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},1);
        } else
        {

        }
    }
    private class Myadpter extends BaseAdapter{
        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v=View.inflate(SelectParkingActivity.this,R.layout.list_selecttcc,null);
            final ParkingInfo info=infos.get(position);
            TextView txt_name=(TextView) v.findViewById(R.id.txt_name);
            TextView txt_content=(TextView) v.findViewById(R.id.txt_content);
            TextView txt_yytime=(TextView) v.findViewById(R.id.txt_yytime);
            Button btn_go=(Button) v.findViewById(R.id.btn_go);
            ImageView img=(ImageView) v.findViewById(R.id.img_flag);
            txt_name.setText(info.getName());
            txt_content.setText(info.getContent());
            txt_yytime.setText("营业时间:"+info.getTime());
            btn_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SelectParkingActivity.this,"数："+info.getName().toString(),Toast.LENGTH_SHORT).show();
                }
            });
            if(info.getState()==0)
            {
                img.setVisibility(View.VISIBLE);
                btn_go.setEnabled(false);
            }
           else
            {
                img.setVisibility(View.GONE);
            }

            return v;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
