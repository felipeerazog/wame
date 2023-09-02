package com.felipe.wame

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val SHARED_PREFERENCES_NAME = "com.felipe.wame"

const val SHOW_DIALOG_PREFERENCE = "show_dialog_preference"

class LocalStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE
    )

    fun getShowDialogPreference(): Boolean {
        return sharedPreferences.getBoolean(SHOW_DIALOG_PREFERENCE, true)
    }

    fun setShowDialogPreference(value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(SHOW_DIALOG_PREFERENCE, value)
            apply()
        }
    }
}
