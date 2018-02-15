package com.gprosper.devradio.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
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
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false).apply {
            activity.supportFragmentManager.let {
                val stationFragment1 = StationsFragment.newInstance(StationsFragment.STATION_TYPE_FEATURED)
                val stationFragment2 = StationsFragment.newInstance(StationsFragment.STATION_TYPE_RECENT)
                val stationFragment3 = StationsFragment.newInstance(StationsFragment.STATION_TYPE_PARTY)

                it.beginTransaction().add(R.id.container_top_row, stationFragment1).commit()
                it.beginTransaction().add(R.id.container_middle_row, stationFragment2).commit()
                it.beginTransaction().add(R.id.container_bottom_row, stationFragment3).commit()
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
