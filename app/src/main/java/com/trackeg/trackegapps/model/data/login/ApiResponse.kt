package com.trackeg.trackegapps.model.data.login

class ApiResponse() {
    var loginResponse: LoginResponse? = null
    private var error: Throwable? = null


    fun getResponse(): LoginResponse? {
        return loginResponse
    }

    fun setlogin(loginResponse: LoginResponse?) {
        this.loginResponse = loginResponse
    }

    fun getError(): Throwable? {
        return error
    }

    fun setError(error: Throwable?) {
        this.error = error
    }


}