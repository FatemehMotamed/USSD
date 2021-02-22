package com.example.ussd;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private final SharedPreferences favoriteUsssd;
    private Activity activity;
    private Context mContext;
    private ArrayList<Item> mItem;
    private FavoriteList favoriteList;

    public CustomAdapter(Context mContext, ArrayList<Item> mItem, SharedPreferences favoriteUsssd, Activity activity) {
        this.mContext = mContext;
        this.mItem = mItem;
        this.favoriteUsssd=favoriteUsssd;
        this.favoriteList=new FavoriteList(favoriteUsssd,mItem);
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return mItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        }

        Item currentItem = (Item) getItem(i);

        ImageView callBtn = view.findViewById(R.id.img_call);
        ImageView removeBtn = view.findViewById(R.id.img_remove);
        final TextView txtItemName = view.findViewById(R.id.txt_name);
        final TextView txtItemUssd = view.findViewById(R.id.txt_ussd);


        txtItemName.setText(currentItem.getTxtName());
        txtItemUssd.setText(currentItem.getTxtUssd());

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext,txtItemName.getText(),Toast.LENGTH_LONG).show();
                String ussdStr=txtItemUssd.getText().toString();
                Call call=new Call(mContext,ussdStr,activity);
                call.callNow();
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item=txtItemName.getText().toString();
                favoriteList.removeFromFavorites(item);
                favoriteList.loadItem();
                notifyDataSetChanged();  // refresh view

            }
        });



        return view;

    }

}