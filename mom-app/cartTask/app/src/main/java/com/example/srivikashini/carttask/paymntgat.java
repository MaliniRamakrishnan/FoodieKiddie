package com.example.srivikashini.carttask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class paymntgat extends AppCompatActivity {

    ListView paymnt;
    String[] banName = {"STATE BANK OF INDIA", "INDIAN BANK"};
    String[] accNoo = {"3456 6789 6384", "8765 8973 3456"};
    String[] accDat = {"43 567", "89 765"};

    //String[] accName = {"SWEDHA", "MALINI"};
    private Toolbar toolba;
    @Override   protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentsgate);
        paymnt = (ListView)findViewById(R.id.listviewpayment);
        Button bacbut = (Button)findViewById(R.id.button);


        toolba = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolba);
      CustomPayment customPayment = new CustomPayment();
        paymnt.setAdapter(customPayment);
        bacbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(paymntgat.this,MainActivity.class);
                startActivity(in);
            }
        });
        }
        class CustomPayment extends BaseAdapter
        {

            @Override
            public int getCount() {
                return 2;
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

                views = getLayoutInflater().inflate(R.layout.paymentlistview,null);
                Button bankName = (Button)views.findViewById(R.id.button3);
                TextView accNo = (TextView)views.findViewById(R.id.textView8);
                TextView accName = (TextView)views.findViewById(R.id.textView9);
                TextView accDates = (TextView)views.findViewById(R.id.textView11);

                accNo.setText("\t\t\t\t\t\t"+ accNoo[i]);
                bankName.setText(banName[i]);
                accDates.setText(accDat[i]);
                accName.setText("SWEDHA K");


                bankName.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent in = new Intent(paymntgat.this,orderdhistory.class);
                        startActivity(in);
                    }
                });
                return views;
            }


        }

    }