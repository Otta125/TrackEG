package com.trackeg.trackegapps.UI.Login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.trackeg.trackegapps.R
import com.trackeg.trackegapps.UI.ForgotPasswordActivity
import com.trackeg.trackegapps.Utilities.AppConfigHelper
import com.trackeg.trackegapps.Utilities.LoadingDialog
import com.trackeg.trackegapps.model.data.login.ApiResponse
import com.trackeg.trackegapps.viewmodel.LoginViewModel
import com.trackeg.trackegapps.viewmodel.LoginWithGoogleViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var loginViewModel: LoginViewModel? = null
    private var loginWithGoogleViewModel: LoginWithGoogleViewModel? = null
    private var loadingDialog: LoadingDialog? = null
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    var login = true

    // get result of google sign in SDK
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                //send task object to view model functions
                loginWithGoogleViewModel!!.handleResult(task)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //initialize the loading dialog
        loadingDialog = LoadingDialog(this)

        // Calling view model
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginWithGoogleViewModel = ViewModelProvider(this).get(LoginWithGoogleViewModel::class.java)

        loginBtn?.setOnClickListener(View.OnClickListener {
            if (emailTxt?.text?.isEmpty()!! ||
                !emailTxt?.text?.matches(emailPattern)!! ||
                passwordTxt?.text?.isEmpty()!!
            ) {
                login = false
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.enter_valid_data),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                login = true
            }

            if (login) {
                //start loading dialog
                loadingDialog?.startLoadingDialog()
                //call login function in viewModel
                loginViewModel!!.login(emailTxt?.text.toString(), passwordTxt?.text.toString())
            }
        })

        loginGoogleLin?.setOnClickListener(View.OnClickListener {
            loginWithGoogleViewModel!!.loginWithGoogle(this)
            // start Intent for result from google SDK
            val signInIntent: Intent = loginWithGoogleViewModel!!.mGoogleSignInClient.signInIntent
            resultLauncher.launch(signInIntent)
        })

        forgotPasswordTxt?.setOnClickListener(View.OnClickListener {
            AppConfigHelper.gotoActivityFinish(this, ForgotPasswordActivity::class.java, false)
        })
        //make observer on data changed
        loginViewModel!!.loginMutableLiveData.observe(this,
            Observer<ApiResponse> { apiResponse ->
                loadingDialog?.dissmissDialog()
                if (apiResponse == null) {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.try_again),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Observer
                }
                if (apiResponse.getError() == null) {
                    Toast.makeText(
                        this@LoginActivity,
                        apiResponse.getResponse()?.description,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        apiResponse.getError()?.localizedMessage.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        //observe data of login with google SDK
        loginWithGoogleViewModel!!.loginGoogleMutableLiveData.observe(this,
            Observer<GoogleSignInAccount> { googleSignInAccount ->
                loadingDialog?.dissmissDialog()

                if (googleSignInAccount != null) {
                    //call server
                    //start loading dialog
                    loadingDialog?.startLoadingDialog()
                    //call login function in viewModel
                    loginWithGoogleViewModel!!.addAccountGoogle(
                        emailTxt?.text.toString(),
                        passwordTxt?.text.toString()
                    )
                } else {
                    // handle error
                }
            })
        //observe data of add account google API  SDK
        loginWithGoogleViewModel!!.addAccountGoogleMutableLiveData.observe(this,
            Observer<ApiResponse> { apiResponse ->
                loadingDialog?.dissmissDialog()
                if (apiResponse == null) {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.try_again),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Observer
                }
                if (apiResponse.getError() == null) {
                    Toast.makeText(
                        this@LoginActivity,
                        apiResponse.getResponse()?.description,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        apiResponse.getError()?.localizedMessage.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}