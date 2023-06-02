package com.OPSC_Group_Task2.opsc_group_task2.Views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.OPSC_Group_Task2.opsc_group_task2.Models.TimesheetData
import com.OPSC_Group_Task2.opsc_group_task2.R

class TimesheetAdapter(val c: Context, private val timesheetList:ArrayList<TimesheetData>) : RecyclerView.Adapter<TimesheetAdapter.TimesheetViewHolder>()
{
    var onItemClick: ((TimesheetData) -> Unit)? = null
    inner class TimesheetViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val EntryName = v.findViewById<TextView>(R.id.etEntryNo)
        val StartDate = v.findViewById<TextView>(R.id.etStartDate)
        val DueDate = v.findViewById<TextView>(R.id.etDueDate)
        val description = v.findViewById<TextView>(R.id.etDescription)
        val category = v.findViewById<TextView>(R.id.etCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesheetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_timesheet_item, parent, false)
        return TimesheetViewHolder(v)
    }

    override fun onBindViewHolder(holder: TimesheetViewHolder, position: Int) {
        val newList = timesheetList[position]
        holder.EntryName.text = newList.EntryName
        holder.StartDate.text = newList.StartDate
        holder.DueDate.text = newList.DueDate
        holder.description.text = newList.Description
        holder.category.text = newList.Category

    }
    override fun getItemCount(): Int {
        return timesheetList.size
    }
}