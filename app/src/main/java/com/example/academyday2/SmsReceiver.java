package com.example.academyday2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = SmsReceiver.class.getSimpleName();
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG,"SMS to Cognizant");

        if (intent.getAction() == SMS_RECEIVED) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                Log.i(TAG, "Message recieved: " + messages[0].getMessageBody());
                Log.i(TAG, "Phone Number: " + messages[0].getDisplayOriginatingAddress());
            }
        }

    }



    /* KOTLIN
    companion object {
        val TAG = SmsReceiver::class.java.simpleName
    }
     */
}