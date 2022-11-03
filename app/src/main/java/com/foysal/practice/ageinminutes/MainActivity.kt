package com.foysal.practice.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker = findViewById<Button>(R.id.DatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            //Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
        }


    }
    fun clickDatePicker(view : View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get((Calendar.YEAR))
        val month = myCalendar.get((Calendar.MONTH))
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val tvSelectedDate = findViewById<TextView>(R.id.selectedDate)
        val tvSelectedDateInMinutes = findViewById<TextView>(R.id.selectedDateInMinutes)

        val dpd = DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
//                            Toast.makeText(this, "Selected Date is  $selectedDay-${selectedMonth+1}-$selectedYear, "
//                                , Toast.LENGTH_LONG).show()
                            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                            tvSelectedDate.setText(selectedDate)

                            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                            val theDate = sdf.parse(selectedDate)

                            val selectedDateInMinutes = theDate!!.time / 60000

                            val currenDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                            val currentDateToMinutes = currenDate!!.time / 60000

                            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())


                        }
                    ,year
                    ,month
                    ,day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}