package com.example.srivikashini.shardprf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by srivikashini on 24/11/17.
 */

public class MainActivity extends AppCompatActivity {

    ListView dessertsList;
    String[] kidid, kidname, kiddob, picString;
    String result, momid, postData, url, images;
    int num;
    String hel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch_kid_main);
        momid = "PARA916814";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String imgpostData = jsonObject.toString();
        String imgurl = Constants.addkidPic;


        try {
            images = new BackgroundWorker(MainActivity.this).execute(imgpostData, imgurl).get();
           // Toast.makeText(MainActivity.this, images.toString(), Toast.LENGTH_LONG).show();
            imagePopulator();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        KidsAdapter adapter = new KidsAdapter(MainActivity.this,hel);

        dessertsList = (ListView)findViewById(R.id.listkid);
        dessertsList.setAdapter(adapter);


    }

    public void imagePopulator() throws JSONException {
   hel = images;

        Toast.makeText(MainActivity.this, hel, Toast.LENGTH_LONG).show();
        }

    }








