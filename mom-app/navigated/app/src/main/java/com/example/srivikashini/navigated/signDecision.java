package com.example.srivikashini.navigated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by srivikashini on 21/11/17.
 */

public class signDecision extends Activity {
    Button signup, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homedecision);
        signup = (Button)findViewById(R.id.button18);
        signin = (Button)findViewById(R.id.button19);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signDecision.this,signUp.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signDecision.this,login.class);
                i.putExtra("typel","signin");
                startActivity(i);
            }
        });
    }

}