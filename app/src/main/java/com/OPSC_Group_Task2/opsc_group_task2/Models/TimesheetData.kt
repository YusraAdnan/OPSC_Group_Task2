package com.OPSC_Group_Task2.opsc_group_task2.Models

import android.os.Parcel
import android.os.Parcelable

data class TimesheetData(

    val EntryName:String,
    val StartDateTime: String,
    val Duration: String,
    val Description: String,
    val Category: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(EntryName)
        parcel.writeString(StartDateTime)
        parcel.writeString(Duration)
        parcel.writeString(Description)
        parcel.writeString(Category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TimesheetData> {
        override fun createFromParcel(parcel: Parcel): TimesheetData {
            return TimesheetData(parcel)
        }

        override fun newArray(size: Int): Array<TimesheetData?> {
            return arrayOfNulls(size)
        }
    }


}