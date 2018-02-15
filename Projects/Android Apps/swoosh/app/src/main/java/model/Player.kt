package model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by gprosper on 1/8/18.
 */
class Player(var league: String? = null, var skill: String? = null) : Parcelable {

    companion object {
        // region Parcelable

        @JvmField val CREATOR = object : Parcelable.Creator<Player> {
            override fun createFromParcel(parcel: Parcel): Player {
                return Player(parcel)
            }

            override fun newArray(size: Int): Array<Player?> {
                return arrayOfNulls(size)
            }
        }

        // endregion
    }

    // region Parcelable

    private constructor(parcel: Parcel) : this(
            league = parcel.readString(),
            skill = parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel){
        writeString(league)
        writeString(skill)
    }

    override fun describeContents() = 0

    // endregion
}