package com.example.srivikashini.shardprf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class KidsAdapter extends BaseAdapter {
String url,postData;
    private Context mContext;
    private String pics;


    public KidsAdapter(Context context, String kpics) {
        mContext = context;
        pics = kpics;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return 1;
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
            convertView = vi.inflate(R.layout.item_kid, parent, false);
            mViewHolder.iv = convertView.findViewById(R.id.thumbnail);
            mViewHolder.vnv = convertView.findViewById(R.id.vnv);
            mViewHolder.cv = convertView.findViewById(R.id.card_view);
            mViewHolder.title = convertView.findViewById(R.id.title);
            mViewHolder.price = convertView.findViewById(R.id.count);
            mViewHolder.cuisine = convertView.findViewById(R.id.cuisine);
//            mViewHolder.cv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Toast.makeText(mContext,Id[position].toString(),Toast.LENGTH_LONG).show();
//
//                        Intent i = new Intent(mContext,itemDecri.class);
//                        Bundle mbundle = new Bundle();
//                        mbundle.putString("itmid",Id[pos]);
//                        mbundle.putString("value",result);
//                        mbundle.putString("fudName",Title[pos]);
//                        mbundle.putString("fooddcusine",Cuisine[pos]);
//                        mbundle.putString("fudType",TypeOfFood[pos]);
//                        mbundle.putString("fudCost",Price[pos]);
//                        i.putExtras(mbundle);
//                        mContext.startActivity(i);
//
//                }
//            });
            convertView.setTag(mViewHolder);
        }
        else { mViewHolder = (ViewHolder) convertView.getTag(); }
//        mViewHolder.title.setText(kidnames[position]);
//        mViewHolder.cuisine.setText(Id[position]);
//        mViewHolder.price.setText(dateofb[position]);
        byte[] decodedString = Base64.decode(pics, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        mViewHolder.iv.setImageBitmap(decodedImage);

        return convertView;
    }

}
