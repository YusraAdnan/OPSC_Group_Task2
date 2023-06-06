package com.OPSC_Group_Task2.opsc_group_task2.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.OPSC_Group_Task2.opsc_group_task2.Models.TimesheetData
import com.OPSC_Group_Task2.opsc_group_task2.R
import com.OPSC_Group_Task2.opsc_group_task2.Views.TimesheetAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimesheetEntry : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton
private lateinit var progressbtn: Button
    private lateinit var recv: RecyclerView
    private lateinit var timesheetList:ArrayList<TimesheetData>
    private lateinit var timesheetAdapter: TimesheetAdapter
    private lateinit var backbutton: Button
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet_entry)


        val bundle: Bundle? = intent.extras
        val msg = bundle!!.getString("key")

        if (msg != null) {
            //setting category name to timesheet entry
            val tv1: TextView = findViewById(R.id.tvCategoryName)
            tv1.text = msg
        }
       // Toast.makeText(this, HomePageActivity.project.projectname, Toast.LENGTH_SHORT).show()
        TimesheetEntry.projecttimesheet.timesheetlist2.clear()
        HomePageActivity.timeSheets.timesheetlist2.forEach{
            if(it.EntryName == msg)
            {
                TimesheetEntry.projecttimesheet.timesheetlist2.add(it)
            }
        }
       getTotalHours()


        timesheetList = TimesheetEntry.projecttimesheet.timesheetlist2
        addsBtn =findViewById(R.id.addingTimesheetButton)
        progressbtn = findViewById(R.id.btnprogress)
        recv = findViewById(R.id.tRecycler)
        backbutton=findViewById(R.id.backBtn)
        timesheetAdapter = TimesheetAdapter(this, timesheetList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = timesheetAdapter
        addsBtn.setOnClickListener{addTimesheetInfo()}

      progressbtn.setOnClickListener{
        val vIntent = Intent(this, ProgressPageActivity:: class.java)
        startActivity(vIntent)
       }
        backbutton.setOnClickListener{
            val vIntent = Intent(this, HomePageActivity:: class.java)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addTimesheetInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_timesheet_item, null)

        val EntryName = v.findViewById<EditText>(R.id.etEntryName)

        EntryName.setText(HomePageActivity.project.projectname)
        EntryName.isEnabled = false
        val startDateTime = v.findViewById<EditText>(R.id.etStartDateTime)
        val duration = v.findViewById<EditText>(R.id.etDuration)
        val description = v.findViewById<EditText>(R.id.etDescription)


        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)

        addDialog.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT
            ).show()

            val EntryName = EntryName.text.toString()
            val startDateTime = startDateTime.text.toString()
            val Duration = duration.text.toString()
            val description = description.text.toString()

            try {
                val EndDate1 =  LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            }
            catch(e: Exception)
            {
                Toast.makeText(this, "Invalid  date format", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            timesheetList.add(
                TimesheetData(
                    "$EntryName",
                    "$startDateTime",
                    "$Duration",
                    "$description",


                )
            )
            HomePageActivity.timeSheets.timesheetlist2.add(
                TimesheetData(
                    "$EntryName",
                    "$startDateTime",
                    "$Duration",
                    "$description",

                )
            )
            getTotalHours()
            timesheetAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Timesheet entry added successfully", Toast.LENGTH_SHORT).show()
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
    object projecttimesheet{
        val timesheetlist2 = ArrayList<TimesheetData>()
    }

    fun getTotalHours()
    {
        var TotalHours: Int  =  0
         for(it in projecttimesheet.timesheetlist2) {
             // if(it.Duration.toIntOrNull() != null)
             //  {
             var temp: Int = (it.Duration.filter { it.isDigit() }.toInt())
             TotalHours += temp

             //  }
         }
        val tv1: TextView = findViewById(R.id.tvTotalHours)
        tv1.text = TotalHours.toString()
    }
}