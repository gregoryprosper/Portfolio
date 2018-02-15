package com.gprosper.devradio.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by gprosper on 2/13/18.
 */
private const val DRAWABLE = "drawable/"

class Station(val title: String, val imageUri: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUri)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Station> {
        override fun createFromParcel(parcel: Parcel): Station {
            return Station(parcel)
        }

        override fun newArray(size: Int): Array<Station?> {
            return arrayOfNulls(size)
        }
    }

}