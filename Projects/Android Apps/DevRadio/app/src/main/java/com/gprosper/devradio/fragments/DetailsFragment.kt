package com.gprosper.devradio.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.gprosper.devradio.R
import com.gprosper.devradio.model.Station
import kotlinx.android.synthetic.main.fragment_details.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private var station: Station? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            station = arguments.getParcelable<Station>(ARG_STATION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_details, container, false).apply {
            station?.let { station ->
                findViewById<ImageView>(R.id.imageView)?.let { imageView ->
                    val resourceId = imageView.resources.getIdentifier(station.imageUri, "drawable", context.packageName)
                    imageView.setImageResource(resourceId)
                }
                findViewById<TextView>(R.id.textView)?.let { textView ->
                    textView.text = station.title
                }
            }
        }
    }

    companion object {
        private val ARG_STATION = "ARG_STATION"

        fun newInstance(station: Station): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_STATION, station)
            fragment.arguments = args
            return fragment
        }
    }
}
