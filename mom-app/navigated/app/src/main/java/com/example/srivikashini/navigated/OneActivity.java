package com.example.srivikashini.navigated;

/**
 * Created by Malini Ramki on 28-09-2017.
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

public class OneActivity extends Fragment {

    ListView startersList;
    String[] itemString, priceString, typeString, picString, cuisineString, itmid;
    String result, images;
    int num;

    public OneActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        result = b.getString("result");
        if(b.getString("images")!=null) images = b.getString("images");
        else Toast.makeText(getContext(),"Sorry couldn't fetch images.",Toast.LENGTH_LONG).show();
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
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ItemsAdapter adapter = new ItemsAdapter(getContext(), itmid, itemString, priceString, typeString, picString, cuisineString);
        startersList = OneActivity.this.getActivity().findViewById(R.id.startersList);
        startersList.setAdapter(adapter);
    }

    public void dataPopulator() throws JSONException {
        JSONObject json_data = new JSONObject(result);
        JSONArray array = json_data.getJSONArray("data");
        num = array.length(); int x=0; int count=0;
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
            if(row.getString("category").equals("Starters")){count++;}
        }
        itmid = new String[count];
        itemString = new String[count]; priceString = new String[count];
        cuisineString = new String[count];
        typeString = new String[count]; picString = new String[count];
        for(int ctrl=0; ctrl<num;ctrl++){
            JSONObject row = array.getJSONObject(ctrl);
            if(row.getString("category").equals("Starters")){
                itmid[x] = row.getString("id");
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
            if(row.getString("category").equals("Starters")){
                String photoData = row.getString("pic").substring(row.getString("pic").indexOf(",") + 1);
                picString[x] = photoData; x++;
            }
        }
    }
}





