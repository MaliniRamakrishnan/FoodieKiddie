package com.example.srivikashini.foodiekid;



        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

/**
 * Created by vikashini on 26/8/17.
 */

public class changepass extends AppCompatActivity {
    Button savePass;
    EditText old, newp, renewp;
    String olds, newps, renewps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassowrd);
        old = (EditText)findViewById(R.id.editText9);
        newp = (EditText)findViewById(R.id.editText10);
        renewp = (EditText)findViewById(R.id.editText11);
        savePass = (Button)findViewById(R.id.button5);
        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                olds = old.getText().toString();
                newps = newp.getText().toString();
                renewps = renewp.getText().toString();
                Toast.makeText(changepass.this,"password changed",Toast.LENGTH_SHORT).show();

            }
        });
    }
}