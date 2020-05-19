package com.coni.sportscalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_change_pass.*
import kotlinx.android.synthetic.main.activity_my_profile.*

class ChangePass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)


        changePass_Button.setOnClickListener(){



            val intent = Intent(this,MyProfile::class.java)
            startActivity(intent)

        }

    }
}