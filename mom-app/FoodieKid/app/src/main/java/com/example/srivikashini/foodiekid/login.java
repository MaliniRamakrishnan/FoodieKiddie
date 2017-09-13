package com.example.srivikashini.foodiekid;
import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;




public class login extends AppCompatActivity {

    String em, pa;
    EditText email,passwor;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = (EditText)findViewById(R.id.editText);
        passwor = (EditText)findViewById(R.id.editText2);
        log = (Button)findViewById(R.id.button3);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = email.getText().toString();
                pa = passwor.getText().toString();
                if (em.equals("hello") && pa.equals("hello")){
                    Intent in = new Intent(login.this,myaccount.class);
                    startActivity(in);
                }
                else {
                    Toast.makeText(login.this,"Invalid details",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}