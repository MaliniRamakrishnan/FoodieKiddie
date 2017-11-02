package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 26/10/17.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class signUp extends AppCompatActivity {

    String mail;
    String pass;
    String repass;
    String contact,name;
    EditText names;
    String postData, url;
    EditText mails,passs, repasss, contacts;
    Button login, signup,extra;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);
        names = (EditText)findViewById(R.id.editText3);
        mails = (EditText)findViewById(R.id.editText4);
        passs = (EditText)findViewById(R.id.editText5);
        repasss = (EditText)findViewById(R.id.editText6);
        contacts = (EditText)findViewById(R.id.editText7);
        signup = (Button)findViewById(R.id.signup);
        extra = (Button) findViewById(R.id.addkid);
    extra.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent nav = new Intent(signUp.this, MainActivity.class);
        startActivity(nav);
    }
    });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = mails.getText().toString();
                pass = passs.getText().toString();
                repass = repasss.getText().toString();
                name = names.getText().toString();
                contact = contacts.getText().toString();


                if ((name.equals("")) || (mail.equals("")) || (pass.equals("")) || (repass.equals("")) || (contact.equals("")) ) {
                    Toast.makeText(signUp.this, "Enter all the fields", Toast.LENGTH_LONG).show();

                } else {
                    if ((!(repass.equals(pass)))) {
                        Toast.makeText(signUp.this, "password doesn't match", Toast.LENGTH_LONG).show();
                    } else {

                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("name", name);
                            jsonObject.put("email", mail);
                            jsonObject.put("password", pass);
                            jsonObject.put("phone", contact);
                            jsonObject.put("role", Constants.role);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        postData = jsonObject.toString();
                        url = Constants.regUrl;

                        try {
                            String result = new BackgroundWorker(signUp.this).execute(postData, url).get();
                            JSONObject json_data= new JSONObject(result);
                            String data = json_data.getString("data");
                            switch(data){
                                case "failed":
                                    Toast.makeText(signUp.this,"Network error",Toast.LENGTH_LONG).show();break;
                                case "Exists":
                                    Toast.makeText(signUp.this,"Account already exists", Toast.LENGTH_LONG).show();break;
                                default:
                                    SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
                                    editor.putString("momID", data);
                                    editor.apply();

                                    Intent i = new Intent(signUp.this, myAccount.class);
                                    startActivity(i);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    public void onTappedLogin(View view){
        Intent i = new Intent(signUp.this, login.class);
        startActivity(i);
    }


}
