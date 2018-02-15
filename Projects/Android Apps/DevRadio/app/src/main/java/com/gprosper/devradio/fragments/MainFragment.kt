package com.gprosper.devradio.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gprosper.devradio.R


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainFragment", "onCreate")
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("MainFragment", "onCreateView")
        return inflater!!.inflate(R.layout.fragment_main, container, false).apply {
            activity.supportFragmentManager.let {
                var stationFragment1 = it.findFragmentById(R.id.container_top_row)
                if (stationFragment1 == null){
                    stationFragment1 = StationsFragment.newInstance(StationsFragment.STATION_TYPE_FEATURED)
                    it.beginTransaction().add(R.id.container_top_row, stationFragment1).commit()
                }

                var stationFragment2 = it.findFragmentById(R.id.container_middle_row)
                if (stationFragment2 == null){
                    stationFragment2 = StationsFragment.newInstance(StationsFragment.STATION_TYPE_RECENT)
                    it.beginTransaction().add(R.id.container_middle_row, stationFragment2).commit()
                }

                var stationFragment3 = it.findFragmentById(R.id.container_bottom_row)
                if (stationFragment3 == null){
                    stationFragment3 = StationsFragment.newInstance(StationsFragment.STATION_TYPE_PARTY)
                    it.beginTransaction().add(R.id.container_bottom_row, stationFragment3).commit()
                }
            }
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

}
