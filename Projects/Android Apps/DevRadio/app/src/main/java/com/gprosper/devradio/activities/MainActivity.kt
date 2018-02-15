package com.gprosper.devradio.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gprosper.devradio.R
import com.gprosper.devradio.constants.BROADCAST_STATION_CLICKED
import com.gprosper.devradio.fragments.DetailsFragment
import com.gprosper.devradio.fragments.MainFragment
import com.gprosper.devradio.model.Station

class MainActivity : AppCompatActivity() {

    private val stationClickedReciever = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            val station = intent?.getParcelableExtra<Station>(BROADCAST_STATION_CLICKED)!!
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, DetailsFragment.newInstance(station))
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainFragment = supportFragmentManager.findFragmentById(R.id.main_container)
        if (mainFragment == null){
            mainFragment = MainFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_container, mainFragment)
                    .commit()
        }

        val intentFilter = IntentFilter(BROADCAST_STATION_CLICKED)
        registerReceiver(stationClickedReciever, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(stationClickedReciever)
    }
}
