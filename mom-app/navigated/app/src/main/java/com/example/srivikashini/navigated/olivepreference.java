package com.example.srivikashini.navigated;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
//        one = (Button)findViewById(R.id.button6);
//        two = (Button)findViewById(R.id.button8);
//        three = (Button)findViewById(R.id.button9);
//        four = (Button)findViewById(R.id.button10);
//        next = (Button)findViewById(R.id.button16);
//        t1 = (TextView)findViewById(R.id.textView2);
//        opone = (Button)findViewById(R.id.button15);
//        optwo = (Button)findViewById(R.id.button12);
//        opthree = (Button)findViewById(R.id.button13);
//        opfour = (Button)findViewById(R.id.button14);

//        category = "olive oil";
//        t1.setText(category);
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
                        finish();//finishing activity
                    }
                }}

            });
//        final String capsipref = getIntent().getExtras().getString("alters");
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//Toast.makeText(olivepreference.this, "The preferences are:" + val+ "\n" + category + ":" +quantityPref, Toast.LENGTH_LONG).show();
//    }
//        });

        //   }
//    public void btnSelected(View v)
//    {
//       switch (v.getId())
//       {
//           case R.id.button6 :
//
//
//             one.setText(opone.getText().toString());
//               two.setText("");
//               three.setText("");
//                four.setText("");
//               quantityPref = one.getText().toString();
//               break;
//           case R.id.button8 : two.setText(optwo.getText().toString());
//               three.setText("");
//               four.setText("");
//               one.setText("");
//
//               quantityPref = two.getText().toString();
//
//               break;
//           case R.id.button9 : three.setText(opthree.getText().toString());
//               one.setText("");
//               two.setText("");
//               four.setText("");
//
//               quantityPref = three.getText().toString();
//               break;
//           case R.id.button10 : four.setText(opfour.getText().toString());
//               one.setText("");
//               two.setText("");
//               three.setText("");
//               quantityPref = four.getText().toString();
//               break;
//
//       }
//    }
    }}