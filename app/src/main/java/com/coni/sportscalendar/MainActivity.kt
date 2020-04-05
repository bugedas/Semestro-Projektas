package com.coni.sportscalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.internal.Logger
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton = findViewById<LoginButton>(R.id.login_button)
        callbackManager = CallbackManager.Factory.create()



        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                Log.d("FBLOGIN", loginResult.accessToken.token.toString())
                Log.d("FBLOGIN", loginResult.recentlyDeniedPermissions.toString())
                Log.d("FBLOGIN", loginResult.recentlyGrantedPermissions.toString())


                val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                    try {
                        //here is the data that you want
                        Log.d("FBLOGIN_JSON_RES", `object`.toString())

                        if (`object`.has("id")) {
                            //handleSignInResultFacebook(`object`)
                        //profile_name =

                        } else {
                            Log.e("FBLOGIN_FAILD", `object`.toString())
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        //dismissDialogLogin()
                    }
                }

                val parameters = Bundle()
                parameters.putString("fields", "name,email,id,picture.type(large)")
                request.parameters = parameters
                request.executeAsync()

            }

            override fun onCancel() {
                Log.e("FBLOGIN_FAILD", "Cancel")
            }

            override fun onError(error: FacebookException) {
                Log.e("FBLOGIN_FAILD", "ERROR", error)
            }
        })


    }
}
