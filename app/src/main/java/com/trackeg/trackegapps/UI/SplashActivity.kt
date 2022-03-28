package com.trackeg.trackegapps.UI

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.ConfigurationCompat
import com.trackeg.trackegapps.MainActivity
import com.trackeg.trackegapps.R
import com.trackeg.trackegapps.UI.Login.LoginActivity
import com.trackeg.trackegapps.Utilities.AppConfigHelper
import com.trackeg.trackegapps.Utilities.AppConfigHelper.Companion.isValid
import com.trackeg.trackegapps.Utilities.Const.ARABIC_LANGUAGE
import com.trackeg.trackegapps.Utilities.Const.ENGLISH_LANGUAGE
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_LANGUAGE_KEY
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_USER_IS_LOGGED_KEY
import com.trackeg.trackegapps.Utilities.SharedPrefHelper
import com.trackeg.trackegapps.Utilities.SharedPrefHelper.Companion.getSharedBoolean

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            getLocalLanguage()
        }, 1500)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun getLocalLanguage() {
        try {
            val language: String = SharedPrefHelper.getSharedString(
                this@SplashActivity,
                SHARED_PREFERENCE_LANGUAGE_KEY
            ).toString()
            if (isValid(language)) {
                if (language == ENGLISH_LANGUAGE) {
                    SharedPrefHelper.setSharedString(
                        this@SplashActivity,
                        SHARED_PREFERENCE_LANGUAGE_KEY,
                        ENGLISH_LANGUAGE
                    )
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                    }
                } else {
                    SharedPrefHelper.setSharedString(
                        this@SplashActivity, SHARED_PREFERENCE_LANGUAGE_KEY, ARABIC_LANGUAGE
                    )
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    }
                }
            } else {
                //start with the default language of the  device
                val language: String =
                    ConfigurationCompat.getLocales(Resources.getSystem().configuration).toString()
                        .substring(1, 3)
                if (language.contains(ENGLISH_LANGUAGE)) {
                    SharedPrefHelper.setSharedString(
                        this@SplashActivity,
                        SHARED_PREFERENCE_LANGUAGE_KEY,
                        ENGLISH_LANGUAGE
                    )
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                    }
                } else {
                    SharedPrefHelper.setSharedString(
                        this@SplashActivity,
                        SHARED_PREFERENCE_LANGUAGE_KEY,
                        ARABIC_LANGUAGE
                    )
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    }
                }
            }
            startActivity()
        } catch (objException: Exception) {
            objException.printStackTrace()
        }
    }

    private fun startActivity() {
        if (getSharedBoolean(this, SHARED_PREFERENCE_USER_IS_LOGGED_KEY)) {
            AppConfigHelper.gotoActivityFinish(this, MainActivity::class.java, true)

        } else {
            AppConfigHelper.gotoActivityFinish(this, LoginActivity::class.java, true)

        }
    }
}