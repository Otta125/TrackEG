package com.trackeg.trackegapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //var apiResponse: ApiResponse? = ApiResponse()
   // var apiResponseForSharedPreference: ApiResponse? = ApiResponse()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     /*   apiResponse = getSharedObject(
            this,
            SHARED_PREFERENCE_USER_OBJECT_KEY,
            apiResponseForSharedPreference
        ) as ApiResponse*/

      //  Log.e("GFGFG", apiResponse?.getResponse()?.token!!)
    }
}