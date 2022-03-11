package com.trackeg.trackegapp.model.API

import com.trackeg.trackegapp.model.data.login.LoginResponse
import com.trackeg.trackegapp.model.data.login.User
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface Api {
/*    @FormUrlEncoded
    @POST("account/login")
    fun userLogin(
        @Field("LoginAccount") email: String?,
        @Field("Password") password: String?
    ): Call<LoginResponse?>?    */


    @POST("account/login")
    fun userLogin(@Body body: User): Call<LoginResponse?>?
}