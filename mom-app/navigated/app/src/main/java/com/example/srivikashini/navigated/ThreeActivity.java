package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 28/10/17.
 */

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

public class ThreeActivity extends Fragment{

    ListView sidesList;
    String[] itemString, priceString, typeString, picString, cuisineString, itemid;
    String result, images;
    int num;

    public ThreeActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        result = b.getString("result");
        if(b.getString("images")!=null) images = b.getString("images");
        else Toast.makeText(getContext(),"Sorry images couldn't be fetched.",Toast.LENGTH_LONG).show();
        try {
            if(result!=null&&images!=null) dataPopulator();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
ItemsAdapter adapter = new ItemsAdapter(getContext(), itemid, itemString, priceString, typeString, picString, cuisineString);
        sidesList = ThreeActivity.this.getActivity().findViewById(R.id.sidesList);
        sidesList.setAdapter(adapter);
    }

    public void dataPopulator() throws JSONException {
        JSONObject json_data = new JSONObject(result);
        JSONArray array = json_data.getJSONArray("data");
        num = array.length(); int x=0; int count=0;
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
            if(row.getString("category").equals("Sides")){count++;}
        }
        itemid = new String[count];
        itemString = new String[count]; priceString = new String[count];
        cuisineString = new String[count];
        typeString = new String[count]; picString = new String[count];
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
            if(row.getString("category").equals("Sides")){
                itemid[x] = row.getString("id");
                itemString[x] = row.getString("name");
                priceString[x] = row.getString("price");
                typeString[x] = row.getString("typeOfFood");
                cuisineString[x] = row.getString("cuisine");
                x++;
            }
        }
        imagePopulator();
    }

    public void imagePopulator() throws JSONException {
        JSONObject json_images = new JSONObject(images);
        JSONArray imgArray = json_images.getJSONArray("pics");
        int x=0;
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = imgArray.getJSONObject(ctrl);
            if(row.getString("category").equals("Sides")){
                String photoData = row.getString("pic").substring(row.getString("pic").indexOf(",") + 1);
                picString[x] = photoData; x++;
            }
        }
    }

}