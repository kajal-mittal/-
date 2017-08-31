package com.kmdev.sandesh.utils

import android.content.Context
import android.content.SharedPreferences
import com.kmdev.sandesh.activity.SignupActivity

/**
 * Created by Kajal_Mittal on 29/08/17.
 */
class SharedPrefUtil(private val mContext: Context) {
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    /**
     * Save a string into shared preference

     * @param key   The name of the preference to modify
     * *
     * @param value The new value for the preference
     */
    fun saveString(key: String, value: String) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences!!.edit()
        mEditor!!.putString(key, value)
        mEditor!!.commit()
    }

    /**
     * Retrieve a String value from the preferences.

     * @param key The name of the preference to retrieve.
     * *
     * @return Returns the preference value if it exists, or null.
     * * Throws ClassCastException if there is a preference with this name that is not a String.
     */
    fun getString(key: String): String {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
        return mSharedPreferences!!.getString(key, null)
    }

    companion object {
        /**
         * Name of the preference file
         */
        private val APP_PREFS = "application_preferences"
    }
}
