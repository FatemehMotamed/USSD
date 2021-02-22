package com.example.ussd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Call {


    private  Activity activity;
    private Context mContext;
    private String ussdStr;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 1;

    public Call(Context mContext, String ussdStr, Activity activity) {
        this.ussdStr=ussdStr;
        this.mContext=mContext;
        this.activity=activity;
    }


    @SuppressLint("MissingPermission")
    public void callNow(){
        if(check_call_permision()){

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(ussdToCallableUri(ussdStr));
            try{
                mContext.startActivity(intent);
            } catch (SecurityException e){
                e.printStackTrace();
            }
            Toast.makeText(mContext, "your call send", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},PERMISSION_REQUEST_CODE);
        }

    }



    private Boolean check_call_permision(){
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }

    }

    private Uri ussdToCallableUri(String ussd) {

        String uriString = "";

        if(!ussd.startsWith("tel:"))
            uriString += "tel:";

        for(char c : ussd.toCharArray()) {

            if(c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }

        return Uri.parse(uriString);
    }
}
