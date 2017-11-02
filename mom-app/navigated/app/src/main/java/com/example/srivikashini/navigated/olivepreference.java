package com.example.srivikashini.navigated;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by srivikashini on 29/09/17.
 */

public class olivepreference extends AppCompatActivity {

    Button one, two, three, four, next;
    TextView t1,opone,optwo,opthree,opfour;
    String category, quantityPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.olivelevelshap);
        one = (Button)findViewById(R.id.button6);
        two = (Button)findViewById(R.id.button8);
        three = (Button)findViewById(R.id.button9);
        four = (Button)findViewById(R.id.button10);
        next = (Button)findViewById(R.id.button16);
        t1 = (TextView)findViewById(R.id.textView2);
        opone = (Button)findViewById(R.id.button15);
        optwo = (Button)findViewById(R.id.button12);
        opthree = (Button)findViewById(R.id.button13);
        opfour = (Button)findViewById(R.id.button14);

        category = "olive oil";
        t1.setText(category);
        final String capsipref = getIntent().getExtras().getString("alters");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(olivepreference.this, "The preferences are:" + capsipref + "\n" + category + ":" +quantityPref, Toast.LENGTH_LONG).show();


            }
        });

    }
    public void btnSelected(View v)
    {
       switch (v.getId())
       {
           case R.id.button6 :


             one.setText(opone.getText().toString());
               two.setText("");
               three.setText("");
                four.setText("");
               quantityPref = one.getText().toString();
               break;
           case R.id.button8 : two.setText(optwo.getText().toString());
               three.setText("");
               four.setText("");
               one.setText("");

               quantityPref = two.getText().toString();

               break;
           case R.id.button9 : three.setText(opthree.getText().toString());
               one.setText("");
               two.setText("");
               four.setText("");

               quantityPref = three.getText().toString();
               break;
           case R.id.button10 : four.setText(opfour.getText().toString());
               one.setText("");
               two.setText("");
               three.setText("");
               quantityPref = four.getText().toString();
               break;

       }
    }
}