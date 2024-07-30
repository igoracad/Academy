package com.example.academyday2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class HomeActivity : AppCompatActivity() {
    var TAG: String = HomeActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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