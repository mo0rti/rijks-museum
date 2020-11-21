package com.orangeocean.rijksmuseum.utils

import android.util.Log

object AppLogger {
    private const val LOG_TAG = "ORANGE_APP"

    fun logInfo(message: String) {
        Log.i(LOG_TAG, message)
    }

    fun logError(message: String, ex: Exception?) {
        Log.e(LOG_TAG, message, ex)
        // TODO: add an api or cache error logger here
    }
}