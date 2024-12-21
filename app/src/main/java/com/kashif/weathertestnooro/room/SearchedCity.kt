package com.kashif.weathertestnooro.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mohammad Kashif Ansari on 20,December,2024
 */

@Entity(tableName = "searchedCities")
data class SearchedCity(
    @PrimaryKey val cityName:String,
    val weatherIcon:String,
    val cityTemp: Double
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<SearchedCity> {
        override fun createFromParcel(parcel: Parcel): SearchedCity {
            return SearchedCity(parcel)
        }

        override fun newArray(size: Int): Array<SearchedCity?> {
            return arrayOfNulls(size)
        }
    }
}
