package com.trackeg.trackegapp.UI.Login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.trackeg.Utilities.LoadingDialog
import com.trackeg.trackegapp.R
import com.trackeg.trackegapp.model.data.login.LoginResponse
import com.trackeg.trackegapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var loginViewModel: LoginViewModel? = null
    private var Loading: LoadingDialog? = null
    private var loadingWithProgress: FrameLayout? = null
    private var loginBtn: Button? = null
    private var emailTxt: TextView? = null
    private var passwordTxt: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //
        loadingWithProgress = findViewById(R.id.loading_frame)
        loginBtn = findViewById(R.id.btnLogin)
        emailTxt = findViewById(R.id.edtEmail)
        passwordTxt = findViewById(R.id.edtPassword)

        //
        Loading = LoadingDialog(this)

        // Calling view model
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginBtn?.setOnClickListener(View.OnClickListener {
            Loading?.startLoadingDialog()
            loginViewModel!!.login(emailTxt?.text.toString(), passwordTxt?.text.toString())
        })

        //make observer on date changed
        loginViewModel!!.loginMutableLiveData.observe(this,
            Observer<LoginResponse> { loginResponse ->
                if (loginResponse==null){
                    Loading?.dissmissDialog()

                }
                if (loginResponse?.response == true) {
                    Loading?.dissmissDialog()
                    Toast.makeText(
                        this@LoginActivity,
                        "\"Logged Successfully\"",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Loading?.dissmissDialog()
                    Toast.makeText(
                        this@LoginActivity,
                        "\"there is something wrong please try again\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }
}