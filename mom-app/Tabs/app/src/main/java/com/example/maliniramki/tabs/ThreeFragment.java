package com.example.maliniramki.tabs;

/**
 * Created by Malini Ramki on 28-09-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ThreeFragment extends Fragment{

    ListView sidesList;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        String[] itemString = {"Veg Dum Biriyani","Curd Rice","Mexican Tacos"};
        String[] priceString = {"120","70","100"};
        int[] drawableIds = {R.drawable.item1, R.drawable.item2, R.drawable.item3};

        ItemsAdapter adapter = new ItemsAdapter(getContext(), itemString, priceString, drawableIds);
        sidesList = ThreeFragment.this.getActivity().findViewById(R.id.sidesList);
        sidesList.setAdapter(adapter);
    }

}