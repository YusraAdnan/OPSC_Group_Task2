package com.OPSC_Group_Task2.opsc_group_task2.Views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.OPSC_Group_Task2.opsc_group_task2.Activity.HomePageActivity
import com.OPSC_Group_Task2.opsc_group_task2.Models.ProjectData
import com.OPSC_Group_Task2.opsc_group_task2.R

class ProjectAdapter(val c: Context, val projectList:ArrayList<ProjectData>) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {
    var onItemClick: ((ProjectData) -> Unit)? = null

    inner class ProjectViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val projectName = v.findViewById<TextView>(R.id.pTitle)
        val maxHours = v.findViewById<TextView>(R.id.maxHours)
        val minHours = v.findViewById<TextView>(R.id.minHours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return ProjectViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val newList = projectList[position]
        holder.projectName.text = newList.projectName
        HomePageActivity.project.projectname = holder.projectName.text.toString()
        holder.maxHours.text = newList.MaxHours.toString()
        holder.minHours.text = newList.MinHours.toString()
        holder.itemView.setOnClickListener {

            //new code
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(newList)
            }
        }
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

}