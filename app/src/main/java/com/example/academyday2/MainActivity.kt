package com.example.academyday2

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.academyday2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object{
         var mScheduler: JobScheduler? = null
    }


    var TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Trigger custom broadcast for testing
        val testIntent = Intent("com.example.academyday2.TEST_ACTION")
        testIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, "1234567890")
        sendBroadcast(testIntent)
    }

    override fun onStart() {
        super.onStart()
        var serviceIntent = Intent(this,MyService::class.java);

        binding.btnStart.setOnClickListener {
            //start a service
            serviceIntent.putExtra("url","imageurl.com")
            startService(serviceIntent)
        }

        binding.btnStop.setOnClickListener { stopService(serviceIntent) }

        binding.btnBind.setOnClickListener{
            bindService(serviceIntent,mConnection, BIND_AUTO_CREATE)
        }

        binding.btnJob.setOnClickListener {
            scheduleJob()
        }

        binding.btnUnjob.setOnClickListener {
            cancelJobs()
        }
    }

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, localBinder: IBinder?) {
            //var myService = MyService()
            val binder = localBinder as MyService.LocalBinder
            var myService =   binder.getMyService()
            var soccerScore = myService.latestScore()
            Log.i(TAG,"score is--"+soccerScore)
            var sum = myService.add(10,20)

            Log.i(TAG,"sum is--"+sum)

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG,"service disconnected --")

        }
    }


    fun scheduleJob() {
        mScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        val serviceName = ComponentName(packageName, MyService::class.java.name)
        val builder = JobInfo.Builder(0, serviceName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

        val myJobInfo = builder.build()
        mScheduler!!.schedule(myJobInfo)
    }

    fun cancelJobs() {
        if (mScheduler != null){
            mScheduler!!.cancelAll()
            mScheduler = null
            //Toast.makeText(this, R.string.jobs_canceled, Toast.LENGTH_SHORT).show();
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

    fun sendFlightBroadCast(view: View) {
        var intent = Intent("ihave.flight")
        intent.setComponent(ComponentName("com.example.secondcognizantapp", "com.example.secondcognizantapp.FlightReceiver"))
        sendBroadcast(intent)
    }


}