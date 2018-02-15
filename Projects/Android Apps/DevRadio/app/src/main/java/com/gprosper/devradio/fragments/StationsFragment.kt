package com.gprosper.devradio.fragments


import android.content.BroadcastReceiver
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gprosper.devradio.R
import com.gprosper.devradio.adapters.StationsAdapter
import com.gprosper.devradio.constants.BROADCAST_STATION_CLICKED
import com.gprosper.devradio.services.DataService
import kotlinx.android.synthetic.main.content_stations_recycler.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [StationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StationsFragment : Fragment() {
    private lateinit var stationsAdapter: StationsAdapter
    private var stationType: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            stationType = arguments.getInt(ARG_STATION_TYPE) ?: STATION_TYPE_FEATURED
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_stations, container, false).apply {
            val stations = when(stationType){
                STATION_TYPE_FEATURED -> DataService.getFeaturedStations()
                STATION_TYPE_RECENT -> DataService.getFeaturedStations()
                STATION_TYPE_PARTY -> DataService.getFeaturedStations()
                else -> DataService.getFeaturedStations()
            }

            stationsAdapter = StationsAdapter(stations).apply {
                itemSelectedListener = { station ->
                    val intent = Intent(BROADCAST_STATION_CLICKED)
                    intent.putExtra(BROADCAST_STATION_CLICKED, station)
                    context.sendBroadcast(intent)
                }
            }

            stations_recyclerView.setHasFixedSize(true)
            stations_recyclerView.adapter = stationsAdapter
            stations_recyclerView.addItemDecoration(HorizontalSpaceItemDecorator(30))
            stations_recyclerView.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }
    }

    class HorizontalSpaceItemDecorator(private val spacer: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect?.right = spacer
        }
    }

    companion object {
        const val STATION_TYPE_FEATURED = 0
        const val STATION_TYPE_RECENT = 1
        const val STATION_TYPE_PARTY = 2

        private const val ARG_STATION_TYPE = "ARG_STATION_TYPE"

        fun newInstance(stationType: Int): StationsFragment {
            val fragment = StationsFragment()
            val args = Bundle()
            args.putInt(ARG_STATION_TYPE, stationType)
            fragment.arguments = args
            return fragment
        }
    }
}
