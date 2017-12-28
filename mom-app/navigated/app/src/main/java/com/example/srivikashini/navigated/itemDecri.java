package com.example.srivikashini.navigated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.security.AccessController.getContext;

/**
 * Created by srivikashini on 30/10/17.
 */

public class itemDecri extends AppCompatActivity {
    ListView ingr;
    Button alterna, addtocart, addtoWish;
    ArrayList<String> mylist,list,listnam,lisnum;
    String postData,url,sam,fudId,message;
    String[] ingsid, ingid;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_item);
        ingr = (ListView)findViewById(R.id.list_ingre);
        alterna = (Button)findViewById(R.id.button);
        addtocart = (Button)findViewById(R.id.button9);
        addtoWish = (Button)findViewById(R.id.button23);
        mylist = new ArrayList<String>();
        list = new ArrayList<String>();
        listnam = new ArrayList<String>();
        lisnum = new ArrayList<String>();
    //getting bundle
        final String itmId = getIntent().getExtras().getString("itmid");
        final String resu = getIntent().getExtras().getString("value");
        final String foodname = getIntent().getExtras().getString("fudName");
        final String foodcusisine = getIntent().getExtras().getString("fooddcusine");
        final String foodtype = getIntent().getExtras().getString("fudType");
        final String foodprice = getIntent().getExtras().getString("fudCost");
        Toast.makeText(itemDecri.this, foodprice, Toast.LENGTH_SHORT).show();
        SharedPreferences prefss = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        final String kidsId = prefss.getString("kidsId", "No name defined");

        //looping bundle
        try{
        JSONObject json_data= new JSONObject(resu);
        JSONArray array = json_data.getJSONArray("data");
            int num = array.length(); int x=0; int count=0;

          for(int ctrl=0; ctrl<num;ctrl++) {
           JSONObject row = array.getJSONObject(ctrl);
           //     ingsid[ctrl] = row.getString("id");
              mylist.add(row.getString("name"));
                listnam.add(row.getString("id"));
              }

    } catch (JSONException e) {
            e.printStackTrace();
        }

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

                for(int i = 0; i < num; i++){
                    list.add(array.getJSONObject(i).getString("ingredientsID"));
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tvs = (TextView) view.findViewById(android.R.id.text1);
                tvs.setTextColor(Color.BLACK);
                tvs.setGravity(Gravity.CENTER);
                tvs.setTextSize(18);
                return view;
            }
        };
        ingr.setAdapter(arrayAdapter);
        ingr.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String item = (String) ingr.getItemAtPosition(position);

                int j= position;

                for (int k=0;k<listnam.size();k++){
                    if( j==k) {
                        fudId= listnam.get(k).toString();
                        Toast.makeText(itemDecri.this,fudId,Toast.LENGTH_LONG).show();
                    }}

                JSONObject jsonObject = new JSONObject();
                try {
                    Toast.makeText(itemDecri.this,fudId,Toast.LENGTH_LONG).show();
                    jsonObject.put("fudname",fudId);
                    jsonObject.put("itmId",itmId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postData = jsonObject.toString();
                url = Constants.fetchalterUrl;

                try {

                    String result = new BackgroundWorker(itemDecri.this).execute(postData, url).get();
                    Intent i = new Intent(itemDecri.this,olivepreference.class);
                    i.putExtra("value",result);
                    startActivityForResult(i,2);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });


        addtoWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
                String momsId = prefs.getString("momId", "No name defined");



                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("momID",momsId );
                    jsonObject.put("kidID",kidsId);
                    jsonObject.put("itemID",itmId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postData = jsonObject.toString();
                url = Constants.addwishUrl;

                try {

                    String result = new BackgroundWorker(itemDecri.this).execute(postData, url).get();
                    JSONObject json_data= new JSONObject(result);

                        Toast.makeText(itemDecri.this, json_data.toString(), Toast.LENGTH_SHORT).show();
//                        Intent i =new Intent(itemDecri.this,cartFragment.class);
//                    startActivity(i);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }








        });
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

                SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
                String momsId = prefs.getString("momId", "No name defined");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("momID",momsId );
                    jsonObject.put("kidID",kidsId);
                    jsonObject.put("itemID",itmId);
                    jsonObject.put("ingID",fudId);
                    jsonObject.put("chosenIng",message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postData = jsonObject.toString();
                url = Constants.cartinsertUrl;

                try {

                    String result = new BackgroundWorker(itemDecri.this).execute(postData, url).get();
                    cartFragment card = new cartFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.replace(R.id.linearframe, card);


                    fragmentTransaction.commit();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            message=data.getStringExtra("MESSAGE");
            Toast.makeText(itemDecri.this,message, Toast.LENGTH_SHORT).show();

        }
    }
}