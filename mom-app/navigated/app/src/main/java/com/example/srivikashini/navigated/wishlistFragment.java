package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 29/11/17.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class wishlistFragment extends Fragment {

    ListView dessertsList;
    String[] itemString, priceString, typeString, cuisineString, itemid;
    String result, images,postData,url;
    int num;


    public wishlistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wish_main, container, false);



        JSONObject jsonObject = new JSONObject();
        try {
            SharedPreferences prefs = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
            String momsId = prefs.getString("momId", "No name defined");
            SharedPreferences prefss = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
            final String kidsId = prefss.getString("kidsId", "No name defined");


            jsonObject.put("momID", momsId);
            jsonObject.put("kidID",kidsId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.fetchwishUrl;

        try {

            result = new BackgroundWorker(getContext()).execute(postData, url).get();
           // Toast.makeText(getContext(),result.toString(),Toast.LENGTH_LONG).show();
            dataPopulator();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        wishadapter adapter = new wishadapter(getContext(), itemid, itemString, priceString, typeString, cuisineString);
        dessertsList = (ListView)rootView.findViewById(R.id.wish_list);
        dessertsList.setAdapter(adapter);

        return rootView;
    }




    public void dataPopulator() throws JSONException {
        JSONObject json_data = new JSONObject(result);
        JSONArray array = json_data.getJSONArray("data");

       // Toast.makeText(getContext(),result.toString(),Toast.LENGTH_LONG).show();
        num = array.length(); int x=0; int count=0;
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
           count++;
        }
        itemid = new String[count];
        itemString = new String[count]; priceString = new String[count];
        cuisineString = new String[count];
        typeString = new String[count];
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);

                itemid[x] = row.getString("id");
                itemString[x] = row.getString("name");
                priceString[x] = row.getString("price");
                typeString[x] = row.getString("typeOfFood");
                cuisineString[x] = row.getString("cuisine");
                x++;

        }
    }

}
