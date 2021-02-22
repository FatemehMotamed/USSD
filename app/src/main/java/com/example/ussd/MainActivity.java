package com.example.ussd;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE ="" ;
    ListView mList;
    CustomAdapter mAdapter;
    ArrayList<Item> arrayItem;
    ImageView addItem;
    ImageView callNow;
    ImageView irancelBtn;
    ImageView mciBtn;
    ImageView rightelBtn;
    SharedPreferences favoriteUsssd;
    FavoriteList favoriteList;
    String MyPREFERENCES;
    EditText ussd;
    EditText ussdName;
    String[] ussdArray; // for second activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.list_view);
        addItem=findViewById(R.id.img_add);
        callNow=findViewById(R.id.img_cal_now);
        irancelBtn=findViewById(R.id.irancel_btn);
        mciBtn=findViewById(R.id.mci_btn);
        rightelBtn=findViewById(R.id.rightel_btn);


        ussd=findViewById(R.id.ussd);
        ussdName=findViewById(R.id.ussd_name);

        favoriteUsssd=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        arrayItem = new ArrayList<>();

        favoriteList=new FavoriteList(favoriteUsssd,arrayItem);

        favoriteList.loadItem();

        mAdapter = new CustomAdapter(this, arrayItem,favoriteUsssd,MainActivity.this);

        callNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ussdStr=ussd.getText().toString();
                Call call=new Call(MainActivity.this,ussdStr,MainActivity.this);
                call.callNow();
            }
        });


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ussdStr=ussd.getText().toString();
                String ussdNameStr=ussdName.getText().toString();
                favoriteList.addToFavorites(ussdNameStr,ussdStr);
                favoriteList.loadItem();
                mList.setAdapter(mAdapter);

            }
        });


        mList.setAdapter(mAdapter);

        irancelBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

//                ArrayList<String> ussdArray = new ArrayList<String>();
                //                ussdArray.add("hi");
//                ussdArray.add("hello");

                ussdArray=new String[]{"*555*1*1#","*555*5#","*555*1#","*555*55#"};
                sendData();

            }
        });

        mciBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ussdArray=new String[]{"*1*1#","*100#","*10*121#","*10*33#"};
                sendData();
            }
        });

        rightelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ussdArray=new String[]{"*141#","*142#","*130#","*144#"};
                sendData();
            }
        });



//        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(MainActivity.this, "Item Number " + i , Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    private void sendData(){
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE,ussdArray);
        startActivity(intent);
    }

    }

