package com.example.srivikashini.navigated;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by srivikashini on 30/11/17.
 */
public class cartFragment extends DialogFragment  {

    ListView dessertsList;
    String[] itemString, priceString, typeString, cuisineString, itemid;
    String result, images,postData,url,momsId,kidsId,results,resulte,pri;
    int num,quantity,prices,pripars;
    Integer price,pripar;
    ArrayList<String> mylist,priclist;
    HashMap<String ,Integer> hm;
    Button datefix,timefix,placeorder;
    TextView pricedisp;
    JSONArray arrays,pricearray;
    public cartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cartview_main, container, false);
        datefix = (Button)rootView.findViewById(R.id.button17);
        timefix = (Button)rootView.findViewById(R.id.button21);
        placeorder = (Button)rootView.findViewById(R.id.button24);
        pricedisp = (TextView)rootView.findViewById(R.id.textView20);
        mylist = new ArrayList<String>();
        priclist = new ArrayList<String>();
        quantity = 1;

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("momId", momsId);
                    jsonObject.put("kidId",kidsId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postData = jsonObject.toString();
                url = Constants.cartchosenfetchUrl;
                try{
                    results = new BackgroundWorker(getContext()).execute(postData, url).get();
                    JSONObject json_data= new JSONObject(results);

                   arrays = json_data.getJSONArray("choices");

                    int num = arrays.length(); int x=0; int count=0;

                    for(int ctrl=0; ctrl<num;ctrl++) {
                        JSONObject row = arrays.getJSONObject(ctrl);
                        mylist.add(row.getString("ItemId"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                JSONObject jsonObjects = new JSONObject();
                try {
                    jsonObjects.put("momId",momsId);
                    jsonObjects.put("kidID",kidsId );
                    jsonObjects.put("deliveryTime",timefix.getText().toString() );
                    jsonObjects.put("deliveryDate",datefix.getText().toString() );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObjectss = new JSONObject();
                try {
                    JSONArray jsArray = new JSONArray(mylist);
                    jsonObjectss.put("itemID",jsArray);
                    jsonObjectss.put("qty",quantity );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObjectsss = new JSONObject();
                try {
                    jsonObjectsss.put("choices",arrays);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObjectfin = new JSONObject();
                try {
                    jsonObjectfin.put("orderPart",jsonObjects);
                    jsonObjectfin.put("orderDetailsPart",jsonObjectss);
                    jsonObjectfin.put("orderItemDetailsPart",jsonObjectsss);
                    Toast.makeText(getContext(),jsonObjectfin.toString(),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                postData = jsonObject.toString();
                url = Constants.ordercreatUrl;

                try {

                    resulte = new BackgroundWorker(getContext()).execute(postData, url).get();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    FriendsFragment llf = new FriendsFragment();
                    ft.replace(R.id.hello, llf);
                    ft.commit();


                Intent i = new Intent(getContext(),FriendsFragment.class);
                    startActivity(i);




                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        timefix.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       // Toast.makeText(getContext(),"kefdf",Toast.LENGTH_LONG).show();

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timefix.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();





    }
});

        datefix.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog datePickerDialog;
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                datefix.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        JSONObject jsonObject = new JSONObject();
        try {
            SharedPreferences prefs = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
           momsId = prefs.getString("momId", "No name defined");
            SharedPreferences prefss = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
         kidsId = prefss.getString("kidsId", "No name defined");


            jsonObject.put("momId", momsId);
            jsonObject.put("kidId",kidsId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.cartfetchUrl;

        try {

            result = new BackgroundWorker(getContext()).execute(postData, url).get();
            JSONObject json_data= new JSONObject(result);

            pricearray = json_data.getJSONArray("data");

            int num = pricearray.length(); int x=0; int count=0;

            for(int ctrl=0; ctrl<num;ctrl++) {
                JSONObject row = pricearray.getJSONObject(ctrl);
                priclist.add(row.getString("price"));

            }

            for(int p=0; p<priclist.size();p++) {

               String v = priclist.get(p);
                int n1 = Integer.parseInt(v);
                prices = prices + n1;
                pri = String.valueOf(prices);

            }

            pricedisp.setText("Toatal Cost : " +pri.toString());
            dataPopulator();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        cartAdapter adapter = new cartAdapter(getContext(), itemid, itemString, priceString);
        dessertsList = (ListView)rootView.findViewById(R.id.listviewcartview);
        dessertsList.setAdapter(adapter);

        return rootView;
    }




    public void dataPopulator() throws JSONException {
        JSONObject json_data = new JSONObject(result);
        JSONArray array = json_data.getJSONArray("data");
        num = array.length(); int x=0; int count=0;
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);


            count++;
        }

        itemid = new String[count];
        itemString = new String[count]; priceString = new String[count];

        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
            itemid[x] = row.getString("momId");
            itemString[x] = row.getString("name");
            priceString[x] = row.getString("price");

            x++;

        }
    }


}
