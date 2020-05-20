package com.coni.sportscalendar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_create_post.*
import org.json.JSONObject
import java.util.*

class CreatePostActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        var date = Date()
        button_submit.setOnClickListener()
        {
            val post = Post(0,1,create_description.text.toString(), create_sport.text.toString(),create_location.text.toString())//,
                //Date(100,24,234,1,5,6))
            Server.getInstance(this).createPost(post, successPostResponse)
        }

        val today = Calendar.getInstance()

        editText_StartDate.text = "" + today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH)+1) + "/" + today.get(Calendar.DAY_OF_MONTH)
        editText_EndDate.text = "" + today.get(Calendar.YEAR) + "/" + (today.get(Calendar.MONTH)+1) + "/" + today.get(Calendar.DAY_OF_MONTH)

        editText_StartTime.text = "" + today.get(Calendar.HOUR_OF_DAY) + ":" + today.get(Calendar.MINUTE)
        editText_EndTime.text = "" + today.get(Calendar.HOUR_OF_DAY) + ":" + today.get(Calendar.MINUTE)



        editText_StartDate.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> editText_StartDate.text = "" + year + "/" + (month+1) + "/" + dayOfMonth
            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))

            datePicker.show()



        }

        editText_EndDate.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> editText_EndDate.text = "" + year + "/" + (month+1) + "/" + dayOfMonth
            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))

            datePicker.show()



        }


        editText_StartTime.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                editText_StartTime.text = "" + hourOfDay + ":" + minute
                if(minute < 10 && hourOfDay < 10){
                    editText_StartTime.text = "0" + hourOfDay + ":0" + minute
                }
                else if(minute < 10){
                    editText_StartTime.text = "" + hourOfDay + ":0" + minute
                }
                else if(hourOfDay < 10){
                    editText_StartTime.text = "0" + hourOfDay + ":" + minute
                }

                },
                today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), true)



            timePicker.show()
        }

        editText_EndTime.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                editText_EndTime.text = "" + hourOfDay + ":" + minute
                if(minute < 10 && hourOfDay < 10){
                    editText_EndTime.text = "0" + hourOfDay + ":0" + minute
                }
                else if(minute < 10){
                    editText_EndTime.text = "" + hourOfDay + ":0" + minute
                }
                else if(hourOfDay < 10){
                    editText_EndTime.text = "0" + hourOfDay + ":" + minute
                }
                },
                today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), true)

            timePicker.show()
        }

    }

    private val successPostResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("PostActivity", "NetworkResponse : ${response.toString()}")
        finish()
    }
}