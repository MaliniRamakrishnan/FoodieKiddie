package com.example.srivikashini.foodiekid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class myaccount extends AppCompatActivity {
    Toolbar toolbars;
    Button edits, changPasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        toolbars = (Toolbar) findViewById(R.id.toolbarl);
        setSupportActionBar(toolbars);
        edits = (Button)findViewById(R.id.button7);
        changPasts = (Button)findViewById(R.id.button6);

        edits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myaccount.this,"hello", Toast.LENGTH_LONG).show();
            }
        });
        changPasts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nxt = new Intent(myaccount.this,changepass.class);
                startActivity(nxt);
            }
        });
    }}