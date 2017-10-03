package com.example.srivikashini.myapplication;

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
        setContentView(R.layout.olivepref);
        one = (Button)findViewById(R.id.button2);
        two = (Button)findViewById(R.id.button3);
        three = (Button)findViewById(R.id.button4);
        four = (Button)findViewById(R.id.button5);
        next = (Button)findViewById(R.id.button7);
        t1 = (TextView)findViewById(R.id.textView2);
        opone = (TextView)findViewById(R.id.textView);
        optwo = (TextView)findViewById(R.id.textView3);
        opthree = (TextView)findViewById(R.id.textView4);
        opfour = (TextView)findViewById(R.id.textView5);
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
           case R.id.button2 : opone.setText(one.getText().toString());
               optwo.setText("");
               opthree.setText("");
                opfour.setText("");
               quantityPref = one.getText().toString();
               break;
           case R.id.button3 : optwo.setText(two.getText().toString());
               opthree.setText("");
               opfour.setText("");
               opone.setText("");

               quantityPref = two.getText().toString();

               break;
           case R.id.button4 : opthree.setText(three.getText().toString());
               opone.setText("");
               optwo.setText("");
               opfour.setText("");

               quantityPref = three.getText().toString();
               break;
           case R.id.button5 : opfour.setText(four.getText().toString());
               opone.setText("");
               optwo.setText("");
               opthree.setText("");
               quantityPref = four.getText().toString();
               break;

       }
    }
}