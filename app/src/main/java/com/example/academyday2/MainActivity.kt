package com.example.academyday2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun clickHandler(view: View) {
        // EditText nameEdittext = findViewById(R.id.etName)
        var nameEditText : EditText = findViewById(R.id.etName)
        var mainTextView : TextView = findViewById(R.id.tvMain)

        var data = nameEditText.text.toString();
        mainTextView.setText(data)

        var hIntention = Intent(this,HomeActivity::class.java)
        hIntention.putExtra("mykey",data)
        startActivity(hIntention)

    }

    fun openDialer(view: View) {
        var dialerIntention = Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345678"))
        startActivity(dialerIntention)
    }

    fun setAlarm(view: View) {
        createAlarm("cognizantrev",20,43)
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        startActivity(intent)
    }

    fun openCalendar(view: View) {
        val intent = Intent("cognizant.portugal.calendar")
        startActivity(intent)
    }


}