package com.example.srivikashini.myapplication;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    Button one, two, three, four;
    TextView t1;
    static String alter;
    Button bcks, dones;
    GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        t1 = (TextView) findViewById(R.id.dispstatus);
        bcks = (Button) findViewById(R.id.button);
        dones = (Button) findViewById(R.id.button1);

        bcks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "go back", Toast.LENGTH_LONG).show();

            }
        });
        dones.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, olivepreference.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("alters", alter);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);


            }
        });
    }

    public void btnpressed(View v) {
        if (v.getId() == R.id.one) {
            t1.setText(one.getText().toString());
            alter = one.getText().toString();
        } else if (v.getId() == R.id.two) {
            t1.setText(two.getText().toString());
            alter = two.getText().toString();
        } else if (v.getId() == R.id.three) {
            t1.setText(three.getText().toString());
            alter = three.getText().toString();
        } else if (v.getId() == R.id.four) {
            t1.setText(four.getText().toString());
            alter = four.getText().toString();

        }

    }}

