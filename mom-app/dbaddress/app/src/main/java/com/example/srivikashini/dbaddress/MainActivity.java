package com.example.srivikashini.dbaddress;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText school, loctype;
    Button subm;
    String schools, loctypes;

    String postData, url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        school = (EditText)findViewById(R.id.editText);
        loctype = (EditText)findViewById(R.id.editText2);
        subm = (Button)findViewById(R.id.button);
        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this,"sooper",Toast.LENGTH_LONG).show();
                schools = school.getText().toString();
                loctypes = loctype.getText().toString();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("school", schools);
                    jsonObject.put("locationtype", loctypes);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postData = jsonObject.toString();
                url = Constants.schoolUrl;

                try {
                    String result = new BackgroundWorker(MainActivity.this).execute(postData, url).get();
                    JSONObject json_data= new JSONObject(result);
                    String data = json_data.getString("data");
                    switch(data){
                        case "failed":
                            Toast.makeText(MainActivity.this,"Network error",Toast.LENGTH_LONG).show();break;
                        case "success":
                            Toast.makeText(MainActivity.this,"sooper",Toast.LENGTH_LONG).show();break;



                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}




