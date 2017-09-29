package com.example.maliniramki.tabs;


import android.content.Context;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;

public class ItemsAdapter extends BaseAdapter {

    private Context mContext;
    private String[]  Title, Price;
    private int[] imge;

    public ItemsAdapter(Context context, String[] text1,String[] price1, int[] imageIds) {
        mContext = context;
        Title = text1;
        Price = price1;
        imge = imageIds;

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

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row;
        row = inflater.inflate(R.layout.item, parent, false);
        TextView title, price;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.thumbnail);
        title = (TextView) row.findViewById(R.id.title);
        price = (TextView) row.findViewById(R.id.count);
        title.setText(Title[position]);
        price.setText(Price[position]);
        i1.setImageResource(imge[position]);

        return (row);
    }
}
