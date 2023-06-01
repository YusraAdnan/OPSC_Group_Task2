package com.OPSC_Group_Task2.opsc_group_task2.Models

import android.os.Parcel
import android.os.Parcelable

data class ProjectData(

    val projectName:String,
    val MaxHours: String,
    val MinHours: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectName)
        parcel.writeString(MaxHours)
        parcel.writeString(MinHours)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProjectData> {
        override fun createFromParcel(parcel: Parcel): ProjectData {
            return ProjectData(parcel)
        }

        override fun newArray(size: Int): Array<ProjectData?> {
            return arrayOfNulls(size)
        }
    }
}