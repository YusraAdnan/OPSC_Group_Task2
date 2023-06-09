package com.OPSC_Group_Task2.opsc_group_task2.Views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.OPSC_Group_Task2.opsc_group_task2.Activity.HomePageActivity
import com.OPSC_Group_Task2.opsc_group_task2.Models.TimesheetData
import com.OPSC_Group_Task2.opsc_group_task2.R
import java.time.Duration

class TimesheetAdapter(val c: Context, private val timesheetList:ArrayList<TimesheetData>) : RecyclerView.Adapter<TimesheetAdapter.TimesheetViewHolder>()
{
    /*Code Attribution
      *These youtube video's were referred to when adding categories to category page
      **Link:https://www.youtube.com/watch?v=RfIR4oaSVfQ
      **Link:https://www.youtube.com/watch?v=xDfkl1Aq6d8
      **/
    var onItemClick: ((TimesheetData) -> Unit)? = null
    inner class TimesheetViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val EntryName = v.findViewById<TextView>(R.id.etEntryNo)
        val StartDateTime = v.findViewById<TextView>(R.id.etStartDateTime)
        val Duration = v.findViewById<TextView>(R.id.etDuration)
        val description = v.findViewById<TextView>(R.id.etDescription)

    }
    //contains layout for every item in recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesheetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_timesheet_item, parent, false)
        return TimesheetViewHolder(v)
    }
    //fetches data from view holder and fills view holders layout with the fetched data
    override fun onBindViewHolder(holder: TimesheetViewHolder, position: Int) {

        val newList = timesheetList[position]
        holder.EntryName.text = newList.EntryName
        holder.StartDateTime.text = newList.StartDateTime
        holder.Duration.text = newList.Duration.toString()
        holder.description.text = newList.Description
    }
    override fun getItemCount(): Int {
        return timesheetList.size
    }
    //_____________end_______________
}