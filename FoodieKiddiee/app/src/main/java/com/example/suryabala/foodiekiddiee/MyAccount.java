package com.example.suryabala.foodiekiddiee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MyAccount extends AppCompatActivity {

    EditText edtEmail, edtContact,edtName;
    Button saveBtn;
    String postData,url,id;
    Boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        saveBtn = (Button)findViewById(R.id.save);
        saveBtn.setVisibility(View.INVISIBLE);

        edtEmail = (EditText)findViewById(R.id.email);
        edtEmail.setEnabled(false);
        edtName = (EditText)findViewById(R.id.name);
        edtName.setEnabled(false);
        edtContact = (EditText)findViewById(R.id.contact);
        edtContact.setEnabled(false);

        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        id = prefs.getString("momID", "No name defined");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.viewAccountUrl;

        try {
            String result = new BackgroundWorker(MyAccount.this).execute(postData, url).get();
            JSONObject json_data= new JSONObject(result);
            edtName.setText(json_data.getString("name"));
            edtEmail.setText(json_data.getString("email"));
            edtContact.setText(json_data.getString("phone"));
        } catch (InterruptedException e) {
        e.printStackTrace();
        } catch (ExecutionException e) {
        e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void edit1Tapped(View view){
        edtEmail.setEnabled(true);
        edtEmail.setHint(edtEmail.getText().toString());
        edtEmail.setText("");
        saveBtn.setVisibility(View.VISIBLE);
        flag = true;
    }

    public void edit2Tapped(View view){
        edtContact.setEnabled(true);
        edtContact.setHint(edtContact.getText().toString());
        edtContact.setText("");
        saveBtn.setVisibility(View.VISIBLE);
    }

    public void edit3Tapped(View view){
        edtName.setEnabled(true);
        edtName.setHint(edtName.getText().toString());
        edtName.setText("");
        saveBtn.setVisibility(View.VISIBLE);
    }

    public void saveBtnTapped(View view){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", id);
            jsonObject.put("flag",flag);
            jsonObject.put("email", edtEmail.getText().toString());
            jsonObject.put("contact", edtContact.getText().toString());
            jsonObject.put("name", edtName.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.editAccountUrl;

        try {
            String result = new BackgroundWorker(MyAccount.this).execute(postData, url).get();
            JSONObject json_data= new JSONObject(result);
            String data = json_data.getString("data");

            switch(data){
                case "failed":
                    Toast.makeText(MyAccount.this,"Network error",Toast.LENGTH_LONG).show();break;
                case "Exists":
                    Toast.makeText(MyAccount.this,"Email id already exists",Toast.LENGTH_LONG).show();break;
                default:
                    Toast.makeText(MyAccount.this,"success",Toast.LENGTH_LONG).show();break;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void btnTapped(View view){
        Intent i = new Intent(MyAccount.this, changePassword.class);
        startActivity(i);
    }
}


