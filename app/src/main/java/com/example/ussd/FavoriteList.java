package com.example.ussd;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Map;

public class FavoriteList {

    private ArrayList<Item> arrayItem;
    private SharedPreferences favoriteUssd;
    SharedPreferences.Editor editor;



    public FavoriteList(SharedPreferences favoriteUssd, ArrayList<Item> arrayItem){
                this.favoriteUssd=favoriteUssd;
                this.arrayItem=arrayItem;
                this.editor=favoriteUssd.edit();

    }

    public void loadItem() {


        arrayItem.clear();
        Map<String, ?> keys = favoriteUssd.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){

            arrayItem.add(new Item(entry.getValue().toString(),entry.getKey().toString()));

//            Log.d("map values",entry.getKey() + ": " +
//                    entry.getValue().toString());
        }

    }

    public void addToFavorites(String ussdNameStr,String ussdStr){

        editor.putString(ussdNameStr,ussdStr);
        editor.apply();
//        Log.d("", favoriteUsssd.getAll().toString());

    }

    public void removeFromFavorites(String item){

        editor.remove(item);
        editor.apply();

    }


}
