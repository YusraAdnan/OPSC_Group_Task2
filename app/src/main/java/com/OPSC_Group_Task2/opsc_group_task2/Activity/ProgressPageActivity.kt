package com.OPSC_Group_Task2.opsc_group_task2.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.OPSC_Group_Task2.opsc_group_task2.R
import com.google.android.play.core.integrity.e
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class ProgressPageActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_page)

        val categoryName = findViewById<EditText>(R.id.categoryNamep)
        val backbtn = findViewById<Button>(R.id.btnclose)
        val calcbtn = findViewById<Button>(R.id.btnCalculate)

        backbtn.setOnClickListener{
            finish()
        }

        calcbtn.setOnClickListener{CalculateProgress()}
        categoryName.setText(HomePageActivity.project.projectname) //sets the current items clicked project name
        categoryName.isEnabled = false
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun CalculateProgress(){

        val date1 = findViewById<EditText>(R.id.etStartDate)
        val date2 = findViewById<EditText>(R.id.etEndDate)

        try {
            val Startdate1 =  LocalDateTime.parse(date1.text, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        }
        catch(e: Exception)
        {
            Toast.makeText(this, "Invalid start date time format. Enter date time as: dd-MM-yyyy HH:mm", Toast.LENGTH_SHORT).show()
            return
        }
        val Startdate =  LocalDateTime.parse(date1.text, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))


        try {
            val EndDate1 =  LocalDateTime.parse(date2.text, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        }
        catch(e: Exception)
        {
            Toast.makeText(this, "Invalid end date time format. Enter date time as: dd-MM-yyyy HH:mm", Toast.LENGTH_SHORT).show()
            return
        }

        val EndDate = LocalDateTime.parse(date2.text, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))

        var totalHours: Int = 0
        HomePageActivity.timeSheets.timesheetlist2.forEach{//for each loop for each item in the global timesheet list

            if( it.EntryName == HomePageActivity.project.projectname)
            {
                val date = LocalDateTime.parse(it.StartDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))

                /*Code Attribution
            *The following stackoverflow link was referred to when comparing the dates
            **Link: https://stackoverflow.com/questions/68325930/compare-date-time-android
            **/
                if(date.isAfter(Startdate)  && date.isBefore(EndDate))
                {
                    var temp: Int = (it.Duration.filter { it.isDigit() }.toInt()) //extracts integer from a string
                    totalHours += temp  //adds and equates the hours worked for between the search date and time
                }
                //__________end_____________
            }
        }
        val tv1: TextView = findViewById(R.id.hoursholdertv)
        tv1.text = totalHours.toString() //equates the total hours to text view in progress page
    }
}