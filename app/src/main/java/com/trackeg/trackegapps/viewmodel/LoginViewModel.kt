package com.trackeg.trackegapps.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.otherlogic.pregokotlin.API.RetrofitClient
import com.trackeg.trackegapps.R
import com.trackeg.trackegapps.Utilities.ConnectionDetector
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_USER_IS_LOGGED_KEY
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_USER_OBJECT_KEY
import com.trackeg.trackegapps.Utilities.SharedPrefHelper
import com.trackeg.trackegapps.Utilities.SharedPrefHelper.Companion.setSharedBoolean
import com.trackeg.trackegapps.Utilities.SharedPrefHelper.Companion.setSharedObject
import com.trackeg.trackegapps.model.data.login.ApiResponse
import com.trackeg.trackegapps.model.data.login.LoginResponse
import com.trackeg.trackegapps.model.data.login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var loginMutableLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    lateinit var apiResponse: ApiResponse
     private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    private var myApplicationContext: Application = application


    fun login(userEmail: String, userPassword: String) {
        // check connection to internet
        if (ConnectionDetector.checkForInternet(myApplicationContext)) {
            // validate email & password
            if (validateLoginEmailAndPassword(userEmail, userPassword)) {
                setDataToApiResponseObject(setObjectOfLoginResponse(false,""), true)
                val call: Call<LoginResponse?>? =RetrofitClient.instance.userLogin(User(userEmail, userPassword))
                call?.enqueue(object : Callback<LoginResponse?> {
                    override fun onResponse(call: Call<LoginResponse?>,response: Response<LoginResponse?>) {
                        if (response.body() != null) {
                            setDataToApiResponseObject(response.body()!!, false)
                            if(response.body()?.status!!){
                                Log.e("fffv", response.body()?.token!!)
                                setSharedObject(
                                    myApplicationContext.applicationContext,
                                    SHARED_PREFERENCE_USER_OBJECT_KEY, apiResponse
                                )
                                setSharedBoolean(myApplicationContext.applicationContext
                                    ,SHARED_PREFERENCE_USER_IS_LOGGED_KEY,true)
                            }
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                        setDataToApiResponseObject(setObjectOfLoginResponse(false,myApplicationContext.getString(R.string.try_again)), false)
                    }
                })
            } else {
                setDataToApiResponseObject(
                    setObjectOfLoginResponse(false,myApplicationContext.getString(R.string.enter_valid_data)), false
                )
            }
        } else {
            setDataToApiResponseObject(setObjectOfLoginResponse(false,myApplicationContext.getString(R.string.no_connection)),false)
        }
    }

    // validation of email & password
    private fun validateLoginEmailAndPassword(userEmail: String, userPassword: String): Boolean {
        return !(userEmail?.isEmpty()!! ||
               /* !userEmail?.matches(emailPattern)!! ||*/
                userPassword?.isEmpty()!!)
    }

    // set values of ApiResponse object
    private fun setDataToApiResponseObject(loginResponse: LoginResponse, loading: Boolean) {
        apiResponse = ApiResponse()
        apiResponse.setlogin(loginResponse)
        apiResponse.setLoading(loading)
        loginMutableLiveData.postValue(apiResponse)
    }

    // set values of LoginResponse object
    private fun setObjectOfLoginResponse(status: Boolean, description: String) :LoginResponse{
      return  LoginResponse(
            status,
            description,
            -1,
            -1,
            "",
            ""
        )
    }
}