package com.gprosper.smack.services

import com.gprosper.smack.application.MyApplication

/**
 * Created by gprosper on 2/6/18.
 */
object LoginManager {
    const private val PREFS_FILENAME = "prefs"
    private val prefs = MyApplication.instance.getSharedPreferences(PREFS_FILENAME, 0)

    private const val AUTH_TOKEN = "AUTH_TOKEN"
    private const val AUTH_EMAIL = "AUTH_EMAIL"

    var authToken: String?
        get() = prefs.getString(AUTH_TOKEN, null)
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var authEmail: String?
        get() = prefs.getString(AUTH_EMAIL, null)
        set(value) = prefs.edit().putString(AUTH_EMAIL, value).apply()

    var isLoggedIn = false

    fun logout() {
        authToken = null
        authEmail = null
        isLoggedIn = false
        UserDataService.logout()
    }
}