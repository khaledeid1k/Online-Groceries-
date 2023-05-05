package com.example.onlinegroceries.utility

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            Constants.prefsProfile,
            Context.MODE_PRIVATE
        )


        fun <T> put(key: String, data: T) {
            sharedPreferences
                .edit().putString(
                    key, data as String
                )
                .apply()


        }

        fun get(key: String): String? {
            return sharedPreferences.getString(key, "")
        }




}