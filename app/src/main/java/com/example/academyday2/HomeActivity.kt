package com.example.academyday2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    var TAG: String = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.i(TAG, "created --egg-- memory is being allocated")

        //getintent which started this activity
        val dataReceived = intent.extras!!.getString("mykey")


        //from the intent i get extras -- mykey
        //set it on a textview
        val homeView : TextView = findViewById(R.id.tvHome)
        homeView.text = dataReceived
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG, "started --egg hatched -- ui is visible")
    }


    override fun onResume() {
        super.onResume()
        Log.e(TAG, "wake up -- resume -foreground")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "nap -- onpause-- background")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "hibernate-- onstop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "purged-- vanished-destroyed")
    }
}