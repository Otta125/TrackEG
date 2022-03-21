package com.trackeg.trackegapps.Utilities

import android.content.Context
import com.google.gson.Gson

class SharedPrefHelper {

    companion object   {
        fun getSharedString(context: Context, key: String?): String? {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            return sharedPreferences.getString(key, "")
        }


        fun getSharedInt(context: Context, key: String?): Int {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            return sharedPreferences.getInt(key, 0)
        }

        fun getSharedFloat(context: Context, key: String?): Float {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            return sharedPreferences.getFloat(key, 0f)
        }

        fun getSharedBoolean(
            context: Context,
            key: String?
        ): Boolean {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            return sharedPreferences.getBoolean(key, false)
        }

        fun setSharedString(
            context: Context,
            key: String?,
            value: String?
        ) {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            sharedPreferences.edit().putString(key, value).apply()
        }

        fun setSharedInt(
            context: Context,
            key: String?,
            value: Int
        ) {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            sharedPreferences.edit().putInt(key, value).apply()
        }

        fun setSharedFloat(
            context: Context,
            key: String?,
            value: Float
        ) {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            sharedPreferences.edit().putFloat(key, value).apply()
        }

        fun setSharedBoolean(
            context: Context,
            key: String?,
            value: Boolean
        ) {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            sharedPreferences.edit().putBoolean(key, value).apply()
        }

        //get saved object
        fun <T> getSharedOBJECT(context: Context, key: String?, type: T): Any? {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            val json = sharedPreferences.getString(key, "")
            return Gson().fromJson(
                json,
                type!!::class.java
            )
        }
        // Save Arrays
/*        fun saveData(
            context: Context,
            mExampleList: ArrayList<CartModel>,
            key: String?
        ) {
            val sharedPreferences = context.getSharedPreferences(
                context.packageName,
                Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val json: String = gson.toJson(mExampleList)
            editor.putString(key, json)
            editor.apply()
        }*/

        // Save object
        fun setSharedOBJECT(context: Context, key: String?, value: Any?) {
            val sharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val prefsEditor = sharedPreferences.edit()
            val gson = Gson()
            val json = gson.toJson(value)
            prefsEditor.putString(key, json)
            prefsEditor.apply()
        }

    }
}