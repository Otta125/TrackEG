package com.trackeg.trackegapps.Utilities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_LANGUAGE_KEY
import com.trackeg.trackegapps.Utilities.SharedPrefHelper.Companion.getSharedString
import java.util.*

class LanguageHelper {
    companion object {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun initLanguage(context: Activity, enforceRtl: Boolean) {
            val currentLanguage =
                context.baseContext.resources.configuration.locale.language
            val language: String = getSharedString(
                context,
                SHARED_PREFERENCE_LANGUAGE_KEY
            )!!
            Log.v("lang", language)
            if (!currentLanguage.equals(language, ignoreCase = true)) {
                changeLanguage(context, enforceRtl, language)
            }

        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun changeLanguage(
            context: Activity,
            enforceRtl: Boolean,
            newLanguage: String
        ) {
            SharedPrefHelper.setSharedString(
                context,
                SHARED_PREFERENCE_LANGUAGE_KEY,
                newLanguage
            )
            /*       Locale locale = new Locale(newLanguage);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getBaseContext().getResources().updateConfiguration(config, context.getBaseContext().getResources().getDisplayMetrics());*/
            val res = context.resources
            val newConfig =
                Configuration(res.configuration)
            val locale = Locale(newLanguage)
            Locale.setDefault(locale)
            newConfig.locale = locale
            newConfig.setLayoutDirection(locale)
            res.updateConfiguration(newConfig, null)
            if (enforceRtl) {
                if (newLanguage.equals("ar", ignoreCase = true) || newLanguage.equals(
                        "ur",
                        ignoreCase = true
                    )
                ) forceRTLIfSupported(context, true)
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        private fun forceRTLIfSupported(context: Activity, replace: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && replace) context.window
                .decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && !replace) context.window
                .decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }


        fun getCurrentLanguage(context: Context): String? {
            val value = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            ).getString(SHARED_PREFERENCE_LANGUAGE_KEY, "en")
            Log.v("language", value!!)
            return value
        }
    }
}