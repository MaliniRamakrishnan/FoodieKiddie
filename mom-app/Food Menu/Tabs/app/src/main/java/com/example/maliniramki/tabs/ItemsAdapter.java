package com.example.maliniramki.tabs;

import android.content.Context;
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

public class ItemsAdapter extends BaseAdapter {

    private Context mContext;
    private String[]  Title, Price, TypeOfFood, Pic, Cuisine;
    int[] drawableIds;

    public ItemsAdapter(Context context, String[] text1,String[] price1, String[] type1, String[] pics1, String[] cuisine1) {
        mContext = context;
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
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.item, parent, false);
            mViewHolder.iv = convertView.findViewById(R.id.thumbnail);
            mViewHolder.vnv = convertView.findViewById(R.id.vnv);
            mViewHolder.title = convertView.findViewById(R.id.title);
            mViewHolder.price = convertView.findViewById(R.id.count);
            mViewHolder.cuisine = convertView.findViewById(R.id.cuisine);

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
