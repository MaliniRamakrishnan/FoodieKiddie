package com.example.srivikashini.navigated;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by srivikashini on 22/11/17.
 */
public class accountFragment extends Fragment {
    EditText edtEmail, edtContact,edtName;
    Button saveBtn, next;
    String postData,url,id;
    Boolean flag = false;
    Fragment one, two, three, four;

    public accountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_account, container, false);


        saveBtn = (Button)rootView.findViewById(R.id.save);
        saveBtn.setVisibility(View.INVISIBLE);
        next = (Button)rootView.findViewById(R.id.button4);
        edtEmail = (EditText)rootView.findViewById(R.id.email);
        edtEmail.setEnabled(false);
        edtName = (EditText)rootView.findViewById(R.id.name);
        edtName.setEnabled(false);
        edtContact = (EditText)rootView.findViewById(R.id.contact);
        edtContact.setEnabled(false);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        id = prefs.getString("momID", "No name defined");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.viewAccountUrl;

        try {
            String result = new BackgroundWorker(getContext()).execute(postData, url).get();
            JSONObject json_data= new JSONObject(result);
            edtName.setText(json_data.getString("name"));
            edtEmail.setText(json_data.getString("email"));
            edtContact.setText(json_data.getString("phone"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }
    public void edit1Tapped(View view){
        edtEmail.setEnabled(true);
        edtEmail.setHint(edtEmail.getText().toString());
        edtEmail.setText("");
        saveBtn.setVisibility(View.VISIBLE);
        flag = true;
    }

    public void edit2Tapped(View view){
        edtContact.setEnabled(true);
        edtContact.setHint(edtContact.getText().toString());
        edtContact.setText("");
        saveBtn.setVisibility(View.VISIBLE);
    }

    public void edit3Tapped(View view){
        edtName.setEnabled(true);
        edtName.setHint(edtName.getText().toString());
        edtName.setText("");
        saveBtn.setVisibility(View.VISIBLE);
    }

    public void saveBtnTapped(View view){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", id);
            jsonObject.put("flag",flag);
            jsonObject.put("email", edtEmail.getText().toString());
            jsonObject.put("contact", edtContact.getText().toString());
            jsonObject.put("name", edtName.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = jsonObject.toString();
        url = Constants.editAccountUrl;

        try {
            String result = new BackgroundWorker(getContext()).execute(postData, url).get();
            JSONObject json_data= new JSONObject(result);
            String data = json_data.getString("data");

            switch(data){
                case "failed":
                    Toast.makeText(getContext(),"Network error",Toast.LENGTH_LONG).show();break;
                case "Exists":
                    Toast.makeText(getContext(),"Email id already exists",Toast.LENGTH_LONG).show();break;
                default:
                    Toast.makeText(getContext(),"success", Toast.LENGTH_LONG).show();break;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void btnTapped(View view){
        Intent i = new Intent(getContext(), changePassword.class);
        startActivity(i);
        //Toast.makeText(myAccount.this,"success", Toast.LENGTH_LONG).show();

    }
}