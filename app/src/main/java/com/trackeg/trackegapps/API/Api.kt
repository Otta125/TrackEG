package com.trackeg.trackegapps.API

import com.trackeg.core.login.ApiResponse
import com.trackeg.core.login.LoginResponse
import com.trackeg.core.login.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface Api {
/*    @FormUrlEncoded
    @POST("account/login")
    fun userLogin(
        @Field("LoginAccount") email: String?,
        @Field("Password") password: String?
    ): Call<LoginResponse?>?    */


    // I used "Response"-->Response<ApiResponse?>?  to use Coroutines and get success and error body
    // suspend for Coroutines
    @POST("account/login")
    suspend fun userLogin(@Body body: User): Response<ApiResponse?>?

    @POST("account/login")
    fun addAccountGoogle(@Body body: User): Call<LoginResponse?>?
}