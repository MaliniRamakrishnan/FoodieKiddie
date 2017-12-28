package com.example.srivikashini.navigated;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by srivikashini on 03/12/17.
 */


public class itemdecrifrag extends Fragment {

    ListView ingr;
    Button alterna, addtocart, addtoWish;
    ArrayList<String> mylist,list,listnam,lisnum;
    String postData,url,sam,fudId,message;
    String[] ingsid, ingid;
    public itemdecrifrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.desc_item, container, false);
        ingr = (ListView) rootView.findViewById(R.id.list_ingre);
        alterna = (Button) rootView.findViewById(R.id.button);
        addtocart = (Button) rootView.findViewById(R.id.button9);
        addtoWish = (Button) rootView.findViewById(R.id.button23);
        mylist = new ArrayList<String>();
        list = new ArrayList<String>();
        listnam = new ArrayList<String>();
        lisnum = new ArrayList<String>();
        final String itmId = getActivity().getIntent().getExtras().getString("itmid");
        final String resu = getActivity().getIntent().getExtras().getString("value");
        final String foodname = getActivity().getIntent().getExtras().getString("fudName");
        final String foodcusisine = getActivity().getIntent().getExtras().getString("fooddcusine");
        final String foodtype = getActivity().getIntent().getExtras().getString("fudType");
        final String foodprice = getActivity().getIntent().getExtras().getString("fudCost");
        Toast.makeText(getContext(), foodprice, Toast.LENGTH_SHORT).show();
        SharedPreferences prefss = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        final String kidsId = prefss.getString("kidsId", "No name defined");
        try {
            JSONObject json_data = new JSONObject(resu);
            JSONArray array = json_data.getJSONArray("data");
            int num = array.length();
            int x = 0;
            int count = 0;

            for (int ctrl = 0; ctrl < num; ctrl++) {
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
            jsonObject.put("fudId", itmId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.fudalternate;

        try {

            String result = new BackgroundWorker(getContext()).execute(postData, url).get();
            JSONObject json_data = new JSONObject(result);
            JSONArray array = json_data.getJSONArray("data");
            int num = array.length();
            int x = 0;
            int count = 0;

            for (int i = 0; i < num; i++) {
                list.add(array.getJSONObject(i).getString("ingredientsID"));
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mylist) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) ingr.getItemAtPosition(position);

                int j = position;

                for (int k = 0; k < listnam.size(); k++) {
                    if (j == k) {
                        fudId = listnam.get(k).toString();
                        Toast.makeText(getContext(), fudId, Toast.LENGTH_LONG).show();
                    }
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    Toast.makeText(getContext(), fudId, Toast.LENGTH_LONG).show();
                    jsonObject.put("fudname", fudId);
                    jsonObject.put("itmId", itmId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postData = jsonObject.toString();
                url = Constants.fetchalterUrl;

                try {

                    String result = new BackgroundWorker(getContext()).execute(postData, url).get();
                    Intent i = new Intent(getContext(), olivepreference.class);
                    i.putExtra("value", result);
                    startActivityForResult(i, 2);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });

        return rootView;
    }}