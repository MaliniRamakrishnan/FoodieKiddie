package com.example.srivikashini.navigated;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by srivikashini on 31/10/17.
 */

public class cartlist extends AppCompatActivity {
    ListView listcart;
    String foodname,foodcusisine,foodtype,foodprice,foodItmID;
    ArrayList list;
    String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartview_main);
        listcart = (ListView)findViewById(R.id.listviewcartview);

        CustomOrder customOrder = new CustomOrder();
        listcart.setAdapter(customOrder);
        foodItmID = getIntent().getExtras().getString("itemID");
        foodname = getIntent().getExtras().getString("fudName");
        foodcusisine = getIntent().getExtras().getString("fudCusine");
          foodtype = getIntent().getExtras().getString("fudtype");
          foodprice = getIntent().getExtras().getString("fudPrice");
        Toast.makeText(cartlist.this, foodprice, Toast.LENGTH_SHORT).show();
         list = new ArrayList<String>();

        values = new String[]{foodItmID, foodname, foodtype, foodprice};
     //   Toast.makeText(cartlist.this,values.toString(),Toast.LENGTH_LONG).show();
       list.addAll(Arrays.asList(values));
        for (int i =0; i<list.size();i++){
            Toast.makeText(cartlist.this,list.get(i).toString(),Toast.LENGTH_LONG).show();
        }


    }




    class CustomOrder extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View views, ViewGroup viewGroup) {

            views = getLayoutInflater().inflate(R.layout.cartmain_listview, null);
            final TextView datefix = (TextView) views.findViewById(R.id.textView4);
            TextView foodName = (TextView) views.findViewById(R.id.textView10);
            TextView foodCost = (TextView) views.findViewById(R.id.textView12);
            ImageView foofType = (ImageView)views.findViewById(R.id.imageView2);
            final Button timePic = (Button)views.findViewById(R.id.button11);
            Button placeOrder = (Button)views.findViewById(R.id.button17);
            CardView cd = (CardView)views.findViewById(R.id.cardView);


            placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    JSONObject orderPart = new JSONObject();
                    try {
                        orderPart.put("deliveryTime", timePic.getText().toString());
                        orderPart.put("deliveryDate", datefix.getText().toString());
                        orderPart.put("KidIs", "KIDSAR120");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }






//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("name", name);
//                        jsonObject.put("email", mail);
//                        jsonObject.put("password", pass);
//                        jsonObject.put("phone", contact);
//                        jsonObject.put("role", Constants.role);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    postData = jsonObject.toString();
//                    url = Constants.regUrl;
//
//                    try {
//                        String result = new BackgroundWorker(signUp.this).execute(postData, url).get();
//                        JSONObject json_data= new JSONObject(result);
//                        String data = json_data.getString("data");
//                        switch(data){
//                            case "failed":
//                                Toast.makeText(signUp.this,"Network error",Toast.LENGTH_LONG).show();break;
//                            case "Exists":
//                                Toast.makeText(signUp.this,"Account already exists", Toast.LENGTH_LONG).show();break;
//                            default:
//                                SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
//                                editor.putString("momID", data);
//                                editor.apply();
//
//                                Intent i = new Intent(signUp.this, myAccount.class);
//                                startActivity(i);
//                        }
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            });
            cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = i;
                    Toast.makeText(cartlist.this,"Order created successfully",Toast.LENGTH_SHORT).show();
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
                    datePickerDialog = new DatePickerDialog(cartlist.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // set day of month , month and year value in the edit text
                                    datefix.setText(dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            });
            timePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(cartlist.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            timePic.setText(selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });


            foodName.setText(foodname);
          //  fudcusine.setText(foodcusisine);
            foodCost.setText("Rs."+ foodprice);
            //Toast.makeText(cartlist.this,foodCost+"",Toast.LENGTH_SHORT).show();

            if(foodtype == "V"){
                foofType.setImageResource(R.drawable.icon_veg);

            }
            else{
                foofType.setImageResource(R.drawable.icon_nonveg);

            }

            return views;


        }

    }

}

