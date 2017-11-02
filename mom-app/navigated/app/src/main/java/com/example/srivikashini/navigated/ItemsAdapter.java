package com.example.srivikashini.navigated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.Base64;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ItemsAdapter extends BaseAdapter {
String url,postData;
    private Context mContext;
    private String[]  Id, Title, Price, TypeOfFood, Pic, Cuisine;
    int[] drawableIds;

    public ItemsAdapter(Context context,String[] id, String[] text1,String[] price1, String[] type1, String[] pics1, String[] cuisine1) {
        mContext = context;
        Id = id;
        Title = text1;
        Price = price1;
        TypeOfFood = type1;
        Pic = pics1;
        Cuisine = cuisine1;
        int[] tempIds = {R.drawable.icon_veg,R.drawable.icon_nonveg};
        drawableIds = tempIds;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Title.length;
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
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.item, parent, false);
            mViewHolder.iv = convertView.findViewById(R.id.thumbnail);
            mViewHolder.vnv = convertView.findViewById(R.id.vnv);
            mViewHolder.cv = convertView.findViewById(R.id.card_view);
            mViewHolder.title = convertView.findViewById(R.id.title);
            mViewHolder.price = convertView.findViewById(R.id.count);
            mViewHolder.cuisine = convertView.findViewById(R.id.cuisine);
            mViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = position;


                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("fudName", Title[pos]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    postData = jsonObject.toString();
                    url = Constants.fuddesc;

                    try {

                        String result = new BackgroundWorker(mContext).execute(postData, url).get();
                        Intent i = new Intent(mContext,itemDecri.class);
                        Bundle mbundle = new Bundle();
                        mbundle.putString("itmid",Id[pos]);
                        mbundle.putString("value",result);
                        mbundle.putString("fudName",Title[pos]);
                        mbundle.putString("fooddcusine",Cuisine[pos]);
                        mbundle.putString("fudType",TypeOfFood[pos]);
                        mbundle.putString("fudCost",Price[pos]);
                        i.putExtras(mbundle);
                        mContext.startActivity(i);



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            });
            convertView.setTag(mViewHolder);
        }
        else { mViewHolder = (ViewHolder) convertView.getTag(); }
        mViewHolder.title.setText(Title[position]);
        mViewHolder.price.setText(Price[position]);
        mViewHolder.cuisine.setText(Cuisine[position]);
        byte[] decodedString = Base64.decode(Pic[position], Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        mViewHolder.iv.setImageBitmap(decodedImage);
        if(TypeOfFood[position].equals("V")){ mViewHolder.vnv.setImageResource(drawableIds[0]); }
        else { mViewHolder.vnv.setImageResource(drawableIds[1]); }
        return convertView;
    }

}
