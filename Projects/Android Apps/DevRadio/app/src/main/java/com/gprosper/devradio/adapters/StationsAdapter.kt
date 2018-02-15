package com.gprosper.devradio.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gprosper.devradio.R
import com.gprosper.devradio.model.Station

/**
 * Created by gprosper on 2/13/18.
 */
class StationsAdapter(private val stations: ArrayList<Station>) : RecyclerView.Adapter<StationsAdapter.Holder>() {

    var itemSelectedListener: ((station: Station) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.card_station, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return stations.count()
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val station = stations[position]
        holder?.updateUI(station);
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        private val mainImage = itemView?.findViewById<ImageView>(R.id.main_image)
        private val stationText = itemView?.findViewById<TextView>(R.id.station_text)

        fun updateUI(station: Station) {
            mainImage?.let {
                val resourceId = it.resources.getIdentifier(station.imageUri, "drawable", it.context.packageName)
                it.setImageResource(resourceId)
            }

            stationText?.text = station.title

            itemSelectedListener?.let {
                itemView?.setOnClickListener {
                    it(station)
                }
            }
        }
    }
}