package com.trackeg.trackegapps.Utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager

class AppConfigHelper {
    companion object {

        fun hideKeyboard(activity: Activity) {
            try {
                val imm =
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                //Find the currently focused view, so we can grab the correct window token from it.
                var view = activity.currentFocus
                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = View(activity)
                }
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                view.clearFocus()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        fun hideKeyboardFrom(context: Context, view: View) {
            val imm =
                context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        @JvmStatic
        fun screenDimensions(activity: Activity): Point? {
            return try {
                val size = Point()
                val display = activity.windowManager.defaultDisplay
                display.getSize(size)
                size
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                null
            }
        }

        fun gotoActivityFinish(activity: Activity,cls: Class<*>?,isFinishCuurentActivity: Boolean) {
            try {
                val objIntent = Intent(activity, cls)
                objIntent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                activity.startActivity(objIntent)
                if (isFinishCuurentActivity) {
                    activity.finish()
                }
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        }
        fun isValid(text: String?): Boolean {
            return if (text != null) {
                text != "" && !TextUtils.isEmpty(text) && text.isNotEmpty()
            } else {
                false
            }
        }
    }
}