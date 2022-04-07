package com.trackeg.trackegapps.model.data.login

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("roleId")
    val roleId: Int,
    @SerializedName("loginAccount")
    val loginAccount: String,
    @SerializedName("token")
    val token: String
)

