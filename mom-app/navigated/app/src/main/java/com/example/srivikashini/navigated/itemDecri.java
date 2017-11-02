package com.example.srivikashini.navigated;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by srivikashini on 30/10/17.
 */

public class itemDecri extends AppCompatActivity {
    ListView ingr;
    Button alterna, addtocart;
    ArrayList<String> mylist;
    String postData,url;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_item);
        ingr = (ListView)findViewById(R.id.list_ingre);
        alterna = (Button)findViewById(R.id.button);
        addtocart = (Button)findViewById(R.id.button9);
        mylist = new ArrayList<String>();

    //getting bundle
        final String itmId = getIntent().getExtras().getString("itmid");
        final String resu = getIntent().getExtras().getString("value");
        final String foodname = getIntent().getExtras().getString("fudName");
        final String foodcusisine = getIntent().getExtras().getString("fooddcusine");
        final String foodtype = getIntent().getExtras().getString("fudType");
        final String foodprice = getIntent().getExtras().getString("fudCost");
        Toast.makeText(itemDecri.this, foodprice, Toast.LENGTH_SHORT).show();

        //looping bundle
        try{
        JSONObject json_data= new JSONObject(resu);
        JSONArray array = json_data.getJSONArray("data");
            int num = array.length(); int x=0; int count=0;
          for(int ctrl=0; ctrl<num;ctrl++) {
           JSONObject row = array.getJSONObject(ctrl);
              mylist.add(row.getString("name"));
              }

    } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                return view;
            }
        };
        ingr.setAdapter(arrayAdapter);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fudId",itmId );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.fudalternate;

        try {

            String result = new BackgroundWorker(itemDecri.this).execute(postData, url).get();
            JSONObject json_data= new JSONObject(result);
            JSONArray array = json_data.getJSONArray("data");
            int num = array.length(); int x=0; int count=0;
            for(int ctrl=0; ctrl<num;ctrl++) {
                JSONObject row = array.getJSONObject(ctrl);
                //mylist.add(row.getString("name"));
                Toast.makeText(itemDecri.this, row.toString(), Toast.LENGTH_SHORT).show();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        alterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(itemDecri.this,gridPreference.class);
                startActivity(i);
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(itemDecri.this,cartlist.class);
                Bundle mbundle = new Bundle();
                mbundle.putString("itemID",itmId);
                mbundle.putString("fudName",foodname);
                mbundle.putString("fudCusine",foodcusisine);
                mbundle.putString("fudtype",foodtype);
                mbundle.putString("fudPrice",foodprice);
                i.putExtras(mbundle);
                startActivity(i);
            }
        });
    }
    }