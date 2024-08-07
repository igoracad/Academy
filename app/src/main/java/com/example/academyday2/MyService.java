package com.example.academyday2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    private final LocalBinder localBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public MyService getMyService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "my service created");
    }

    public int latestScore() {
        return 5;
    }

    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "my service started--" + (intent != null ? intent.getStringExtra("url") : null));
        MediaPlayer player = MediaPlayer.create(this, R.raw.tune);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "my service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "some activity is trying to bind to this service");
        return localBinder;
    }
}