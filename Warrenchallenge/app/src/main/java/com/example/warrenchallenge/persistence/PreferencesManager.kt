package com.example.warrenchallenge.persistence

import android.content.Context
import com.example.warrenchallenge.BuildConfig
import com.google.gson.Gson

class PreferencesManager(
    context: Context,
) {
    private val sharedPreferences =
        context.getSharedPreferences(BuildConfig.preferences_key, Context.MODE_PRIVATE)

    var accessToken: String?
        get() {
            return "token".load()
        }
        set(value) {
            "token".save(value)
        }

    private fun String.load(): String? {
        if (sharedPreferences == null)
            throw RuntimeException("PreferencesManager should be initialized")
        return sharedPreferences.getString(this, null)
    }

    private fun String.save(value: Any?) {
        if (sharedPreferences == null)
            throw RuntimeException("PreferencesManager should be initialized")
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> {
                editor.putString(this, value)
            }
            is Int -> {
                editor.putInt(this, value)
            }
            is Long -> {
                editor.putLong(this, value)
            }
            is Float -> {
                editor.putFloat(this, value)
            }
            is Boolean -> {
                editor.putBoolean(this, value)
            }
            else -> {
                val json: String? = Gson().toJson(value)
                editor.putString(this, json)
            }
        }
        editor.apply()
    }
}