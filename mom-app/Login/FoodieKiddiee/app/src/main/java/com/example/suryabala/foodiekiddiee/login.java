package com.example.suryabala.foodiekiddiee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class login extends AppCompatActivity {
    EditText edtemail,edtpassword;
    String postData, url;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edtemail = (EditText)findViewById(R.id.editText);
        edtpassword = (EditText)findViewById(R.id.editText2);
    }

    public void onLogin(View view) throws ExecutionException, InterruptedException, JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email",edtemail.getText().toString() );
            jsonObject.put("password",edtpassword.getText().toString());
            jsonObject.put("role", Constants.role);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.loginUrl;

        String result =  new BackgroundWorker(login.this).execute(postData,url).get() ;
        JSONObject json_data= new JSONObject(result);
        String data = json_data.getString("data");
        if(data.equals("failed")){
            Toast.makeText(login.this,"Invalid password or email address",Toast.LENGTH_LONG).show();
        }else {
            SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
            editor.putString("momID", data);
            editor.apply();
            Intent i = new Intent(login.this, MyAccount.class);
            startActivity(i);
        }
    }
}
