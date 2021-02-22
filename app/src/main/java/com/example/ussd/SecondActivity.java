package com.example.ussd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    String[] uusdArray;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    ImageView btn1;
    ImageView btn2;
    ImageView btn3;
    ImageView btn4;
    String ussdStr;
    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        Intent intent=getIntent();
        uusdArray=intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);

        txt1.setText(uusdArray[0]);
        txt2.setText(uusdArray[1]);
        txt3.setText(uusdArray[2]);
        txt4.setText(uusdArray[3]);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ussdStr=txt1.getText().toString();
                Call call=new Call(SecondActivity.this,ussdStr,SecondActivity.this);
                call.callNow();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ussdStr=txt2.getText().toString();
                Call call=new Call(SecondActivity.this,ussdStr,SecondActivity.this);
                call.callNow();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ussdStr=txt3.getText().toString();
                Call call=new Call(SecondActivity.this,ussdStr,SecondActivity.this);
                call.callNow();

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ussdStr=txt4.getText().toString();
                Call call=new Call(SecondActivity.this,ussdStr,SecondActivity.this);
                call.callNow();

            }
        });

//        TextView txt=findViewById(R.id.txt1);
//        String t="";
//        for (String i: uusdArray){
//           t= t+i;
//
//        }
//
//        txt.setText(t);

    }
}
