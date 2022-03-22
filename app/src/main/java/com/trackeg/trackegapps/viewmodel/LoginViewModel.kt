package com.trackeg.trackegapps.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otherlogic.pregokotlin.API.RetrofitClient
import com.trackeg.trackegapps.model.data.login.ApiResponse
import com.trackeg.trackegapps.model.data.login.LoginResponse
import com.trackeg.trackegapps.model.data.login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    var loginMutableLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    lateinit var apiResponse: ApiResponse
    fun login(userEmail: String, userPassword: String) {

        val call: Call<LoginResponse?>? = RetrofitClient.instance.userLogin(
            User(userEmail, userPassword)
        )
        call?.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.body() != null) {
                    apiResponse = ApiResponse()
                    apiResponse.setlogin(response.body())
                    loginMutableLiveData.postValue(apiResponse)
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                apiResponse = ApiResponse()
                apiResponse.setError(t)
                loginMutableLiveData.postValue(apiResponse)
            }
        })
    }

}