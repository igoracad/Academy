package com.example.academyday2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = SmsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {


        Log.i(TAG,"SMS to Cognizant");

    }



    /* KOTLIN
    companion object {
        val TAG = SmsReceiver::class.java.simpleName
    }
     */
}