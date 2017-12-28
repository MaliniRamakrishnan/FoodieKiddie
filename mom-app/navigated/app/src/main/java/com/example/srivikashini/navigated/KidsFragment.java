package com.example.srivikashini.navigated;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by srivikashini on 22/11/17.
 */

public class KidsFragment extends Fragment {

    ListView dessertsList;
    String[] kidid, kidname, kiddob, picString;
    String result, momid, postData, url, images;
    int num;


    public KidsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fetch_kid_main, container, false);



    JSONObject jsonObject = new JSONObject();
        try {
            SharedPreferences prefs = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
            String momsId = prefs.getString("momId", "No name defined");
            Toast.makeText(getContext(),momsId,Toast.LENGTH_LONG).show();
            jsonObject.put("momId", momsId);
    } catch (JSONException e) {
        e.printStackTrace();
    }
    postData = jsonObject.toString();
    url = Constants.fetchkidUrl;

        try {

        result = new BackgroundWorker(getContext()).execute(postData, url).get();

        dataPopulator();
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (JSONException e) {
            e.printStackTrace();
        }

    KidsAdapter adapter = new KidsAdapter(getContext(), kidid, kidname, kiddob);

    dessertsList = (ListView)rootView.findViewById(R.id.listkid);
        dessertsList.setAdapter(adapter);

        return rootView;
    }




    public void dataPopulator() throws JSONException {
        JSONObject json_data = new JSONObject(result);
        JSONArray array = json_data.getJSONArray("data");

        num = array.length();
        int x = 0;
        int count = 0;
        for (int ctrl = 0; ctrl < num; ctrl++) {
            JSONObject row = array.getJSONObject(ctrl);
            //   if(row.getString("category").equals("Desserts")){
            count++;
            //   }
        }
        kidid = new String[count];
        kidname = new String[count];
        kiddob = new String[count];


        for (int ctrl = 0; ctrl < num; ctrl++) {
            JSONObject row = array.getJSONObject(ctrl);

            kidid[x] = row.getString("kidID");
            kidname[x] = row.getString("name");
            kiddob[x] = row.getString("dob");


            x++;

        }

    }

}
