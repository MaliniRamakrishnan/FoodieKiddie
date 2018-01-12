package com.example.srivikashini.navigated;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

/**
 * Created by srivikashini on 29/09/17.
 */

public class olivepreference extends AppCompatActivity {
    ListView pref;
    Button one, two, three, four, next;
    TextView t1,opone,optwo,opthree,opfour;
    String fudids,category, quantityPref;
    ArrayList<String> mylists,mynames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perf_main);
        pref = (ListView)findViewById(R.id.pref_list);
        mylists = new ArrayList<String>();
        mynames = new ArrayList<String>();

        final String val = getIntent().getExtras().getString("value");
       Toast.makeText(olivepreference.this, "The preferences are:" + val , Toast.LENGTH_LONG).show();

        try{
            JSONObject json_data= new JSONObject(val);
            JSONArray array = json_data.getJSONArray("data");
            int num = array.length(); int x=0; int count=0;

            for(int ctrl=0; ctrl<num;ctrl++) {
                JSONObject row = array.getJSONObject(ctrl);
                //     ingsid[ctrl] = row.getString("id");
                mylists.add(row.getString("name"));
                mynames.add(row.getString("id"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
//
//
//
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylists){
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
        pref.setAdapter(arrayAdapter);
       // pref.setAdapter(arrayAdapter);
        pref.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) pref.getItemAtPosition(position);
                int f= position;

                for (int k=0;k<mynames.size();k++){
                   if( f==k) {
                        fudids= mynames.get(k).toString();
                       Toast.makeText(olivepreference.this,fudids,Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.putExtra("MESSAGE",fudids);
                        setResult(2,intent);
                        finish();}//finishing activity
                  }
                }

            });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("select the alternatives");

        builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    } }