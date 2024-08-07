package com.example.academyday2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallsReceiver extends BroadcastReceiver {

    private static final String TAG = CallsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Log.i(TAG, "Outgoing call to: " + phoneNumber);

    }
}