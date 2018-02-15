package com.gprosper.devradio.services

import com.gprosper.devradio.model.Station


/**
 * Created by gprosper on 2/13/18.
 */
object DataService {

    fun getFeaturedStations() : ArrayList<Station> {
        return ArrayList<Station>(3).apply {
            add(Station("Flight Plan (Tunes for Travel)", "flightplanmusic"))
            add(Station("Two-Wheelin' (Biking Playlist)", "bicyclemusic"))
            add(Station("Kids Jams (Music for children)", "kidsmusic"))
        }
    }

    fun getRecentStations(): ArrayList<Station> {

        return ArrayList()
    }

    fun getPartyStations(): ArrayList<Station> {

        return ArrayList()
    }
}