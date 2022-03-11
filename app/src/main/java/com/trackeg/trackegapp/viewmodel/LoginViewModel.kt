package com.trackeg.trackegapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otherlogic.pregokotlin.API.RetrofitClient
import com.trackeg.trackegapp.model.data.login.LoginResponse
import com.trackeg.trackegapp.model.data.login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    var loginMutableLiveData: MutableLiveData<LoginResponse> = MutableLiveData()

    fun login(userEmail: String, userPassword: String) {
        val call: Call<LoginResponse?>? = RetrofitClient.instance.userLogin(
            User(userEmail,userPassword)
        )
        call?.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.body() != null) {
                    loginMutableLiveData.setValue(response.body())
                } else {

                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
            }
        })
    }
}