package com.trackeg.trackegapps.Features.Login

import android.app.Application
import androidx.lifecycle.*

import com.otherlogic.pregokotlin.API.RetrofitClient
import com.trackeg.trackegapps.R
import com.trackeg.trackegapps.Utilities.ConnectionDetector
import com.trackeg.core.login.ApiResponse
import com.trackeg.core.login.User
import com.trackeg.trackegapps.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val myApplicationContext: Application,
    val loginMutableLiveData: MutableLiveData<Resource<ApiResponse>>
) : AndroidViewModel(myApplicationContext) {

   //  private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

    fun login(userEmail: String, userPassword: String) {
        // check connection to internet
        if (ConnectionDetector.checkForInternet(myApplicationContext)) {
            // validate email & password
            if (validateLoginEmailAndPassword(userEmail, userPassword)) {
                ////
                viewModelScope.launch {
                    loginMutableLiveData.postValue(Resource.loading(null))
                    RetrofitClient.instance.userLogin(User(userEmail, userPassword)).let {
                        if (it!!.isSuccessful) {
                            //Log.e("BBBB", it.body().toString())
                            loginMutableLiveData.postValue(Resource.success(it.body()))

                        } else {
                            //Log.e("ERR", "ERRP")
                            loginMutableLiveData.postValue(
                                Resource.error(
                                    myApplicationContext.getString(R.string.Please_try_again_later),
                                    null
                                )
                            )
                        }
                    }
                }


            } else {
                loginMutableLiveData.postValue(
                    Resource.error(
                        myApplicationContext.getString(R.string.enter_valid_data),
                        null
                    )
                )
            }
        } else {
            loginMutableLiveData.postValue(
                Resource.error(
                    myApplicationContext.getString(R.string.no_connection),
                    null
                )
            )
        }
    }

    // validation of email & password
    private fun validateLoginEmailAndPassword(userEmail: String, userPassword: String): Boolean {
        return !(userEmail?.isEmpty()!! ||
                /* !userEmail?.matches(emailPattern)!! ||*/
                userPassword?.isEmpty()!!)
    }

}