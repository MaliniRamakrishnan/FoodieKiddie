package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 26/10/17.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentActivity myContext;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String postData, url;
    Bundle bundle;
    Fragment one,two,three,four;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_main, container, false);


        try { getMenu(); }
        catch (Exception e) { e.printStackTrace(); }

        viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        // Inflate the layout for this fragment
        return rootView;
    }
    private void setupViewPager(ViewPager viewPager) {
        one = new OneActivity(); two = new TwoActivity();
        three = new ThreeActivity(); four = new FourActivity();
       ViewPagerAdapter adapter = new ViewPagerAdapter(myContext.getSupportFragmentManager());

        one.setArguments(bundle); two.setArguments(bundle);
        three.setArguments(bundle); four.setArguments(bundle);

        adapter.addFragment(one, "STARTERS");
        adapter.addFragment(two, "MAINS");
        adapter.addFragment(three, "SIDES");
        adapter.addFragment(four, "DESSERTS");
        viewPager.setAdapter(adapter);
    }

    public void getMenu() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.food_listUrl;

        String result = null;
        try {
            result = new BackgroundWorker(getContext()).execute(postData, url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json_data = new JSONObject(result);

        if (json_data.getString("data").equals("failed")) {
            Toast.makeText(getContext(), "Sorry, try again.", Toast.LENGTH_LONG).show();
        } else {
            bundle = new Bundle();
            bundle.putString("result",result);
        }
        getMenuPics();
    }

    public void getMenuPics() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String imgpostData = jsonObject.toString();
        String imgurl = Constants.food_pics;

        String images = null;
        try {
            images = new BackgroundWorker(getContext()).execute(imgpostData, imgurl).get();
            //Toast.makeText(getApplicationContext(),"img\n"+images,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(),"error img\n"+e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        JSONObject json_data = new JSONObject(images);

        if (json_data.getString("pics").equals("failed")) {
            Toast.makeText(getContext(), "Sorry, try again.", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(getApplicationContext(),"img\n"+images,Toast.LENGTH_LONG).show();
            bundle.putString("images",images);
        }
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public void onAttach(Activity activity) {

        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
