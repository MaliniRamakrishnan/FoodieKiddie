package com.example.srivikashini.foodiekid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addKid extends AppCompatActivity {

    EditText knam, reg, dob, schl, addre;
    String knams, regs, dobs, schls, addres;
    Button savekid,bactolo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkids);
        knam = (EditText)findViewById(R.id.editText24);
        reg = (EditText)findViewById(R.id.editText25);
        dob = (EditText)findViewById(R.id.editText26);
        schl = (EditText)findViewById(R.id.editText29);
        addre = (EditText)findViewById(R.id.editText30);
        savekid = (Button)findViewById(R.id.button10);
        bactolo = (Button)findViewById(R.id.button8);

        savekid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knams = knam.getText().toString();
                regs = reg.getText().toString();
                dobs = dob.getText().toString();
                schls = schl.getText().toString();
                addres = addre.getText().toString();
                Toast.makeText(addKid.this,"data======>"+knams+regs+dobs+schls+addres,Toast.LENGTH_SHORT).show();
            }
        });
        bactolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bk = new Intent(addKid.this,MainActivity.class);
                startActivity(bk);
            }
        });

    }
}