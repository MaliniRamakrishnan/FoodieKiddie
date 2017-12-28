package com.example.srivikashini.navigated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by srivikashini on 24/10/17.
 */

public class kidscladdr extends AppCompatActivity {

    EditText sclName,locDoorNo,locLandmark,locStreet,locCity,locState,locCountry,locPin,locLatitude,locLongitude;
    String sclNames,locDoorNos,locLandmarks,locStreets,locCitys,locStates,locCountrys,locPins,locLatitudes,locLongitudes, postData,url,momsId,kidsId;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kidaddrdet);

        sclName = (EditText)findViewById(R.id.editText13);
        locDoorNo = (EditText)findViewById(R.id.editText14);
        locLandmark = (EditText)findViewById(R.id.editText15);
        locStreet = (EditText)findViewById(R.id.editText17);
        locCity = (EditText)findViewById(R.id.editText18);
        locState = (EditText)findViewById(R.id.editText25);
        locCountry = (EditText)findViewById(R.id.editText26);
        locPin = (EditText)findViewById(R.id.editText28);
        locLatitude = (EditText)findViewById(R.id.editText29);
        locLongitude = (EditText)findViewById(R.id.editText30);
        submit = (Button)findViewById(R.id.button3);


       final String kidName = getIntent().getStringExtra("kname");
        final String kidRegno = getIntent().getExtras().getString("kregno");
        final String kiddob = getIntent().getExtras().getString("kdob");
        final String kidclas = getIntent().getStringExtra("kclas");
        byte[] byteArray = getIntent().getByteArrayExtra("kidImage");
        final String imgstr = Base64.encodeToString(byteArray, 0);
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        momsId = prefs.getString("momId", "No name defined");
        SharedPreferences prefss = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        kidsId = prefss.getString("kidsId", "No name defined");
        Toast.makeText(kidscladdr.this,kidsId.toString(),Toast.LENGTH_LONG).show();
       submit.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
                sclNames = sclName.getText().toString();
                locDoorNos= locDoorNo.getText().toString();
                locLandmarks = locLandmark.getText().toString();
                locStreets = locStreet.getText().toString();
                locCitys = locCity.getText().toString();
                locStates = locState.getText().toString();
                locCountrys = locCountry.getText().toString();
                locPins = locPin.getText().toString();
                locLatitudes = locLatitude.getText().toString();
                locLongitudes = locLongitude.getText().toString();

               JSONObject jsonObject = new JSONObject();
               try {
                    jsonObject.put("imageStr",imgstr);
                    jsonObject.put("momId", momsId);
                    jsonObject.put("kidName", kidName);
                    jsonObject.put("kidRegNo", kidRegno);
                    jsonObject.put("kiddob", kiddob);
                   jsonObject.put("kidsection", kidclas);
                    jsonObject.put("sclName", sclNames);
                    jsonObject.put("locDoorNo", locDoorNo);
                    jsonObject.put("locLandmarks", locLandmarks);
                    jsonObject.put("locStreet", locStreets);
                    jsonObject.put("locCity", locCitys);
                    jsonObject.put("locState", locStates);
                    jsonObject.put("locCountry", locCountrys);
                    jsonObject.put("locPin", locPins);
                    jsonObject.put("LocLatitude", locLatitudes);
                    jsonObject.put("locLongitude", locLongitudes);

               } catch (JSONException e) {
                   e.printStackTrace();
               }
               postData = jsonObject.toString();

               url = Constants.addkidUrl;

               try {
                   String result = new BackgroundWorker(kidscladdr.this).execute(postData, url).get();
                   JSONObject json_data = new JSONObject(result);
                   String value = json_data.getString("data");
                  // Toast.makeText(kidscladdr.this,value,Toast.LENGTH_LONG).show();
                  if(value == "failed"){
                      Toast.makeText(kidscladdr.this,"Registration failed",Toast.LENGTH_LONG).show();
                  }
                  else
                  {
                      Toast.makeText(kidscladdr.this,"Registration success",Toast.LENGTH_LONG).show();
                      SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE).edit();
                      editor.putString("kidsId", value);
                      editor.apply();

                      Intent homep = new Intent(kidscladdr.this,MainActivity.class);
                      startActivity(homep);
                  }
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       });

    }
}
