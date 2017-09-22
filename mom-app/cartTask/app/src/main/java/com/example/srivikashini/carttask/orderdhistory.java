package com.example.srivikashini.carttask;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
/**
 * Created by srivikashini on 21/09/17.
 */

public class orderdhistory extends AppCompatActivity {
final Context context = this;
    ListView orderhis;
//    String[] banName = {"STATE BANK OF INDIA", "INDIAN BANK"};
//    String[] accNoo = {"3456 6789 6384", "8765 8973 3456"};
//    String[] accDat = {"43 567", "89 765"};

    //String[] accName = {"SWEDHA", "MALINI"};
    private Toolbar toolba;
    @Override   protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhistory);
        orderhis = (ListView)findViewById(R.id.listvieworderhis);
        Button filters = (Button)findViewById(R.id.button5);


        toolba = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolba);
        CustomOrder customOrder = new CustomOrder();
        orderhis.setAdapter(customOrder);
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(orderdhistory.this,MainActivity.class);

                startActivity(in);
            }
        });
    }
    class CustomOrder extends BaseAdapter {

        @Override
        public int getCount() {
            return 3;
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
        public View getView(int i, View views, ViewGroup viewGroup) {

            views = getLayoutInflater().inflate(R.layout.orderlistview, null);
            final Button feedback = (Button) views.findViewById(R.id.button6);
            final Button statuss = (Button) views.findViewById(R.id.button7);
            TextView foodname = (TextView) views.findViewById(R.id.textView13);
            TextView pricefood = (TextView) views.findViewById(R.id.textView14);
            TextView daydates = (TextView) views.findViewById(R.id.textView7);

            foodname.setText("SALAD");
            pricefood.setText("RS. 20");
            daydates.setText("Monday");


            feedback.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                    final Dialog dialog = new Dialog(orderdhistory.this);
                    dialog.setContentView(R.layout.review);


                    // set the custom dialog components - text, image and button

                    Button but = (Button)dialog.findViewById(R.id.buttonrev);


                    // Button dialogButton = (Button) dialog.findViewById(R.id.button8);
//                    // if button is clicked, close the custom dialog

                    but.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText reviw = (EditText)dialog.findViewById(R.id.editText4);
                            String revie = reviw.getText().toString();
                            RatingBar ratingBar = (RatingBar)dialog.findViewById(R.id.ratingBar2);
                            String foodrating = String.valueOf(ratingBar.getRating());
                            Toast.makeText(orderdhistory.this,revie + foodrating,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });




                    dialog.show();


                    }
            });


            statuss.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    statuss.setEnabled(false);
                    feedback.setEnabled(false);
                }
            });
            return views;
        }
    }
    }
