package com.coni.sportscalendar

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_create_post.*
import org.json.JSONObject
import java.time.Year
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    lateinit var context : Context
    lateinit var alarmManager: AlarmManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        var date = Calendar.getInstance()
        var metai = date.get(Calendar.YEAR)
        var men = date.get(Calendar.MONTH)
        var diena = date.get(Calendar.DAY_OF_MONTH)
        var valanda = date.get(Calendar.HOUR_OF_DAY)
        var min = date.get(Calendar.MINUTE)


        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        button_submit.setOnClickListener()
        {

            date.set(metai, men, diena, valanda, min)
            val intent = Intent(context,Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, date.timeInMillis - 600000, pendingIntent)
            Log.d("Timer","created: " + Date().toString() )


            var StartD = "" + editText_StartDate.text + "T" + editText_StartTime.text + ":00Z"
            var EndD = "" + editText_EndDate.text + "T" + editText_EndTime.text + ":00Z"

            val post = Post(0,1,editText_postDescription.text.toString(), editText_SportType.text.toString(), StartD.toString(), EndD.toString(),editText_postLocation.text.toString(), editText_PeopleLimit.text.toString().toInt())

            Server.getInstance(this).createPost(post, successPostResponse)
        }

        val today = Calendar.getInstance()

        editText_StartDate.text = "" + today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH)+1) + "-" + today.get(Calendar.DAY_OF_MONTH)
        editText_EndDate.text = "" + today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH)+1) + "-" + today.get(Calendar.DAY_OF_MONTH)

        if((today.get(Calendar.MONTH)+1) < 10 && today.get(Calendar.DAY_OF_MONTH) < 10){
            editText_StartDate.text = "" + today.get(Calendar.YEAR) + "-0" + (today.get(Calendar.MONTH)+1) + "-0" + today.get(Calendar.DAY_OF_MONTH)
            editText_EndDate.text = "" + today.get(Calendar.YEAR) + "-0" + (today.get(Calendar.MONTH)+1) + "-0" + today.get(Calendar.DAY_OF_MONTH)
        }
        else if((today.get(Calendar.MONTH)+1) < 10){
            editText_StartDate.text = "" + today.get(Calendar.YEAR) + "-0" + (today.get(Calendar.MONTH)+1) + "-" + today.get(Calendar.DAY_OF_MONTH)
            editText_EndDate.text = "" + today.get(Calendar.YEAR) + "-0" + (today.get(Calendar.MONTH)+1) + "-" + today.get(Calendar.DAY_OF_MONTH)
        }
        else if(today.get(Calendar.DAY_OF_MONTH) < 10){
            editText_StartDate.text = "" + today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH)+1) + "-0" + today.get(Calendar.DAY_OF_MONTH)
            editText_EndDate.text = "" + today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH)+1) + "-0" + today.get(Calendar.DAY_OF_MONTH)
        }

        editText_StartTime.text = "" + today.get(Calendar.HOUR_OF_DAY) + ":" + today.get(Calendar.MINUTE)
        editText_EndTime.text = "" + today.get(Calendar.HOUR_OF_DAY) + ":" + today.get(Calendar.MINUTE)

        if(today.get(Calendar.MINUTE) < 10 && today.get(Calendar.HOUR_OF_DAY) < 10){
            editText_StartTime.text = "0" + today.get(Calendar.HOUR_OF_DAY) + ":0" + today.get(Calendar.MINUTE)
            editText_EndTime.text = "0" + today.get(Calendar.HOUR_OF_DAY) + ":0" + today.get(Calendar.MINUTE)
        }
        else if(today.get(Calendar.MINUTE) < 10){
            editText_StartTime.text = "" + today.get(Calendar.HOUR_OF_DAY) + ":0" + today.get(Calendar.MINUTE)
            editText_EndTime.text = "" + today.get(Calendar.HOUR_OF_DAY) + ":0" + today.get(Calendar.MINUTE)
        }
        else if(today.get(Calendar.HOUR_OF_DAY) < 10){
            editText_StartTime.text = "0" + today.get(Calendar.HOUR_OF_DAY) + ":" + today.get(Calendar.MINUTE)
            editText_EndTime.text = "0" + today.get(Calendar.HOUR_OF_DAY) + ":" + today.get(Calendar.MINUTE)
        }



        editText_StartDate.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                metai = year
                men = month
                diena = dayOfMonth
                editText_StartDate.text = "" + year + "-" + (month+1) + "-" + dayOfMonth

                if(month+1 < 10 && dayOfMonth < 10){
                    editText_StartDate.text = "" + year + "-0" + (month+1) + "-0" + dayOfMonth
                }
                else if(month+1 < 10){
                    editText_StartDate.text = "" + year + "-0" + (month+1) + "-" + dayOfMonth
                }
                else if(dayOfMonth < 10){
                    editText_StartDate.text = "" + year + "-" + (month+1) + "-0" + dayOfMonth
                }

            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))

            datePicker.show()



        }

        editText_EndDate.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                editText_EndDate.text = "" + year + "-" + (month+1) + "-" + dayOfMonth

                if(month+1 < 10 && dayOfMonth < 10){
                    editText_EndDate.text = "" + year + "-0" + (month+1) + "-0" + dayOfMonth
                }
                else if(month+1 < 10){
                    editText_EndDate.text = "" + year + "-0" + (month+1) + "-" + dayOfMonth
                }
                else if(dayOfMonth < 10){
                    editText_EndDate.text = "" + year + "-" + (month+1) + "-0" + dayOfMonth
                }

            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH))

            datePicker.show()



        }


        editText_StartTime.setOnClickListener(){


            val startDate = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                valanda = hourOfDay
                min = minute

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

    class Receiver : BroadcastReceiver(){

        lateinit var notificationManager: NotificationManager
        lateinit var notificationChannel: NotificationChannel
        lateinit var builder: Notification.Builder
        private val channelID = "com.coni.sportscalendar"
        private val description = "Your event will start soon"


        override fun onReceive(context: Context?, intent: Intent?) {
            //Notification
            Log.d("Timer", "received: " + Date().toString())

            notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(context, LoginActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelID, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.YELLOW
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(context, channelID)
                    .setContentTitle("Coni")
                    .setContentText("Your event will start in 10 min.")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)
            }
            else{
                builder = Notification.Builder(context)
                    .setContentTitle("Coni")
                    .setContentText("Your event will start in 10 min.")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234, builder.build())


        }


    }



    private val successPostResponse = Response.Listener <JSONObject>()
    { response ->
        Log.d("PostActivity", "NetworkResponse : ${response.toString()}")
        finish()
    }
}