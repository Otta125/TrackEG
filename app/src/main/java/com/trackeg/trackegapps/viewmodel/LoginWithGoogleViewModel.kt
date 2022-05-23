package com.trackeg.trackegapps.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.trackeg.core.login.ApiResponse

class LoginWithGoogleViewModel : ViewModel() {
    var loginGoogleMutableLiveData: MutableLiveData<GoogleSignInAccount> = MutableLiveData()
    var addAccountGoogleMutableLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private var signInIntent: Intent? = null
    private lateinit var apiResponse: ApiResponse

    fun loginWithGoogle(activity: Activity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
          //  .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)

        firebaseAuth = FirebaseAuth.getInstance()
        signInIntent = mGoogleSignInClient.signInIntent
    }

/*    fun addAccountGoogle(userEmail: String, userPassword: String) {
        val call: Call<LoginResponse?>? = RetrofitClient.instance.addAccountGoogle(
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
                    addAccountGoogleMutableLiveData.postValue(apiResponse)
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                apiResponse = ApiResponse()
                apiResponse.setError(t)
                addAccountGoogleMutableLiveData.postValue(apiResponse)
            }
        })
    }*/

    fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                updateUI(account)
            } else {
                loginGoogleMutableLiveData.value = account
            }
        } catch (e: ApiException) {

        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                loginGoogleMutableLiveData.value = account
                Log.e("ID", account.id.toString())
                Log.e("displayName", account.displayName.toString())
                Log.e("idToken", account.idToken.toString())
                Log.e("email", account.email.toString())
                Log.e("acc", account.toString())

            }
        }
    }


}