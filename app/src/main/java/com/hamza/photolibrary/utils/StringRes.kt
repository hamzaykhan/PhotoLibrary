package com.hamza.photolibrary.utils

import android.app.Application
import com.hamza.photolibrary.R

class StringRes(val appContext: Application) {
    fun getString(id: Int): String {
        return appContext.getString(id)
    }
}