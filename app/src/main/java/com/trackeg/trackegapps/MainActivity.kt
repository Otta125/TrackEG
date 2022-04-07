package com.trackeg.trackegapps

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_USER_OBJECT_KEY
import com.trackeg.trackegapps.Utilities.SharedPrefHelper.Companion.getSharedObject
import com.trackeg.trackegapps.model.data.login.ApiResponse

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