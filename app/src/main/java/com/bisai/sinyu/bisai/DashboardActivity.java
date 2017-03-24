package com.bisai.sinyu.bisai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listView= (ListView) findViewById(R.id.lv_dashboard);

        listView.setAdapter(new MyAdapter());
    }
    public class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=View.inflate(DashboardActivity.this,R.layout.list_dashboard,null);


            return v;
        }
    }
}
