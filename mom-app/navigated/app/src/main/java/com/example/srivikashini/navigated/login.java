package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 26/10/17.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class login extends AppCompatActivity {
    EditText edtemail,edtpassword;
    String postData, url, typeChosen, postDatas;
    SharedPreferences sharedpreferences;
    private CheckBox checkBoxRememberMe;
    String id,kid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edtemail = (EditText)findViewById(R.id.editText);
        edtpassword = (EditText)findViewById(R.id.editText2);
        typeChosen = getIntent().getStringExtra("typel");
        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBox);
        if (!new PrefManager(this).isUserLogedOut()) {
            startHomeActivity();
        }

    }

    public void onLogin(View view) throws ExecutionException, InterruptedException, JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", edtemail.getText().toString());
            jsonObject.put("password", edtpassword.getText().toString());
            jsonObject.put("role", Constants.role);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.loginUrl;

        String result = new BackgroundWorker(login.this).execute(postData, url).get();
        JSONObject json_data = new JSONObject(result);
        String data = json_data.getString("data");
        Toast.makeText(login.this, data, Toast.LENGTH_LONG).show();
        if (data.equals("failed")) {
            Toast.makeText(login.this, "Invalid password or email address", Toast.LENGTH_LONG).show();
        } else {


            SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
            editor.putString("momId", data);
            editor.apply();

            if(typeChosen.equals("signin")) {
                kiddet();
                attemptLogin();

            }

            else if(typeChosen.equals("reg"))
            {
                Intent i = new Intent(login.this,addKid.class);
                startActivity(i);
            }
   }




    }

    private void kiddet() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
            id = prefs.getString("momID", "No name defined");
            Toast.makeText(login.this, id, Toast.LENGTH_LONG).show();
            jsonObject.put("momID",id );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postDatas = jsonObject.toString();
        url = Constants.fetchkidid;

        String result = null;
        try {
            result = new BackgroundWorker(login.this).execute(postDatas, url).get();
            JSONObject json_data= new JSONObject(result);
            JSONArray array = json_data.getJSONArray("data");
            int num = array.length(); int x=0; int count=0;
            for(int ctrl=0; ctrl<num;ctrl++) {
                JSONObject row = array.getJSONObject(ctrl);
                String em = row.getString("kidID");
                if(ctrl == 0)
                { SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
                editor.putString("kidsId", em);
                editor.apply();

                }


            }


//            if (value.equals("failed")){
//                Toast.makeText(login.this, "Not able to fetch data", Toast.LENGTH_LONG).show();
//            }
//            if (value.equals("")){
//                Toast.makeText(login.this, "Add kid before selecting menu", Toast.LENGTH_LONG).show();
//            }
//            else{
//               // Toast.makeText(login.this, value, Toast.LENGTH_LONG).show();
//                SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
//                editor.putString("kidsId", value);
//                editor.apply();
//
//            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private void attemptLogin() {

        edtemail.setError(null);
        edtpassword.setError(null);
        String email = edtemail.getText().toString();
        String password = edtpassword.getText().toString();
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        kid = prefs.getString("kidsId", "No name defined");
        Toast.makeText(login.this,kid,Toast.LENGTH_LONG).show();
        boolean cancel = false;
        View focusView = null;


        if (cancel) {

            focusView.requestFocus();
        } else {

            if (checkBoxRememberMe.isChecked())
                saveLoginDetails(email, password, kid);
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password, String kid) {

        new PrefManager(this).saveLoginDetails(email, password, kid);

    }

}
