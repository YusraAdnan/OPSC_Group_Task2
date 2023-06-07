package com.OPSC_Group_Task2.opsc_group_task2.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.OPSC_Group_Task2.opsc_group_task2.Models.ProjectData
import com.OPSC_Group_Task2.opsc_group_task2.Models.TimesheetData
import com.OPSC_Group_Task2.opsc_group_task2.R
import com.OPSC_Group_Task2.opsc_group_task2.Views.ProjectAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class HomePageActivity : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var projectList:ArrayList<ProjectData>
    private lateinit var userAdapter: ProjectAdapter
    private lateinit var button:Button

    /*Code Attribution
        *These youtube video's were referred to when adding categories to category page
        **Link:https://www.youtube.com/watch?v=RfIR4oaSVfQ
        **Link:https://www.youtube.com/watch?v=xDfkl1Aq6d8
        **Link:https://www.youtube.com/watch?v=RzjCMa4GBD4&t=32s
        **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //initialize list of projects
        projectList = ArrayList()

        addsBtn=findViewById(R.id.addingButton)
        recv = findViewById(R.id.mRecycler)
        userAdapter = ProjectAdapter(this, projectList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter
        addsBtn.setOnClickListener{addInfo()}


        //when user clicks on the each item added to the recycler view they shall be directed to the add timesheet entry activity
        userAdapter.onItemClick ={

            val intent = Intent(this, TimesheetEntry::class.java)
            intent.putExtra("key",HomePageActivity.project.projectname) //passes the category/project name to the timesheet entry from global variable that holds the project name
            startActivity(intent)
        }
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item, null)

        val projectName = v.findViewById<EditText>(R.id.projectName)
        val maxHours = v.findViewById<EditText>(R.id.maxHours)
        val minHours = v.findViewById<EditText>(R.id.minHours)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)

        addDialog.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT
            ).show()
            //when the user presses "Ok" the text views will be equated to the user input
            val names = projectName.text.toString()
            val max = maxHours.text.toString()
            val min = minHours.text.toString()

            //add user input to the object of the list
            projectList.add(
                ProjectData(
                    "Category:$names",
                    "Maximum Hours: $max",
                    "Minimum Hours: $min"
                )
            )
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding Project information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        addDialog.setNegativeButton(android.R.string.cancel) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.cancel, Toast.LENGTH_SHORT
            ).show()
        }
        addDialog.create()
        addDialog.show()
    }

    //global list holding the timesheet data
    object timeSheets{
        val timesheetlist2 = ArrayList<TimesheetData>()
    }
    //global variable that holds the category name/ project name that user inputs
    object project{
        var projectname:String = "aaa"

    }
      //___________________end____________________
}