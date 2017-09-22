package com.example.srivikashini.carttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


        // Array of strings...
        ListView simpleList;
        String foodtype[] = {"Italian", "southIndian", "NorthIndian", "Franchise", "Japanese", "chinese"};
        String dateoforder[] = {"mon", "tues", "wed", "thurs", "fri", "saturday"};
        private Toolbar toolbar;
    Button goback, done;
        @Override   protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            toolbar = (Toolbar)findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);
            simpleList = (ListView)findViewById(R.id.listView);
            goback = (Button)findViewById(R.id.button);
            done = (Button)findViewById(R.id.button2);
            CustomAdapter customAdapter = new CustomAdapter();
            simpleList.setAdapter(customAdapter);

    goback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this,"goback clicked",Toast.LENGTH_LONG).show();
        }
    });


            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Toast.makeText(MainActivity.this,"goback clicked",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, paymntgat.class);
                    startActivity(i);
                     }
            });
                 }



    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activitylistliew,null);
            ImageView img = (ImageView)view.findViewById(R.id.icon);
            TextView foodtypes = (TextView)view.findViewById(R.id.textView);
            TextView dateoforders = (TextView)view.findViewById(R.id.textView1);

            img.setImageResource(R.mipmap.ic_launcher);
            foodtypes.setText(foodtype[i]);
            dateoforders.setText(dateoforder[i]);

            return view;
        }
    }
    }
