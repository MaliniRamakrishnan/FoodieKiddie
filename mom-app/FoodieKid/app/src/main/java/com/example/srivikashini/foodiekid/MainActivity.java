package com.example.srivikashini.foodiekid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {


    String mail, pass, repass, contact;
    EditText mails,passs, repasss, contacts;
    Button login, signup,extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mails = (EditText)findViewById(R.id.editText4);
        passs = (EditText)findViewById(R.id.editText5);
        repasss = (EditText)findViewById(R.id.editText6);
        contacts = (EditText)findViewById(R.id.editText7);
        login = (Button)findViewById(R.id.button2);
        signup = (Button)findViewById(R.id.button);
        extra = (Button) findViewById(R.id.button4);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = mails.getText().toString();
                pass = passs.getText().toString();
                repass = repasss.getText().toString();
                contact = contacts.getText().toString();


                if ((mail.equals("")) || (pass.equals("")) || (repass.equals("")) || (contact.equals("")) ) {
                    Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_LONG).show();

                } else if ((!(repass.equals(pass)))) {
                    Toast.makeText(MainActivity.this, "password doesn't match", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "successfully registered", Toast.LENGTH_LONG).show();
                }

            }
        });
        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(MainActivity.this, addKid.class);
                startActivity(io);
            }
        });
    }
}