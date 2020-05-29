package com.viveris.android.appexemplekotlin.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    companion object {
        private const val SHARED_PREF_NAME = "AppExemplePref"
        const val PREF_KEY_ALIAS = "PREF_KEY_ALIAS"

        fun getSharedAppSharedPreference(application: Application): SharedPreferences = application.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}