package com.example.srivikashini.navigated;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by srivikashini on 30/11/17.
 */
public class cartAdapter extends BaseAdapter {
    String url,postData;
    private Context mContext;
    private String[]  Id, dateofb, kidnames, picData;


    public cartAdapter(Context context, String[] id, String[] kname, String[] kdob) {
        mContext = context;
        Id = id;
        kidnames = kname;
        dateofb = kdob;

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
        Button add;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.cartmain_listview, parent, false);
            mViewHolder.iv = convertView.findViewById(R.id.thumbnail);
            mViewHolder.vnv = convertView.findViewById(R.id.vnv);
            mViewHolder.cv = convertView.findViewById(R.id.card_view);
            mViewHolder.title = convertView.findViewById(R.id.title);
            mViewHolder.price = convertView.findViewById(R.id.count);
            mViewHolder.cuisine = convertView.findViewById(R.id.cuisine);

            mViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {





                }
            });



            convertView.setTag(mViewHolder);
        }
        else { mViewHolder = (ViewHolder) convertView.getTag(); }
        mViewHolder.title.setText(kidnames[position]);
        mViewHolder.price.setText(dateofb[position]);
        return convertView;
    }

}
