package com.example.srivikashini.navigated;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.srivikashini.navigated.BackgroundWorker;
import com.example.srivikashini.navigated.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by srivikashini on 21/10/17.
 */

public class changePassword extends AppCompatActivity {
    EditText oldPwd, newPwd, reEnterPwd;
    String postData,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        oldPwd = (EditText)findViewById(R.id.oldpass);
        newPwd = (EditText)findViewById(R.id.newpass);
        reEnterPwd = (EditText)findViewById(R.id.renewpass);
    }

    public void onSave(View view){
        String oldPassword = oldPwd.getText().toString();
        String newPassword = newPwd.getText().toString();
        String rnewPassword = reEnterPwd.getText().toString();
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        String id = prefs.getString("momID", "No name defined");


        if(newPassword.equals(rnewPassword)){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("oldPassword", oldPassword);
                jsonObject.put("newPassword",newPassword);
                jsonObject.put("userId",id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            postData = jsonObject.toString();
            //   url = SyncStateContract.Constants.changePassUrl;
            url = Constants.changePassUrl;


            try {
                String result = new BackgroundWorker(changePassword.this).execute(postData, url).get();
                JSONObject json_data= new JSONObject(result);
                String data = json_data.getString("data");
                Toast.makeText(changePassword.this,data,Toast.LENGTH_LONG).show();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(changePassword.this,"password mismatches", Toast.LENGTH_LONG).show();
        }
    }

}
