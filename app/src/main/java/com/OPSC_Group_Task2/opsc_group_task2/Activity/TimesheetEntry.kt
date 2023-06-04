package com.OPSC_Group_Task2.opsc_group_task2.Activity

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
import com.OPSC_Group_Task2.opsc_group_task2.Models.TimesheetData
import com.OPSC_Group_Task2.opsc_group_task2.R
import com.OPSC_Group_Task2.opsc_group_task2.Views.TimesheetAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TimesheetEntry : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var timesheetList:ArrayList<TimesheetData>
    private lateinit var timesheetAdapter: TimesheetAdapter
    private lateinit var backbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet_entry)
        timesheetList = ArrayList()
        addsBtn =findViewById(R.id.addingTimesheetButton)
        recv = findViewById(R.id.tRecycler)
        backbutton=findViewById(R.id.backBtn)
        timesheetAdapter = TimesheetAdapter(this, timesheetList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = timesheetAdapter
        addsBtn.setOnClickListener{addTimesheetInfo()}

        backbutton.setOnClickListener{
            val vIntent = Intent(this, HomePageActivity:: class.java)
           vIntent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);

            startActivity(vIntent)
            finish();

        }
    }
    private fun addTimesheetInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_timesheet_item, null)

        val EntryName = v.findViewById<EditText>(R.id.etEntryName)
        val startDate = v.findViewById<EditText>(R.id.etStartDate)
        val dueDate = v.findViewById<EditText>(R.id.etDueDate)
        val description = v.findViewById<EditText>(R.id.etDescription)
        val category = v.findViewById<EditText>(R.id.etCategory)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)

        addDialog.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT
            ).show()

            val EntryName = EntryName.text.toString()
            val startDate = startDate.text.toString()
            val dueDate = dueDate.text.toString()
            val description = description.text.toString()
            val category = category.text.toString()

            timesheetList.add(
                TimesheetData(
                    "EntryName:$EntryName",
                    "startDate: $startDate",
                    "dueDate: $dueDate",
                    "description: $description",
                    "category: $category"
                )
            )
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
}