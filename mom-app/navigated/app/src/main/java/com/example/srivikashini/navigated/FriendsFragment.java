package com.example.srivikashini.navigated;

/**
 * Created by srivikashini on 26/10/17.
 */

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class FriendsFragment extends DialogFragment {
    ListView orderhis;
    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);
        orderhis = (ListView)rootView.findViewById(R.id.listviewordersdg);
        CustomOrder customOrder = new CustomOrder();
        orderhis.setAdapter(customOrder);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    class CustomOrder extends BaseAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View views, ViewGroup viewGroup) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            views = inflater.inflate(R.layout.friend_list_view, null);
            TextView foodName = (TextView) views.findViewById(R.id.textView2);
            TextView foodCost = (TextView) views.findViewById(R.id.textView3);
            Button feedback = (Button) views.findViewById(R.id.button2);


            final Button deliveryStatus = (Button) views.findViewById(R.id.button3);
            foodName.setText("SALAD");
            foodCost.setText("RS. 20");

            feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "the feedback is:" , Toast.LENGTH_LONG).show();

                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.feedbackreview);
                    final EditText feedtext=(EditText)dialog.findViewById(R.id.editText);
                    final RatingBar rating = (RatingBar)dialog.findViewById(R.id.ratingBar);
                    Button sub = (Button) dialog.findViewById(R.id.submit);

                    sub.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            String data = feedtext.getText().toString();
                            String ratings = String.valueOf(rating.getRating());


                            Toast.makeText(getContext(), "the feedback is:" + "\n" + data + "\n" + ratings, Toast.LENGTH_LONG).show();

                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });

            deliveryStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deliveryStatus.setEnabled(false);
                }
            });

            return views;


        }

    }
}

