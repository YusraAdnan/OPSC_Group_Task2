package com.OPSC_Group_Task2.opsc_group_task2.Views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.OPSC_Group_Task2.opsc_group_task2.Activity.HomePageActivity
import com.OPSC_Group_Task2.opsc_group_task2.Models.ProjectData
import com.OPSC_Group_Task2.opsc_group_task2.R

class ProjectAdapter(val c: Context, val projectList:ArrayList<ProjectData>) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {
    var onItemClick: ((ProjectData) -> Unit)? = null

    /*Code Attribution
       *These youtube video's were referred to when adding categories to category page
       **Link:https://www.youtube.com/watch?v=RfIR4oaSVfQ
       **Link:https://www.youtube.com/watch?v=xDfkl1Aq6d8
       **/
    inner class ProjectViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val projectName = v.findViewById<TextView>(R.id.pTitle)
        val maxHours = v.findViewById<TextView>(R.id.maxHours)
        val minHours = v.findViewById<TextView>(R.id.minHours)
    }

    //contains layout for every item in recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return ProjectViewHolder(v)
    }
    //fetches data from view holder and fills view holders layout with the fetched data
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val newList = projectList[position]
        holder.projectName.text = newList.projectName
        HomePageActivity.project.projectname = holder.projectName.text.toString()//equating the user input for project name/category name to the global variable for the project name
        holder.maxHours.text = newList.MaxHours.toString()
        holder.minHours.text = newList.MinHours.toString()
        holder.itemView.setOnClickListener {


            holder.itemView.setOnClickListener {
                HomePageActivity.project.projectname = projectList[position].projectName //update project name on every tap
                onItemClick?.invoke(newList)
            }
        }
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
     //_____________end_______________
}