package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 26/10/17.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

public class FriendsFragment extends DialogFragment  {

    ListView dessertsList;
    String[] itemString, priceString, typeString, cuisineString, itemid;
    String result, images,postData,url,momsId,kidsId,results,resulte,pri;
    int num,quantity,prices,pripars;
    Integer price,pripar;
    ArrayList<String> mylist,priclist;
    HashMap<String ,Integer> hm;
    Button datefix,timefix,placeorder;
    TextView pricedisp;
    JSONArray arrays,pricearray;
    public FriendsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);



        JSONObject jsonObject = new JSONObject();
        try {
//            SharedPreferences prefs = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
//            momsId = prefs.getString("momId", "No name defined");
            SharedPreferences prefss = getContext().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
            kidsId = prefss.getString("kidsId", "No name defined");

            jsonObject.put("kidId",kidsId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.orderfetchUrl;

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

        FriendAdapter adapter = new FriendAdapter(getContext(), itemid, itemString, priceString,typeString,cuisineString);
        dessertsList = (ListView)rootView.findViewById(R.id.listviewordersdg);
        dessertsList.setAdapter(adapter);

        return rootView;
    }




    public void dataPopulator() throws JSONException {
        JSONObject json_data = new JSONObject(result);
        JSONArray array = json_data.getJSONArray("data");


        num = array.length(); int x=0; int count=0;
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);

            count++;
        }

        itemid = new String[count];
        itemString = new String[count]; priceString = new String[count];
        cuisineString = new String[count];
        typeString = new String[count];

        for(int ctrl=0; ctrl<num; ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
           // Toast.makeText(getContext(),row.getString(""),Toast.LENGTH_LONG).show();
            itemid[x] = row.getString("orderID");
            itemString[x] = row.getString("deliveryDate");
            priceString[x] = row.getString("totalAmount");
            typeString[x] = row.getString("deliveryTime");
            cuisineString[x] = row.getString("status");



            x++;

        }
    }


}
