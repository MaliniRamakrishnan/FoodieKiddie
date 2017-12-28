package com.example.srivikashini.navigated;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by srivikashini on 01/12/17.
 */
public class FriendAdapter extends BaseAdapter {
    String url,postData;
    private Context mContext;
    private String[]  Id, Title, Price, TypeOfFood, Pic, Cuisine;
    ArrayList<String> itmlist = new ArrayList<String>();;

    public FriendAdapter(Context context, String[] id, String[] text1,String[] price1, String[] type1, String[] cuisine1) {
        mContext = context;
        Id = id;
        Title = text1;
        Price = price1;
        TypeOfFood = type1;
        Cuisine = cuisine1;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Id.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    static class ViewHolder {
        TextView title, price, cuisine;
        ImageView iv, vnv;
        CardView cv;
        Button add,status,feedback;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.friend_list_view, parent, false);
            mViewHolder.iv = convertView.findViewById(R.id.thumbnail);
            mViewHolder.vnv = convertView.findViewById(R.id.vnv);
            mViewHolder.cv = convertView.findViewById(R.id.card_view);
            mViewHolder.title = convertView.findViewById(R.id.title);
            mViewHolder.price = convertView.findViewById(R.id.count);
            mViewHolder.cuisine = convertView.findViewById(R.id.cuisine);
            mViewHolder.status = convertView.findViewById(R.id.button22);
            mViewHolder.feedback = convertView.findViewById(R.id.button11);
            mViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    JSONObject jsonObject = new JSONObject();
                    try {

                        jsonObject.put("ordId",Id[position].toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    postData = jsonObject.toString();
                    url = Constants.ordernamefetchUrl;

                    try {

                        String result = new BackgroundWorker(mContext).execute(postData, url).get();
                        try{
                            JSONObject json_data= new JSONObject(result);
                            JSONArray array = json_data.getJSONArray("data");
                            int num = array.length(); int x=0; int count=0;

                            for(int ctrl=0; ctrl<num;ctrl++) {
                                JSONObject row = array.getJSONObject(ctrl);
                                itmlist.add(row.getString("name"));
                            }

                            //Toast.makeText(mContext,itmlist.toString(),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                }
            });
            mViewHolder.feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Dialog dialog = new Dialog(mContext);
                    dialog.setContentView(R.layout.feedbackreview);
                    final EditText feedtext=(EditText)dialog.findViewById(R.id.editText);
                    final RatingBar rating = (RatingBar)dialog.findViewById(R.id.ratingBar);
                    Button sub = (Button) dialog.findViewById(R.id.submit);
                    sub.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String data = feedtext.getText().toString();
                            String ratings = String.valueOf(rating.getRating());
                            Toast.makeText(mContext, "the feedback is:" + "\n" + data + "\n" + ratings, Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                        }


            });



            convertView.setTag(mViewHolder);
        }
        else { mViewHolder = (ViewHolder) convertView.getTag(); }
        mViewHolder.title.setText(Title[position]);
        mViewHolder.price.setText(Price[position]);
        mViewHolder.cuisine.setText(TypeOfFood[position]);
       mViewHolder.status.setText(Cuisine[position]);
        return convertView;
    }

}
