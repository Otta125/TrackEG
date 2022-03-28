package com.trackeg.trackegapps.model.data.login

class ApiResponse() {
    var loginResponse: LoginResponse? = null

    private var error: Throwable? = null
    var isLoading: Boolean? = false

    fun getResponse(): LoginResponse? {
        return loginResponse
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun isLoading(): Boolean {
        return isLoading!!
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